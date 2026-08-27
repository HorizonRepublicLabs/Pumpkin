//! Wire encoders for the `NeoForge` payloads Pumpkin sends.
//!
//! # `neoforge:network` — the payload setup
//!
//! A map of protocol phase to a map of channel id to its negotiated version. The client
//! refuses to *send* on a modded channel that is not in this map — reading one can fall
//! through to an ad-hoc registration, sending cannot — and a mod whose payloads were
//! registered as required refuses the connection outright if its channels are absent.
//!
//! # `neoforge:frozen_registry` — a registry snapshot
//!
//! A registry name, then an id-to-name map, then an alias map. The payload **replaces** the
//! registry it names rather than adding to it: a snapshot listing nothing leaves the client
//! with an empty registry, which fails on the first chunk with `No value with id 0`. So a
//! snapshot always describes a registry completely.
//!
//! The client also validates every key against its own registry and disconnects on the
//! first name it does not recognise, which is why snapshots only go to clients on the game
//! version Pumpkin's data was generated for.

use bytes::Bytes;
use pumpkin_data::{Block, BlockId, dynamic, entity::EntityType, fluid::Fluid, item::Item};
use pumpkin_protocol::{
    codec::var_int::VarInt,
    ser::{NetworkWriteExt, WritingError},
};

use super::channels::{ChannelProtocol, ModdedChannel};

/// Version string `NeoForge` registers its built-in configuration payloads under.
pub const BUILTIN_CHANNEL_VERSION: &str = "1";

/// The registries Pumpkin can describe completely, in the order they are sent.
pub const SYNCED_REGISTRIES: [&str; 6] = [
    "minecraft:block",
    "minecraft:item",
    "minecraft:entity_type",
    "minecraft:fluid",
    "minecraft:block_entity_type",
    "minecraft:menu",
];

/// Encodes `neoforge:network`: which channels this connection may carry, per phase.
pub fn modded_network_setup(channels: &[ModdedChannel]) -> Result<Bytes, WritingError> {
    // Group by phase, because the outer map is keyed by protocol.
    let mut phases: Vec<(ChannelProtocol, Vec<&ModdedChannel>)> = Vec::new();
    for channel in channels {
        match phases
            .iter_mut()
            .find(|(phase, _)| *phase == channel.protocol)
        {
            Some((_, group)) => group.push(channel),
            None => phases.push((channel.protocol, vec![channel])),
        }
    }

    let mut buf = Vec::new();
    write_len(&mut buf, phases.len())?;

    for (protocol, group) in phases {
        buf.write_var_int(&VarInt(protocol.ordinal()))?;
        write_len(&mut buf, group.len())?;
        for channel in group {
            // The id is both the map key and part of the channel value.
            buf.write_string(&channel.id)?;
            buf.write_string(&channel.id)?;
            buf.write_string(&channel.version)?;
        }
    }

    Ok(Bytes::from(buf))
}

/// Encodes `neoforge:register`: the server's own channels, offered for negotiation.
///
/// This is the opening move. A `NeoForge` client answers it with its own channel list on
/// the same channel, and only then is the connection modded as far as it is concerned —
/// which is why it is sent to every client rather than waiting to learn what one is. A
/// vanilla client ignores a channel it does not know.
///
/// Each entry is the channel id, its version, an optional packet flow and whether it is
/// optional. The flow is left empty, meaning the channel is usable in both directions.
pub fn network_query(channels: &[ModdedChannel]) -> Result<Bytes, WritingError> {
    let mut phases: Vec<(ChannelProtocol, Vec<&ModdedChannel>)> = Vec::new();
    for channel in channels {
        match phases
            .iter_mut()
            .find(|(phase, _)| *phase == channel.protocol)
        {
            Some((_, group)) => group.push(channel),
            None => phases.push((channel.protocol, vec![channel])),
        }
    }

    let mut buf = Vec::new();
    write_len(&mut buf, phases.len())?;

    for (protocol, group) in phases {
        buf.write_var_int(&VarInt(protocol.ordinal()))?;
        write_len(&mut buf, group.len())?;
        for channel in group {
            buf.write_string(&channel.id)?;
            buf.write_string(&channel.version)?;
            // No packet flow: the channel is not restricted to one direction.
            buf.write_bool(false)?;
            // Not optional: these are the channels the connection is built around.
            buf.write_bool(false)?;
        }
    }

    Ok(Bytes::from(buf))
}

