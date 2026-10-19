//! `NeoForge` client support.
//!
//! # How a modded connection is established
//!
//! As `NeoForge`'s own server does it. Every client is sent an empty `neoforge:register`
//! query before the server's brand. A `NeoForge` client answers with every payload it
//! registered — and from then on treats the server as modded — while a vanilla client
//! ignores it. The answer is negotiated against what this server offers ([`negotiate`]):
//!
//! - **Agreed:** the agreed set goes out on `neoforge:network`, then the configuration
//!   tasks follow — config files, the frozen registry sync, and the common
//!   `c:version`/`c:register` exchange.
//! - **Refused:** the reasons go out on `neoforge:modded_network_setup_failed`, which the
//!   client shows on its disconnect screen, and the connection is closed.
//!
//! The client does not second-guess the agreed set, so negotiation is the whole of the
//! compatibility check. What the server offers is `NeoForge`'s own optional built-ins that
//! Pumpkin implements, plus the channels plugins declared via [`channels::declare`], all
//! optional: the client must find its required channels here, but need not carry ours.
//!
//! # Declaring the connection modded has a cost
//!
//! Sending `neoforge:network` moves the client off the vanilla path: a mod that tries to
//! send on a channel outside the agreed set is refused rather than quietly disabled, which
//! is what an unmodded connection would give it. So the whole feature is off by default.

pub mod channels;
pub mod negotiate;
pub mod payloads;

use bytes::Bytes;
use pumpkin_data::packet::CURRENT_MC_VERSION;
use pumpkin_util::text::TextComponent;
use tracing::{debug, warn};

use crate::net::java::{
    config::task::{ConfigStage, ConfigTask},
    pending::PendingConnection,
};

pub use channels::{ChannelError, ChannelProtocol, ModdedChannel};

/// Opens a screen a mod draws itself.
pub const OPEN_SCREEN_CHANNEL: &str = "neoforge:advanced_open_screen";
/// Carries a config file the client needs a server-side copy of.
pub const CONFIG_FILE_CHANNEL: &str = "neoforge:config_file";
/// Carries the channel list each side offers, server first, client in reply.
pub const NETWORK_QUERY_CHANNEL: &str = "neoforge:register";
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
/// Carries the reasons negotiation failed, just before the disconnect.
pub const SETUP_FAILED_CHANNEL: &str = "neoforge:modded_network_setup_failed";
/// Common networking (shared with Fabric): the versions each side speaks.
pub const COMMON_VERSION_CHANNEL: &str = "c:version";
/// Common networking: the play channels each side listens on.
pub const COMMON_REGISTER_CHANNEL: &str = "c:register";

/// The common-networking versions this server speaks, as `NeoForge` 26.3 does.
pub const COMMON_VERSIONS: [i32; 1] = [1];

/// Most channels a client may announce, `NeoForge`'s default `maxAdhocChannelLimit`.
pub const MAX_CLIENT_CHANNELS: usize = 2048;

/// `NeoForge`'s own configuration channels, which every `NeoForge` client has built in.
const SYNC_CHANNELS: [&str; 4] = [
    SYNC_START_CHANNEL,
    FROZEN_REGISTRY_CHANNEL,
    SYNC_COMPLETED_CHANNEL,
    CONFIG_FILE_CHANNEL,
];

/// `NeoForge` reads these settings from the server, not the client.
///
/// Its own defaults, with LAN advertising off since that is a client-side courtesy a
/// dedicated server has no use for. Without this the client crashes on its first entity
/// tick: `Level.guardEntityTick` asks the config whether to swallow entity errors, and a
/// config that was never loaded throws instead of answering.
// `NeoForge` 26.3 renamed the config type from SERVER to SYNCED, and with it the file the
// client accepts: one named `neoforge-server.toml` is silently ignored.
const SERVER_CONFIG_NAME: &str = "neoforge-synced.toml";
const SERVER_CONFIG: &str = include_str!("server_config.toml");

