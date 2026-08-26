//! `NeoForge` client support.
//!
//! # How a modded connection is established
//!
//! `NeoForge` has no handshake of its own. Its server reads the channel list the client
//! advertises on `minecraft:register` and runs the configuration tasks that client can
//! handle. Pumpkin does the same where it can, but cannot rely on it: a client carrying a
//! mod whose payloads were registered as **required** disconnects during configuration
//! before advertising anything, having concluded it is talking to a vanilla server. So the
//! trigger here is the client's brand, which arrives first and is enough on its own —
//! `NeoForge`'s built-in channels exist on every `NeoForge` client by definition.
//!
//! Two things then happen, and they are independent:
//!
//! 1. **The payload setup is declared**, always. This is what lets a mod's required
//!    channels negotiate, and so what lets the client join at all. The channels come from
//!    plugins via [`channels::declare`], because they belong to the mod being implemented,
//!    not to Pumpkin.
//! 2. **The registries are synchronised**, when the client's game version matches the one
//!    Pumpkin's data was generated for. This is what makes ids agree, so that content a
//!    plugin registered means the same thing on both sides.
//!
//! Older clients still get step 1 and so can still join; they just do not get id agreement.
//!
//! # Declaring the connection modded has a cost
//!
//! Sending `neoforge:network` moves the client off the vanilla path. It then narrows its
//! channel list to what was negotiated — a client that advertised fifteen channels comes
//! back having kept ten — and a mod that tries to send on a channel outside that set is
//! refused rather than quietly disabled, which is what an unmodded connection would give
//! it. So the setup declares only what is actually in use, and the whole feature is off by
//! default.

pub mod channels;
pub mod payloads;

use bytes::Bytes;
use pumpkin_data::packet::CURRENT_MC_VERSION;
use tracing::{debug, warn};

use crate::net::java::{
    config::task::{ConfigStage, ConfigTask},
    pending::PendingConnection,
};

pub use channels::{ChannelError, ChannelProtocol, ModdedChannel};

/// Announces which registries are about to be synchronised.
pub const SYNC_START_CHANNEL: &str = "neoforge:frozen_registry_sync_start";
/// Carries one registry snapshot.
pub const FROZEN_REGISTRY_CHANNEL: &str = "neoforge:frozen_registry";
/// Closes the sync. Sent by the server, echoed by the client.
pub const SYNC_COMPLETED_CHANNEL: &str = "neoforge:frozen_registry_sync_completed";
/// Declares which modded channels the connection may carry.
pub const MODDED_NETWORK_CHANNEL: &str = "neoforge:network";
/// Vanilla channel-registration, used here to announce what the server listens on.
pub const MINECRAFT_REGISTER_CHANNEL: &str = "minecraft:register";

/// `NeoForge`'s own configuration channels, which every `NeoForge` client has built in.
const SYNC_CHANNELS: [&str; 3] = [
    SYNC_START_CHANNEL,
    FROZEN_REGISTRY_CHANNEL,
    SYNC_COMPLETED_CHANNEL,
];

/// The subset of the `NeoForge` configuration this module needs.
pub struct NeoForgeSettings {
    /// Whether `NeoForge` support runs at all.
    pub enabled: bool,
    /// Whether the registry sync task runs.
    pub sync_registries: bool,
}

/// Queues the `NeoForge` configuration tasks for this connection.
///
/// Returns whether anything was queued. A `false` result means there is nothing to do —
/// the feature is off, or this is not a `NeoForge` client — and the caller may try again
/// when it learns more about the client.
pub fn queue_config_tasks(connection: &mut PendingConnection, config: &NeoForgeSettings) -> bool {
    if !config.enabled || !is_neoforge_client(connection) {
        return false;
    }

    let Some(payloads) = negotiation_payloads() else {
        return false;
    };

    debug!(
        "Declaring a modded network with {} channel(s)",
        payloads.channel_count
    );

    // The setup comes first: until the client has it, a modded channel is one the client
    // may read but not answer on, and a required one is grounds for disconnecting.
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget(
            "neoforge:modded_network",
            MODDED_NETWORK_CHANNEL,
            payloads.setup.clone(),
        ),
    );
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget(
            "neoforge:register_channels",
            MINECRAFT_REGISTER_CHANNEL,
            payloads.register.clone(),
        ),
    );

    queue_registry_sync(connection, config);

    true
}