/// Encodes `neoforge:advanced_open_screen`.
///
/// Window id, the menu's registry id, the title, then whatever the menu's own constructor
/// reads. See [`super::advanced_open_screen`] for why vanilla's packet will not do.
pub fn advanced_open_screen(
    sync_id: u8,
    menu_id: u16,
    title: &pumpkin_util::text::TextComponent,
    extra: &[u8],
    version: &pumpkin_util::version::JavaMinecraftVersion,
) -> Result<Bytes, WritingError> {
    let mut buf = Vec::new();
    buf.write_var_int(&VarInt(i32::from(sync_id)))?;
    buf.write_var_int(&VarInt(i32::from(menu_id)))?;
    buf.write_component(title, version)?;
    write_len(&mut buf, extra.len())?;
    buf.extend_from_slice(extra);
    Ok(Bytes::from(buf))
}

/// Encodes `neoforge:config_file`: a config file's name and its contents.
///
/// `NeoForge` reads some of its own settings from the server rather than the client, and
/// the client crashes on its first entity tick if they never arrive — `Level.guardEntityTick`
/// asks the config whether to swallow entity errors, and a config that was never loaded
/// throws instead of answering.
pub fn config_file(name: &str, contents: &[u8]) -> Result<Bytes, WritingError> {
    let mut buf = Vec::new();
    buf.write_string(name)?;
    write_len(&mut buf, contents.len())?;
    buf.extend_from_slice(contents);
    Ok(Bytes::from(buf))
}

/// Encodes `minecraft:register`: NUL-separated channel names, the vanilla format.
#[must_use]
pub fn channel_registration(channels: &[&str]) -> Bytes {
    Bytes::from(channels.join("\0").into_bytes())
}

/// Encodes `neoforge:frozen_registry_sync_start`: the registries about to be sent.
pub fn sync_start(registries: &[&str]) -> Result<Bytes, WritingError> {
    let mut buf = Vec::new();
    write_len(&mut buf, registries.len())?;
    for registry in registries {
        buf.write_string(registry)?;
    }
    Ok(Bytes::from(buf))
}

/// Encodes a `neoforge:frozen_registry` snapshot for one registry.
///
/// Returns `None` for a registry name this function does not know how to describe, rather
/// than sending a partial snapshot — see the module docs for why a partial one is unsafe.
#[must_use]
pub fn registry_snapshot(registry: &str) -> Option<Result<Bytes, WritingError>> {
    let entries: Vec<(u16, String)> = match registry {
        "minecraft:block" => (0..BlockId::count())
            .filter_map(BlockId::new)
            .map(|id| (id.as_u16(), namespaced(Block::from_id(id).name)))
            .collect(),
        "minecraft:item" => (0..dynamic::item_count())
            .filter_map(|id| Item::from_id(id).map(|item| (id, namespaced(item.registry_key))))
            .collect(),
        "minecraft:entity_type" => (0..dynamic::entity_type_count())
            .filter_map(|id| EntityType::from_raw(id).map(|ty| (id, namespaced(ty.resource_name))))
            .collect(),
        "minecraft:fluid" => (0..dynamic::fluid_count())
            .filter_map(|id| Fluid::from_id(id).map(|fluid| (id, namespaced(fluid.name))))
            .collect(),
        "minecraft:block_entity_type" => (0..dynamic::block_entity_type_count())
            .filter_map(|id| dynamic::block_entity_type_name(id).map(|name| (id, namespaced(name))))
            .collect(),
        "minecraft:menu" => (0..dynamic::menu_type_count())
            .filter_map(|id| dynamic::menu_type_name(id).map(|name| (id, namespaced(name))))
            .collect(),
        _ => return None,
    };

    Some(encode_snapshot(registry, &entries))
}