/// The subset of the `NeoForge` configuration this module needs.
pub struct NeoForgeSettings {
    /// Whether `NeoForge` support runs at all.
    pub enabled: bool,
    /// Whether the registry sync task runs.
    pub sync_registries: bool,
}

/// Queues the `NeoForge` configuration tasks for this connection.
///
/// Returns whether anything was queued. A `false` result means there is nothing to do yet —
/// the feature is off, or the client has not answered the network query — and the caller
/// may try again when it learns more about the client.
pub fn queue_config_tasks(connection: &mut PendingConnection, config: &NeoForgeSettings) -> bool {
    if !config.enabled {
        return false;
    }
    let Some(agreed) = connection.negotiated_channels.clone() else {
        return false;
    };

    let setup = match payloads::modded_network_setup(&agreed) {
        Ok(setup) => setup,
        Err(err) => {
            warn!("Failed to encode the NeoForge payload setup: {err}");
            return false;
        }
    };
    debug!(
        "Declaring a modded network with {} channel(s)",
        agreed.len()
    );

    // The setup comes first: until the client has it, a modded channel is one the client
    // may read but not answer on.
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget("neoforge:modded_network", MODDED_NETWORK_CHANNEL, setup),
    );

    // `minecraft:register` announces what the *server* listens on.
    let listening: Vec<&str> = agreed
        .iter()
        .filter(|channel| channel.serverbound)
        .map(|channel| channel.id.as_str())
        .collect();
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget(
            "neoforge:register_channels",
            MINECRAFT_REGISTER_CHANNEL,
            payloads::channel_registration(&listening),
        ),
    );

    for server_config in server_config_payloads() {
        connection.queue_config_task(
            ConfigStage::BeforeRegistries,
            ConfigTask::fire_and_forget(
                "neoforge:server_config",
                CONFIG_FILE_CHANNEL,
                server_config.clone(),
            ),
        );
    }

    queue_registry_sync(connection, config);

    true
}

/// Queues the common `c:version` then `c:register` exchange, as `NeoForge` does after the
/// registry sync.
///
/// Runs for a negotiated `NeoForge` client, and for any client that announced both
/// channels, which is how a Fabric client says it speaks them. Returns whether it queued.
pub fn queue_common_tasks(connection: &mut PendingConnection, config: &NeoForgeSettings) -> bool {
    let speaks_common = connection.negotiated_channels.is_some()
        || (connection.supports_channel(COMMON_VERSION_CHANNEL)
            && connection.supports_channel(COMMON_REGISTER_CHANNEL));
    if !config.enabled || !speaks_common {
        return false;
    }

    let listening: Vec<&str> = channels::declared()
        .iter()
        .filter(|channel| channel.serverbound && channel.protocol == ChannelProtocol::Play)
        .map(|channel| channel.id.as_str())
        .collect();
    let (Ok(version), Ok(register)) = (
        payloads::common_version(&COMMON_VERSIONS),
        payloads::common_register(1, "play", &listening),
    ) else {
        warn!("Failed to encode the common networking payloads");
        return false;
    };

    // Each waits for the client's own c:version / c:register, which pending.rs checks
    // before acknowledging.
    connection.queue_config_task(
        ConfigStage::AfterRegistries,
        ConfigTask::awaiting_ack(
            "c:version",
            COMMON_VERSION_CHANNEL,
            version,
            COMMON_VERSION_CHANNEL,
        ),
    );
    connection.queue_config_task(
        ConfigStage::AfterRegistries,
        ConfigTask::awaiting_ack(
            "c:register",
            COMMON_REGISTER_CHANNEL,
            register,
            COMMON_REGISTER_CHANNEL,
        ),
    );
    true
}

/// Why a client's network query answer was not accepted.
pub enum Refusal {
    /// The answer did not decode.
    Malformed(String),
    /// Negotiation failed, for these channels.
    Incompatible(Vec<negotiate::Failure>),
}

