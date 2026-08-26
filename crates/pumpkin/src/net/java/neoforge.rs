//! `NeoForge` configuration-phase support.
//!
//! `NeoForge` does not negotiate with a handshake of its own. Its server reads the channel
//! list the client advertises on `minecraft:register` and registers only the configuration
//! tasks that client can handle. Pumpkin does the same: [`queue_config_tasks`] inspects the
//! channels captured during configuration and queues the tasks it can honour.
//!
//! # The snapshot is authoritative, so it is limited to one game version
//!
//! A `neoforge:frozen_registry` payload replaces the registry it names. It is not an
//! additive assignment of ids to entries the client already has: send a snapshot listing
//! nothing and the client ends up with a block registry containing nothing, then dies on
//! the first chunk with `No value with id 0`. Whatever Pumpkin sends must therefore be the
//! complete list.
//!
//! The client also validates every key against its own registry and disconnects on the
//! first name it does not recognise. Between the two, the snapshot has to match the
//! client's game version exactly — a 26.1 client rejects 26.2's `minecraft:cinnabar`.
//!
//! Pumpkin's per-version data remaps block *state ids*, not registry names, so an older
//! version's block list cannot be reconstructed from it. The sync therefore runs only for
//! clients on the version the data was generated for. Everyone else falls back to behaving
//! as they do against any vanilla server, which works.
//!
//! # Declaring the connection modded has a cost
//!
//! Sending `neoforge:network` moves the client off the vanilla path. It then narrows its
//! channel list to what was negotiated — a client that advertised fifteen channels comes
//! back having kept ten — and a mod that tries to send on a channel outside that set is
//! refused rather than quietly disabled, which is what an unmodded connection would give
//! it. So the setup declares only the channels actually in use. This is also why the whole
//! feature is off by default.
//!
//! Only registry synchronisation is implemented. That is the task that matters most,
//! because it is how the client learns the server's numeric ids — without it, any content
//! Pumpkin registers at runtime is meaningless to a modded client. Everything else
//! (`c:version`, config file sync, registry data maps, feature flags) is still unanswered,
//! which a `NeoForge` client tolerates exactly as it tolerates a vanilla server.
//!
//! # Wire format
//!
//! Five payloads, matching `NeoForge` 26.x:
//!
//! - `neoforge:network` — the negotiated payload setup: which channels the connection may
//!   carry, per protocol phase. Without it the client refuses to *send* on a modded
//!   channel even when it happily reads one, because its `hasChannel` check finds no
//!   negotiated entry and the ad-hoc fallback does not permit serverbound traffic. That is
//!   what makes the acknowledgement below possible at all.
//! - `minecraft:register` — the channels the server listens on, NUL-separated, vanilla
//!   format.
//! - `neoforge:frozen_registry_sync_start` — the registry names about to be sent, as a
//!   length-prefixed list of identifiers.
//! - `neoforge:frozen_registry` — one registry: its name, then a snapshot holding an
//!   id-to-name map and an alias map. Pumpkin has no aliases, so that map is always empty.
//! - `neoforge:frozen_registry_sync_completed` — empty. The server sends it to close the
//!   sequence, and the client echoes it back to acknowledge.

use std::sync::OnceLock;

use bytes::Bytes;
use pumpkin_data::{Block, BlockId, packet::CURRENT_MC_VERSION};
use pumpkin_protocol::{
    codec::var_int::VarInt,
    ser::{NetworkWriteExt, WritingError},
};
use tracing::{debug, warn};

use crate::net::java::{
    config::task::{ConfigStage, ConfigTask},
    pending::PendingConnection,
};

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

/// The registry Pumpkin can currently describe: block ids.
const BLOCK_REGISTRY: &str = "minecraft:block";

/// Ordinal of `ConnectionProtocol.CONFIGURATION` in vanilla's enum, which `NeoForge`
/// encodes the payload setup's keys with.
///
/// The enum is `HANDSHAKING, PLAY, STATUS, LOGIN, CONFIGURATION`. Confirmed against a
/// `NeoForge` 26.2.0.67 client, which accepts the setup and re-registers its channels down
/// to the declared set.
const CONFIGURATION_PROTOCOL: i32 = 4;

/// Version string `NeoForge` registers its built-in configuration payloads under.
const BUILTIN_CHANNEL_VERSION: &str = "1";

/// The channels the registry sync needs the client to treat as negotiated.
const SYNC_CHANNELS: [&str; 3] = [
    SYNC_START_CHANNEL,
    FROZEN_REGISTRY_CHANNEL,
    SYNC_COMPLETED_CHANNEL,
];

/// The subset of the `NeoForge` configuration this module needs.
pub struct NeoForgeSettings {
    /// Whether `NeoForge` configuration tasks run at all.
    pub enabled: bool,
    /// Whether the registry sync task runs.
    pub sync_registries: bool,
}