fn encode_snapshot(registry: &str, entries: &[(u16, String)]) -> Result<Bytes, WritingError> {
    let mut buf = Vec::new();
    buf.write_string(registry)?;

    write_len(&mut buf, entries.len())?;
    for (id, name) in entries {
        buf.write_var_int(&VarInt(i32::from(*id)))?;
        buf.write_string(name)?;
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

/// Generated names carry no namespace; runtime-registered ones already do.
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

    fn channel(id: &str, protocol: ChannelProtocol) -> ModdedChannel {
        ModdedChannel {
            id: id.to_string(),
            protocol,
            version: BUILTIN_CHANNEL_VERSION.to_string(),
            serverbound: false,
        }
    }

    #[test]
    fn setup_groups_channels_by_protocol_phase() {
        let payload = modded_network_setup(&[
            channel("neoforge:frozen_registry", ChannelProtocol::Configuration),
            channel("examplemod:one", ChannelProtocol::Play),
            channel("examplemod:two", ChannelProtocol::Play),
        ])
        .expect("encodes");

        let mut cursor = 0;
        assert_eq!(read_var_int(&payload, &mut cursor), 2, "two phases");

        assert_eq!(
            read_var_int(&payload, &mut cursor),
            ChannelProtocol::Configuration.ordinal()
        );
        assert_eq!(read_var_int(&payload, &mut cursor), 1);
        assert_eq!(
            read_string(&payload, &mut cursor),
            "neoforge:frozen_registry"
        );
        assert_eq!(
            read_string(&payload, &mut cursor),
            "neoforge:frozen_registry"
        );
        assert_eq!(read_string(&payload, &mut cursor), BUILTIN_CHANNEL_VERSION);

        assert_eq!(
            read_var_int(&payload, &mut cursor),
            ChannelProtocol::Play.ordinal()
        );
        assert_eq!(read_var_int(&payload, &mut cursor), 2, "both play channels");

        for id in ["examplemod:one", "examplemod:two"] {
            assert_eq!(read_string(&payload, &mut cursor), id);
            assert_eq!(read_string(&payload, &mut cursor), id);
            assert_eq!(read_string(&payload, &mut cursor), BUILTIN_CHANNEL_VERSION);
        }

        assert_eq!(cursor, payload.len(), "no trailing bytes");
    }

    #[test]
    fn channel_registration_round_trips_through_the_vanilla_parser() {
        use crate::net::java::config::task::parse_channel_list;

        let channels = ["examplemod:one", "examplemod:two"];
        assert_eq!(
            parse_channel_list(&channel_registration(&channels)),
            channels.to_vec()
        );
    }

    #[test]
    fn sync_start_lists_every_registry() {
        let payload = sync_start(&SYNCED_REGISTRIES).expect("encodes");
        let mut cursor = 0;
        assert_eq!(
            read_var_int(&payload, &mut cursor),
            SYNCED_REGISTRIES.len() as i32
        );
        for registry in SYNCED_REGISTRIES {
            assert_eq!(read_string(&payload, &mut cursor), registry);
        }
        assert_eq!(cursor, payload.len(), "no trailing bytes");
    }

    /// A snapshot replaces the registry it names, so every entry has to be present and
    /// every name has to be a resolvable identifier.
    #[test]
    fn every_synced_registry_encodes_completely() {
        let expected: [(&str, u16); 6] = [
            ("minecraft:block", BlockId::count()),
            ("minecraft:item", dynamic::item_count()),
            ("minecraft:entity_type", dynamic::entity_type_count()),
            ("minecraft:fluid", dynamic::fluid_count()),
            (
                "minecraft:block_entity_type",
                dynamic::block_entity_type_count(),
            ),
            ("minecraft:menu", dynamic::menu_type_count()),
        ];

        for (registry, count) in expected {
            let payload = registry_snapshot(registry)
                .unwrap_or_else(|| panic!("{registry} should be describable"))
                .expect("encodes");
            let mut cursor = 0;

            assert_eq!(read_string(&payload, &mut cursor), registry);
            assert_eq!(
                read_var_int(&payload, &mut cursor),
                i32::from(count),
                "{registry} must be described completely"
            );

            for _ in 0..count {
                let _id = read_var_int(&payload, &mut cursor);
                let name = read_string(&payload, &mut cursor);
                assert!(name.contains(':'), "{name} should be namespaced");
            }

            assert_eq!(read_var_int(&payload, &mut cursor), 0, "no aliases");
            assert_eq!(cursor, payload.len(), "{registry} has trailing bytes");
        }
    }

    #[test]
    fn block_snapshot_starts_at_air() {
        let payload = registry_snapshot("minecraft:block")
            .expect("describable")
            .expect("encodes");
        let mut cursor = 0;
        let _registry = read_string(&payload, &mut cursor);
        let _count = read_var_int(&payload, &mut cursor);

        assert_eq!(read_var_int(&payload, &mut cursor), 0);
        assert_eq!(read_string(&payload, &mut cursor), "minecraft:air");
    }

    #[test]
    fn an_unknown_registry_is_not_described_rather_than_described_partially() {
        assert!(registry_snapshot("minecraft:recipe_type").is_none());
    }

    #[test]
    fn generated_names_gain_a_namespace_and_registered_ones_keep_theirs() {
        assert_eq!(namespaced("stone"), "minecraft:stone");
        assert_eq!(namespaced("examplemod:ruby"), "examplemod:ruby");
    }
}