/// Negotiates a client's `neoforge:register` answer against what this server offers.
///
/// # Errors
/// [`Refusal`] if the answer is malformed or no channel set could be agreed.
pub fn negotiate_client(answer: &[u8]) -> Result<Vec<ModdedChannel>, Refusal> {
    negotiate_against(answer, channels::declared())
}

/// [`negotiate_client`] against an explicit set of declared channels.
fn negotiate_against(
    answer: &[u8],
    declared: &[ModdedChannel],
) -> Result<Vec<ModdedChannel>, Refusal> {
    let offered = negotiate::parse_query(answer).map_err(Refusal::Malformed)?;
    let mut agreed = Vec::new();
    for protocol in [ChannelProtocol::Configuration, ChannelProtocol::Play] {
        let server = server_channels(protocol, declared);
        let server_components: Vec<negotiate::Component> = server
            .iter()
            .map(|(component, _)| component.clone())
            .collect();
        let client = offered
            .iter()
            .find(|(phase, _)| *phase == protocol)
            .map_or(&[][..], |(_, components)| components.as_slice());

        for component in
            negotiate::negotiate(&server_components, client).map_err(Refusal::Incompatible)?
        {
            let serverbound = server
                .iter()
                .any(|(offer, listens)| offer.id == component.id && *listens);
            agreed.push(ModdedChannel {
                id: component.id,
                protocol,
                version: component.version,
                serverbound,
            });
        }
    }
    Ok(agreed)
}

/// Encodes the reasons for a refusal, as `neoforge:modded_network_setup_failed` carries them.
#[must_use]
pub fn setup_failed_payload(
    failures: &[negotiate::Failure],
    version: &pumpkin_util::version::JavaMinecraftVersion,
) -> Bytes {
    let reasons: Vec<(String, TextComponent)> = failures
        .iter()
        .map(|failure| {
            let args: Vec<TextComponent> = failure
                .args
                .iter()
                .cloned()
                .map(TextComponent::text)
                .collect();
            (
                failure.id.clone(),
                TextComponent::translate(failure.key.clone(), args),
            )
        })
        .collect();
    payloads::setup_failed(&reasons, version)
        .inspect_err(|err| warn!("Failed to encode the NeoForge setup failure: {err}"))
        .unwrap_or_default()
}