/// Queues the `NeoForge` configuration tasks this client can handle.
///
/// Returns whether anything was queued. A `false` result means this connection has nothing
/// to do — the feature is off, or the client has not (yet) advertised the channels — and
/// the caller is free to try again when more of the client's channel list arrives.
pub fn queue_config_tasks(connection: &mut PendingConnection, config: &NeoForgeSettings) -> bool {
    if !config.enabled || !config.sync_registries {
        return false;
    }

    let version = connection.version.load();
    if version != CURRENT_MC_VERSION {
        // The snapshot has to describe the client's own block list exactly, and Pumpkin
        // only has the generated version's. See the module docs.
        debug!(
            "Skipping NeoForge registry sync: client is on {version}, but the block \
             registry describes {CURRENT_MC_VERSION}"
        );
        return false;
    }

    let supported = SYNC_CHANNELS
        .iter()
        .all(|channel| connection.supports_channel(channel));

    if !supported {
        debug!("Client does not advertise the frozen registry channels; skipping sync");
        return false;
    }

    let Some((setup, register, start, block_snapshot)) = cached_payloads() else {
        return false;
    };

    debug!("Queueing NeoForge registry sync");

    // The client has to know the server's ids before it can interpret the registry data
    // that follows, so every part of this runs before the built-in registry send.
    //
    // The setup declaration comes first: until the client has it, the sync is a one-way
    // conversation it cannot answer.
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget(
            "neoforge:modded_network",
            MODDED_NETWORK_CHANNEL,
            setup.clone(),
        ),
    );
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget(
            "neoforge:register_channels",
            MINECRAFT_REGISTER_CHANNEL,
            register.clone(),
        ),
    );
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget("neoforge:sync_start", SYNC_START_CHANNEL, start.clone()),
    );
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::fire_and_forget(
            "neoforge:sync_block_registry",
            FROZEN_REGISTRY_CHANNEL,
            block_snapshot.clone(),
        ),
    );
    connection.queue_config_task(
        ConfigStage::BeforeRegistries,
        ConfigTask::awaiting_ack(
            "neoforge:sync_completed",
            SYNC_COMPLETED_CHANNEL,
            Bytes::new(),
            SYNC_COMPLETED_CHANNEL,
        ),
    );

    true
}

/// The sync payloads, built once.
///
/// Registry ids are fixed by the time any client connects, so every connection is handed
/// the same bytes rather than re-encoding them per join.
fn cached_payloads() -> Option<&'static (Bytes, Bytes, Bytes, Bytes)> {
    static PAYLOADS: OnceLock<Option<(Bytes, Bytes, Bytes, Bytes)>> = OnceLock::new();

    PAYLOADS
        .get_or_init(|| {
            let encode = |what: &str, result: Result<Bytes, WritingError>| {
                result
                    .inspect_err(|err| warn!("Failed to encode the NeoForge {what}: {err}"))
                    .ok()
            };

            Some((
                encode("payload setup", modded_network_setup(&SYNC_CHANNELS))?,
                channel_registration(&SYNC_CHANNELS),
                encode("registry sync start", sync_start(&[BLOCK_REGISTRY]))?,
                encode("block registry snapshot", block_registry_snapshot())?,
            ))
        })
        .as_ref()
}

/// Encodes `neoforge:network`: the channels this connection may carry.
///
/// The shape is a map of protocol phase to a map of channel id to its negotiated version.
/// Only the configuration phase is declared, because that is the only phase Pumpkin answers
/// on.
fn modded_network_setup(channels: &[&str]) -> Result<Bytes, WritingError> {
    let mut buf = Vec::new();

    // One protocol phase: configuration.
    write_len(&mut buf, 1)?;
    buf.write_var_int(&VarInt(CONFIGURATION_PROTOCOL))?;

    write_len(&mut buf, channels.len())?;
    for channel in channels {
        // The id is both the map key and part of the channel value.
        buf.write_string(channel)?;
        buf.write_string(channel)?;
        buf.write_string(BUILTIN_CHANNEL_VERSION)?;
    }

    Ok(Bytes::from(buf))
}

/// Encodes `minecraft:register`: NUL-separated channel names, the vanilla format.
fn channel_registration(channels: &[&str]) -> Bytes {
    Bytes::from(channels.join("\0").into_bytes())
}

/// Encodes `neoforge:frozen_registry_sync_start`: the registries about to be sent.
fn sync_start(registries: &[&str]) -> Result<Bytes, WritingError> {
    let mut buf = Vec::new();
    write_len(&mut buf, registries.len())?;
    for registry in registries {
        buf.write_string(registry)?;
    }
    Ok(Bytes::from(buf))
}

/// Encodes `neoforge:frozen_registry` for `minecraft:block`.
///
/// Every block, generated and runtime-registered alike. The payload is authoritative for
/// the registry it names — see the module docs — so a partial map is not an option.
fn block_registry_snapshot() -> Result<Bytes, WritingError> {
    let count = BlockId::count();

    let mut buf = Vec::new();
    buf.write_string(BLOCK_REGISTRY)?;

    write_len(&mut buf, count as usize)?;
    for raw_id in 0..count {
        let Some(id) = BlockId::new(raw_id) else {
            // `raw_id` is below the count that produced it, so this cannot happen.
            continue;
        };
        buf.write_var_int(&VarInt(i32::from(raw_id)))?;
        buf.write_string(&namespaced(Block::from_id(id).name))?;
    }

    // Pumpkin has no registry aliases.
    write_len(&mut buf, 0)?;

    Ok(Bytes::from(buf))
}