/// Adds the frozen registry sync, if this client can be given Pumpkin's ids.
fn queue_registry_sync(connection: &mut PendingConnection, config: &NeoForgeSettings) {
    if !config.sync_registries {
        return;
    }

    let version = connection.version.load();
    if version != CURRENT_MC_VERSION {
        // A snapshot has to describe the client's own registries exactly, and Pumpkin only
        // has the generated version's. The client still joins; its ids just stay its own.
        debug!(
            "Skipping NeoForge registry sync: client is on {version}, but Pumpkin's data \
             describes {CURRENT_MC_VERSION}"
        );
        return;
    }

    let Some(payloads) = sync_payloads() else {
        return;
    };

    debug!(
        "Queueing NeoForge registry sync for {} registries",
        payloads.snapshots.len()
    );

    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget(
            "neoforge:sync_start",
            SYNC_START_CHANNEL,
            payloads.start.clone(),
        ),
    );
    for (registry, snapshot) in &payloads.snapshots {
        connection.queue_config_task(
            ConfigStage::BeforeRegistries,
            ConfigTask::fire_and_forget(
                format!("neoforge:sync_{registry}"),
                FROZEN_REGISTRY_CHANNEL,
                snapshot.clone(),
            ),
        );
    }
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::awaiting_ack(
            "neoforge:sync_completed",
            SYNC_COMPLETED_CHANNEL,
            Bytes::new(),
            SYNC_COMPLETED_CHANNEL,
        ),
    );
}

/// Whether this connection is worth speaking `NeoForge` to.
///
/// The brand is the reliable signal: it arrives at the start of configuration, before a
/// client with required mod payloads would give up. The channel list is checked too, for
/// clients that send it first.
fn is_neoforge_client(connection: &PendingConnection) -> bool {
    connection
        .brand
        .as_deref()
        .is_some_and(|brand| brand.contains("neoforge"))
        || connection.supports_channel(SYNC_START_CHANNEL)
}

/// The `neoforge:network` and `minecraft:register` payloads, built once.
struct NegotiationPayloads {
    setup: Bytes,
    register: Bytes,
    channel_count: usize,
}

/// The registry sync payloads, built once.
struct SyncPayloads {
    start: Bytes,
    /// Registry name and its snapshot, in send order.
    snapshots: Vec<(String, Bytes)>,
}

/// Builds the negotiation payloads on first use.
///
/// Channels are fixed once plugins have loaded, so every connection is handed the same
/// bytes rather than re-encoding them per join.
fn negotiation_payloads() -> Option<&'static NegotiationPayloads> {
    static PAYLOADS: std::sync::OnceLock<Option<NegotiationPayloads>> = std::sync::OnceLock::new();

    PAYLOADS
        .get_or_init(|| {
            let mut all: Vec<ModdedChannel> = SYNC_CHANNELS
                .iter()
                .map(|id| ModdedChannel {
                    id: (*id).to_string(),
                    protocol: ChannelProtocol::Configuration,
                    version: payloads::BUILTIN_CHANNEL_VERSION.to_string(),
                    // The client echoes the completion payload back to us.
                    serverbound: *id == SYNC_COMPLETED_CHANNEL,
                })
                .collect();
            all.extend(channels::declared().iter().cloned());

            let setup = payloads::modded_network_setup(&all)
                .inspect_err(|err| warn!("Failed to encode the NeoForge payload setup: {err}"))
                .ok()?;

            // `minecraft:register` announces what the *server* listens on.
            let listening: Vec<&str> = all
                .iter()
                .filter(|channel| channel.serverbound)
                .map(|channel| channel.id.as_str())
                .collect();

            Some(NegotiationPayloads {
                setup,
                register: payloads::channel_registration(&listening),
                channel_count: all.len(),
            })
        })
        .as_ref()
}

/// Builds the registry sync payloads on first use.
fn sync_payloads() -> Option<&'static SyncPayloads> {
    static PAYLOADS: std::sync::OnceLock<Option<SyncPayloads>> = std::sync::OnceLock::new();

    PAYLOADS
        .get_or_init(|| {
            let mut snapshots = Vec::new();
            for registry in payloads::SYNCED_REGISTRIES {
                let Some(encoded) = payloads::registry_snapshot(registry) else {
                    warn!("No snapshot available for {registry}; skipping the registry sync");
                    return None;
                };
                let encoded = encoded
                    .inspect_err(|err| warn!("Failed to encode {registry}: {err}"))
                    .ok()?;
                snapshots.push((registry.to_string(), encoded));
            }

            let names: Vec<&str> = payloads::SYNCED_REGISTRIES.to_vec();
            let start = payloads::sync_start(&names)
                .inspect_err(|err| warn!("Failed to encode the registry sync start: {err}"))
                .ok()?;

            Some(SyncPayloads { start, snapshots })
        })
        .as_ref()
}