/// What this server offers in one phase, and whether it listens on each channel.
///
/// `NeoForge`'s own built-ins that Pumpkin implements, optional as `NeoForge` registers
/// them, then every channel a plugin declared, also optional.
fn server_channels(
    protocol: ChannelProtocol,
    declared: &[ModdedChannel],
) -> Vec<(negotiate::Component, bool)> {
    let builtin = |id: &str| negotiate::Component {
        id: id.to_string(),
        version: payloads::BUILTIN_CHANNEL_VERSION.to_string(),
        flow: None,
        optional: true,
    };
    let mut offered: Vec<(negotiate::Component, bool)> = match protocol {
        ChannelProtocol::Configuration => SYNC_CHANNELS
            .iter()
            // The client echoes the completion payload back to us.
            .map(|id| (builtin(id), *id == SYNC_COMPLETED_CHANNEL))
            .collect(),
        ChannelProtocol::Play => vec![(builtin(OPEN_SCREEN_CHANNEL), false)],
    };
    offered.extend(
        declared
            .iter()
            .filter(|channel| channel.protocol == protocol)
            .map(|channel| {
                (
                    negotiate::Component {
                        id: channel.id.clone(),
                        version: channel.version.clone(),
                        flow: None,
                        // Offered, not demanded: a client's required channels must all be
                        // here, but nothing Pumpkin runs needs a client to carry a mod. It
                        // also means a channel declared by mistake cannot refuse anyone.
                        optional: true,
                    },
                    channel.serverbound,
                )
            }),
    );
    offered
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

/// The `neoforge:register` query sent to every client when the feature is on.
#[must_use]
pub fn network_query(config: &NeoForgeSettings) -> Option<Bytes> {
    config.enabled.then(payloads::network_query)
}

/// Where mods' synced configs live, relative to the server's working directory.
/// `pumpkin-mod-convert` writes them there at their defaults.
const SYNCED_CONFIG_DIR: &str = "synced-configs";

/// Every synced config a client is sent, encoded once: each file under
/// [`SYNCED_CONFIG_DIR`], named by its path there (`Mekanism/gear.toml`), and
/// `neoforge-synced.toml` unless that folder has its own. A NeoForge client loads these
/// from the server alone and throws on reading one that never arrived.
// ponytail: read once at first login; edits need a restart, as NeoForge's own reload sync
// is not mirrored.
fn server_config_payloads() -> &'static [Bytes] {
    static PAYLOADS: std::sync::OnceLock<Vec<Bytes>> = std::sync::OnceLock::new();
    PAYLOADS.get_or_init(|| {
        let mut files = Vec::new();
        collect_configs(std::path::Path::new(SYNCED_CONFIG_DIR), "", &mut files);
        if !files.iter().any(|(name, _)| name == SERVER_CONFIG_NAME) {
            files.push((
                SERVER_CONFIG_NAME.to_string(),
                SERVER_CONFIG.as_bytes().to_vec(),
            ));
        }
        files
            .iter()
            .filter_map(|(name, contents)| {
                payloads::config_file(name, contents)
                    .inspect_err(|err| warn!("Failed to encode synced config {name}: {err}"))
                    .ok()
            })
            .collect()
    })
}

/// Every `.toml` under `dir`, named by its `/`-separated path below the top folder.
fn collect_configs(dir: &std::path::Path, prefix: &str, out: &mut Vec<(String, Vec<u8>)>) {
    let Ok(entries) = std::fs::read_dir(dir) else {
        return;
    };
    let mut entries: Vec<_> = entries.filter_map(Result::ok).collect();
    entries.sort_by_key(std::fs::DirEntry::file_name);
    for entry in entries {
        let name = format!("{prefix}{}", entry.file_name().to_string_lossy());
        let path = entry.path();
        if path.is_dir() {
            collect_configs(&path, &format!("{name}/"), out);
        } else if path.extension().is_some_and(|ext| ext == "toml") {
            match std::fs::read(&path) {
                Ok(contents) => out.push((name, contents)),
                Err(err) => warn!("Failed to read synced config {}: {err}", path.display()),
            }
        }
    }
}