/// Writes a collection length as the `VarInt` prefix `NeoForge` expects.
fn write_len(buf: &mut Vec<u8>, len: usize) -> Result<(), WritingError> {
    let len = i32::try_from(len).map_err(|_| {
        WritingError::Message("collection is too large to encode as a VarInt".to_string())
    })?;
    buf.write_var_int(&VarInt(len))
}

/// Generated block names carry no namespace; runtime-registered ones already do.
fn namespaced(name: &str) -> String {
    if name.contains(':') {
        name.to_string()
    } else {
        format!("minecraft:{name}")
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    /// Reads a `VarInt` back, mirroring `write_var_int`.
    fn read_var_int(buf: &[u8], cursor: &mut usize) -> i32 {
        let mut value = 0i32;
        let mut shift = 0u32;
        loop {
            let byte = buf[*cursor];
            *cursor += 1;
            value |= i32::from(byte & 0x7F) << shift;
            if byte & 0x80 == 0 {
                return value;
            }
            shift += 7;
        }
    }

    fn read_string(buf: &[u8], cursor: &mut usize) -> String {
        let len = read_var_int(buf, cursor) as usize;
        let text = String::from_utf8(buf[*cursor..*cursor + len].to_vec()).expect("utf8");
        *cursor += len;
        text
    }

    #[test]
    fn modded_network_setup_declares_the_configuration_channels() {
        let payload = modded_network_setup(&SYNC_CHANNELS).expect("encodes");
        let mut cursor = 0;

        assert_eq!(read_var_int(&payload, &mut cursor), 1, "one protocol phase");
        assert_eq!(
            read_var_int(&payload, &mut cursor),
            CONFIGURATION_PROTOCOL,
            "declared for the configuration phase"
        );
        assert_eq!(read_var_int(&payload, &mut cursor), 3, "three channels");

        for channel in SYNC_CHANNELS {
            assert_eq!(read_string(&payload, &mut cursor), channel, "map key");
            assert_eq!(read_string(&payload, &mut cursor), channel, "channel id");
            assert_eq!(read_string(&payload, &mut cursor), BUILTIN_CHANNEL_VERSION);
        }

        assert_eq!(cursor, payload.len(), "no trailing bytes");
    }

    #[test]
    fn channel_registration_round_trips_through_the_vanilla_parser() {
        use crate::net::java::config::task::parse_channel_list;

        let payload = channel_registration(&SYNC_CHANNELS);
        assert_eq!(parse_channel_list(&payload), SYNC_CHANNELS.to_vec());
    }

    #[test]
    fn sync_start_lists_every_registry() {
        let payload = sync_start(&["minecraft:block", "minecraft:item"]).expect("encodes");
        let mut cursor = 0;
        assert_eq!(read_var_int(&payload, &mut cursor), 2);
        assert_eq!(read_string(&payload, &mut cursor), "minecraft:block");
        assert_eq!(read_string(&payload, &mut cursor), "minecraft:item");
        assert_eq!(cursor, payload.len(), "no trailing bytes");
    }

    /// The snapshot replaces the client's registry wholesale, so it has to list every
    /// block. A short one leaves the client unable to resolve ids it needs for chunks.
    #[test]
    fn block_snapshot_covers_every_block() {
        let payload = block_registry_snapshot().expect("encodes");
        let mut cursor = 0;

        assert_eq!(read_string(&payload, &mut cursor), "minecraft:block");

        let count = read_var_int(&payload, &mut cursor);
        assert_eq!(count, i32::from(BlockId::count()), "every block");

        for expected_id in 0..count {
            assert_eq!(read_var_int(&payload, &mut cursor), expected_id);
            let name = read_string(&payload, &mut cursor);
            assert!(
                name.contains(':'),
                "{name} should be a namespaced identifier"
            );
        }

        assert_eq!(read_var_int(&payload, &mut cursor), 0, "no aliases");
        assert_eq!(cursor, payload.len(), "no trailing bytes");
    }

    /// Air is id 0 on both sides; if that drifts, chunks stop decoding.
    #[test]
    fn block_snapshot_starts_at_air() {
        let payload = block_registry_snapshot().expect("encodes");
        let mut cursor = 0;
        let _registry = read_string(&payload, &mut cursor);
        let _count = read_var_int(&payload, &mut cursor);

        assert_eq!(read_var_int(&payload, &mut cursor), 0);
        assert_eq!(read_string(&payload, &mut cursor), "minecraft:air");
    }

    #[test]
    fn generated_names_gain_a_namespace_and_registered_ones_keep_theirs() {
        assert_eq!(namespaced("stone"), "minecraft:stone");
        assert_eq!(namespaced("examplemod:ruby"), "examplemod:ruby");
    }
}
