//! Modded network channels the server is willing to carry.
//!
//! A mod's client half refuses to join unless every channel it registered as *required* was
//! negotiated. Those channels belong to the mod, not to Pumpkin, so the server cannot know
//! them ahead of time — a plugin implementing the mod's server half declares them during
//! load, and the negotiation sent to each client is built from what was declared.
//!
//! # Lifecycle
//!
//! The same two phases as the content registries, and for the same reason: the negotiated
//! set has to be identical for every connection, so it is fixed before the first one
//! arrives. [`declare`] is open while plugins load; [`freeze`] closes it.

use std::sync::{Mutex, OnceLock};

/// The protocol phase a channel is used in.
///
/// The values are the ordinals of vanilla's `ConnectionProtocol`, which is how `NeoForge`
/// encodes the keys of a payload setup: `HANDSHAKING, PLAY, STATUS, LOGIN, CONFIGURATION`.
#[derive(Debug, Clone, Copy, PartialEq, Eq, PartialOrd, Ord)]
pub enum ChannelProtocol {
    /// In play, after the client has joined the world. Where most mod traffic lives.
    Play = 1,
    /// During configuration, before the client joins.
    Configuration = 4,
}

impl ChannelProtocol {
    /// The ordinal `NeoForge` expects on the wire.
    #[must_use]
    pub const fn ordinal(self) -> i32 {
        self as i32
    }
}

/// A channel the server will negotiate.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct ModdedChannel {
    /// Namespaced channel id, e.g. `mysticalagriculture:update_aoe_offset`.
    pub id: String,
    /// The phase the channel is used in.
    pub protocol: ChannelProtocol,
    /// The version string the mod registered its payloads under. `NeoForge` compares this
    /// against the client's, so it has to match the mod's `event.registrar("...")` exactly.
    pub version: String,
    /// Whether the server listens on this channel, which decides if it is announced on
    /// `minecraft:register`.
    pub serverbound: bool,
}

/// Why a channel declaration was rejected.
#[derive(Debug, PartialEq, Eq)]
pub enum ChannelError {
    /// [`freeze`] has already run.
    Frozen,
    /// The id has no namespace.
    InvalidId(String),
    /// The id was already declared for the same protocol phase.
    Duplicate(String),
}

impl std::fmt::Display for ChannelError {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match self {
            Self::Frozen => write!(f, "network channels are frozen"),
            Self::InvalidId(id) => write!(f, "channel {id} must be namespaced"),
            Self::Duplicate(id) => write!(f, "channel {id} is already declared"),
        }
    }
}

impl std::error::Error for ChannelError {}

static STAGING: Mutex<Option<Vec<ModdedChannel>>> = Mutex::new(None);
static FROZEN: OnceLock<Vec<ModdedChannel>> = OnceLock::new();

/// Declares a channel the server will negotiate with `NeoForge` clients.
///
/// # Errors
///
/// Returns [`ChannelError`] if declaration has closed, the id is not namespaced, or the
/// same id was already declared for that phase.
pub fn declare(channel: ModdedChannel) -> Result<(), ChannelError> {
    if FROZEN.get().is_some() {
        return Err(ChannelError::Frozen);
    }

    if !channel.id.contains(':') {
        return Err(ChannelError::InvalidId(channel.id));
    }

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(Vec::new);

    if staging
        .iter()
        .any(|existing| existing.id == channel.id && existing.protocol == channel.protocol)
    {
        return Err(ChannelError::Duplicate(channel.id));
    }

    staging.push(channel);
    Ok(())
}

/// Closes declaration and publishes the negotiated set.
pub fn freeze() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take()
        .unwrap_or_default();

    // Ignores the result: a second freeze leaves the first publication in place.
    let _ = FROZEN.set(staged);
}

/// Every declared channel. Empty until [`freeze`] has run.
#[must_use]
pub fn declared() -> &'static [ModdedChannel] {
    FROZEN.get().map_or(&[], Vec::as_slice)
}

#[cfg(test)]
mod tests {
    use super::*;

    fn channel(id: &str, protocol: ChannelProtocol) -> ModdedChannel {
        ModdedChannel {
            id: id.to_string(),
            protocol,
            version: "1".to_string(),
            serverbound: false,
        }
    }

    #[test]
    fn protocol_ordinals_match_vanillas_enum() {
        assert_eq!(ChannelProtocol::Play.ordinal(), 1);
        assert_eq!(ChannelProtocol::Configuration.ordinal(), 4);
    }

    /// One test for the whole lifecycle: the registry is process-global and freezes once,
    /// so separate tests would depend on the order they happened to run in.
    #[test]
    fn declaration_lifecycle() {
        assert_eq!(
            declare(channel("update_aoe_offset", ChannelProtocol::Play)),
            Err(ChannelError::InvalidId("update_aoe_offset".to_string())),
            "an id without a namespace is rejected"
        );

        assert!(declare(channel("examplemod:one", ChannelProtocol::Play)).is_ok());
        assert!(
            declare(channel("examplemod:one", ChannelProtocol::Configuration)).is_ok(),
            "the same id in another phase is a different channel"
        );
        assert_eq!(
            declare(channel("examplemod:one", ChannelProtocol::Play)),
            Err(ChannelError::Duplicate("examplemod:one".to_string()))
        );

        assert!(
            declared().is_empty(),
            "declarations are invisible until frozen"
        );

        freeze();

        assert_eq!(declared().len(), 2);
        assert_eq!(
            declare(channel("examplemod:too_late", ChannelProtocol::Play)),
            Err(ChannelError::Frozen)
        );
    }
}