/// The registry sync payloads, built once.
struct SyncPayloads {
    start: Bytes,
    /// Registry name and its snapshot, in send order.
    snapshots: Vec<(String, Bytes)>,
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

/// Encodes `neoforge:advanced_open_screen`: which screen to open and what it needs to know.
///
/// Vanilla's open-screen packet names a menu and a title and stops there. A mod's menu
/// constructor reads more than that — a block position, so the screen knows which machine it
/// is looking at — which is why this payload exists and why a modded screen cannot be opened
/// the vanilla way.
#[must_use]
pub fn advanced_open_screen(
    window_id: i32,
    menu_id: u16,
    title: &TextComponent,
    extra: &[u8],
    version: &pumpkin_util::version::JavaMinecraftVersion,
) -> Bytes {
    payloads::advanced_open_screen(window_id, menu_id, title, extra, version)
        .inspect_err(|err| warn!("Failed to encode the NeoForge open screen payload: {err}"))
        .unwrap_or_default()
}

/// Whether the client runs Sable, which reads a byte vanilla never sends after the entity
/// move and teleport packets. Only a client with the mod agrees on one of its channels.
pub fn speaks_sable(channels: &[ModdedChannel]) -> bool {
    channels
        .iter()
        .any(|channel| channel.id.starts_with("sable:"))
}

/// Appends the flag Sable reads after a play packet's vanilla fields: whether the entity is
/// inside one of its sub-levels. This server has none, so it is always false. Any other
/// packet is returned as it was. `packet_data` is the packet id followed by its payload.
pub fn extend_for_sable(packet_data: Bytes) -> Bytes {
    use pumpkin_data::packet::clientbound::play::{
        MOVE_ENTITY_POS, MOVE_ENTITY_POS_ROT, TELEPORT_ENTITY,
    };
    use pumpkin_protocol::ser::NetworkReadExt;

    let Ok(id) = (&mut &packet_data[..]).get_var_int() else {
        return packet_data;
    };
    if ![MOVE_ENTITY_POS, MOVE_ENTITY_POS_ROT, TELEPORT_ENTITY].contains(&id.0.into()) {
        return packet_data;
    }
    let mut extended = Vec::with_capacity(packet_data.len() + 1);
    extended.extend_from_slice(&packet_data);
    extended.push(0);
    extended.into()
}

#[cfg(test)]
mod open_screen_tests {
    use super::OPEN_SCREEN_CHANNEL;
    use pumpkin_protocol::java::client::play::CCustomPayload;
    use pumpkin_protocol::packet::MultiVersionJavaPacket;
    use pumpkin_util::version::JavaMinecraftVersion;

    /// The screen a mod opens travels on a play-state custom payload, never the
    /// configuration one.
    ///
    /// The two states number their packets separately, and in play the id configuration
    /// gives a custom payload is `add_entity`. A client sent the wrong one does not reject
    /// it as unknown — it decodes the channel and screen data as an entity spawn and closes
    /// the connection over the leftover bytes, which reads as a protocol bug anywhere but
    /// here. This pins the id so the mistake cannot come back quietly.
    #[test]
    fn open_screen_uses_the_play_state_payload_id() {
        for version in [
            JavaMinecraftVersion::V_26_2,
            JavaMinecraftVersion::V_26_1,
            JavaMinecraftVersion::V_1_21_9,
        ] {
            let sent = <CCustomPayload as MultiVersionJavaPacket>::to_id(version);
            let add_entity = pumpkin_data::packet::clientbound::play::ADD_ENTITY.to_id(version);
            let config_payload =
                pumpkin_data::packet::clientbound::config::CUSTOM_PAYLOAD.to_id(version);

            assert_ne!(
                sent, add_entity,
                "{version}: the screen payload would be read as an entity spawn"
            );
            assert_eq!(
                config_payload, add_entity,
                "{version}: the trap this guards against has moved; update the test"
            );
        }
        assert!(!OPEN_SCREEN_CHANNEL.is_empty());
    }
}

#[cfg(test)]
mod negotiation_tests {
    use super::*;
    use negotiate::{Component, Flow};
    use pumpkin_protocol::{codec::var_int::VarInt, ser::NetworkWriteExt};

    /// Encodes a client answer the way `NeoForge`'s `ModdedNetworkQueryPayload` does.
    fn answer(phases: &[(ChannelProtocol, &[Component])]) -> Vec<u8> {
        let mut buf = Vec::new();
        buf.write_var_int(&VarInt(phases.len() as i32)).unwrap();
        for (protocol, components) in phases {
            buf.write_var_int(&VarInt(protocol.ordinal())).unwrap();
            buf.write_var_int(&VarInt(components.len() as i32)).unwrap();
            for c in *components {
                buf.write_string(&c.id).unwrap();
                buf.write_string(&c.version).unwrap();
                buf.write_bool(c.flow.is_some()).unwrap();
                if let Some(flow) = c.flow {
                    buf.write_var_int(&VarInt(flow as i32)).unwrap();
                }
                buf.write_bool(c.optional).unwrap();
            }
        }
        buf
    }

