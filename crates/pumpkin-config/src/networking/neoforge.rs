use serde::{Deserialize, Serialize};

/// Configuration for `NeoForge` client compatibility.
///
/// `NeoForge` clients connect to Pumpkin without any of this: a server that announces no
/// modded channels is treated as vanilla, and the client disables its mod-side networking.
/// Enabling these options makes Pumpkin answer some of `NeoForge`'s configuration-phase
/// protocol instead, which is what lets a modded client agree with the server on ids.
#[derive(Deserialize, Serialize, Clone)]
#[serde(default)]
pub struct NeoForgeConfig {
    /// Whether to run `NeoForge` configuration tasks for clients that advertise support.
    ///
    /// Off by default: the protocol is only partly implemented, so a vanilla-looking
    /// connection is still the better-tested path.
    pub enabled: bool,
    /// Whether to synchronise Pumpkin's registry ids to the client.
    ///
    /// Only takes effect when [`Self::enabled`] is set and the client advertises the
    /// frozen-registry channels.
    pub sync_registries: bool,
}

impl Default for NeoForgeConfig {
    fn default() -> Self {
        Self {
            enabled: false,
            sync_registries: true,
        }
    }
}