    fn neo(id: &str, flow: Flow) -> Component {
        Component {
            id: id.into(),
            version: "1".into(),
            flow: Some(flow),
            optional: true,
        }
    }

    fn mod_channel(id: &str) -> ModdedChannel {
        ModdedChannel {
            id: id.into(),
            protocol: ChannelProtocol::Play,
            version: "2".into(),
            serverbound: true,
        }
    }

    /// A stock `NeoForge` client offers dozens of optional built-ins; Pumpkin agrees to
    /// the ones it implements and drops the rest.
    #[test]
    fn a_stock_client_agrees_to_the_built_ins_pumpkin_implements() {
        let config = [
            neo(CONFIG_FILE_CHANNEL, Flow::Clientbound),
            neo(SYNC_COMPLETED_CHANNEL, Flow::Clientbound),
            neo("neoforge:feature_flags", Flow::Clientbound),
        ];
        let play = [
            neo(OPEN_SCREEN_CHANNEL, Flow::Clientbound),
            neo("neoforge:aux_light_data", Flow::Clientbound),
        ];
        let Ok(agreed) = negotiate_against(
            &answer(&[
                (ChannelProtocol::Configuration, &config),
                (ChannelProtocol::Play, &play),
            ]),
            &[],
        ) else {
            panic!("a stock client should be accepted");
        };

        let ids: Vec<&str> = agreed.iter().map(|c| c.id.as_str()).collect();
        assert_eq!(
            ids,
            [
                SYNC_COMPLETED_CHANNEL,
                CONFIG_FILE_CHANNEL,
                OPEN_SCREEN_CHANNEL
            ]
        );
        assert!(agreed[0].serverbound, "the sync completion is echoed back");
    }

    #[test]
    fn a_declared_channel_is_offered_not_required() {
        let declared = [mod_channel("mekanism:packet")];
        let Ok(agreed) = negotiate_against(&answer(&[]), &declared) else {
            panic!("a client without the mod is not refused over the server's channels");
        };
        assert!(agreed.is_empty());

        let offered = [Component {
            id: "mekanism:packet".into(),
            version: "2".into(),
            flow: Some(Flow::Serverbound),
            optional: false,
        }];
        let Ok(agreed) =
            negotiate_against(&answer(&[(ChannelProtocol::Play, &offered)]), &declared)
        else {
            panic!("a client with the mod should be accepted");
        };
        assert_eq!(agreed, vec![mod_channel("mekanism:packet")]);

        let Err(Refusal::Incompatible(failures)) =
            negotiate_against(&answer(&[(ChannelProtocol::Play, &offered)]), &[])
        else {
            panic!("a client requiring a channel the server lacks is still refused");
        };
        assert_eq!(failures[0].id, "mekanism:packet");
    }

    #[test]
    fn sable_reads_a_flag_after_entity_moves_only() {
        use pumpkin_data::packet::clientbound::play::{MOVE_ENTITY_POS, SET_TIME};
        let packet = |id: i32| Bytes::from(vec![u8::try_from(id).unwrap(), 7, 7]);
        assert_eq!(
            &extend_for_sable(packet(MOVE_ENTITY_POS.0))[..],
            &[u8::try_from(MOVE_ENTITY_POS.0).unwrap(), 7, 7, 0]
        );
        assert_eq!(extend_for_sable(packet(SET_TIME.0)), packet(SET_TIME.0));
    }

    #[test]
    fn common_payloads_round_trip() {
        let version = payloads::common_version(&COMMON_VERSIONS).unwrap();
        assert_eq!(
            payloads::parse_common_version(&version).unwrap(),
            COMMON_VERSIONS
        );

        let register = payloads::common_register(1, "play", &["a:x", "b:y"]).unwrap();
        assert_eq!(
            payloads::parse_common_register(&register).unwrap(),
            (
                "play".to_string(),
                vec!["a:x".to_string(), "b:y".to_string()]
            )
        );
    }
}
