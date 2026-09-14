use std::io::Write;

use crate::packet::MultiVersionJavaPacket;
use crate::{ClientPacket, WritingError, ser::NetworkWriteExt};

use crate::codec::var_int::VarInt;
use pumpkin_data::{
    packet::clientbound::play::UPDATE_TAGS,
    tag::{RegistryKey, get_registry_key_tags},
};
use pumpkin_util::version::JavaMinecraftVersion;

pub struct CUpdateTagsPlay<'a> {
    pub tags: &'a [pumpkin_data::tag::RegistryKey],
}

impl MultiVersionJavaPacket for CUpdateTagsPlay<'_> {
    fn to_id(version: JavaMinecraftVersion) -> i32 {
        let id = UPDATE_TAGS.to_id(version);
        if id != -1 {
            return id;
        }
        #[allow(clippy::match_same_arms)]
        match version {
            JavaMinecraftVersion::V_1_20_5 => 0x78,
            JavaMinecraftVersion::V_1_20_3 => 0x74,
            JavaMinecraftVersion::V_1_20_2 => 0x70,
            JavaMinecraftVersion::V_1_20 | JavaMinecraftVersion::V_1_19_4 => 0x6E,
            JavaMinecraftVersion::V_1_19_3 => 0x6A,
            JavaMinecraftVersion::V_1_19_1 => 0x6B,
            JavaMinecraftVersion::V_1_19 => 0x68,
            JavaMinecraftVersion::V_1_18_2 | JavaMinecraftVersion::V_1_18 => 0x67,
            JavaMinecraftVersion::V_1_17_1 | JavaMinecraftVersion::V_1_17 => 0x66,
            JavaMinecraftVersion::V_1_16_4
            | JavaMinecraftVersion::V_1_16_3
            | JavaMinecraftVersion::V_1_16_2
            | JavaMinecraftVersion::V_1_16_1
            | JavaMinecraftVersion::V_1_16
            | JavaMinecraftVersion::V_1_14_4
            | JavaMinecraftVersion::V_1_14_3
            | JavaMinecraftVersion::V_1_14_2
            | JavaMinecraftVersion::V_1_14_1
            | JavaMinecraftVersion::V_1_14 => 0x5B,
            JavaMinecraftVersion::V_1_15_2
            | JavaMinecraftVersion::V_1_15_1
            | JavaMinecraftVersion::V_1_15 => 0x5C,
            JavaMinecraftVersion::V_1_13_2
            | JavaMinecraftVersion::V_1_13_1
            | JavaMinecraftVersion::V_1_13 => 0x55,
            _ => -1,
        }
    }
}

impl<'a> CUpdateTagsPlay<'a> {
    #[must_use]
    pub const fn new(tags: &'a [RegistryKey]) -> Self {
        Self { tags }
    }
}

fn remap_tag_entry_id(key: RegistryKey, id: u16, version: JavaMinecraftVersion) -> u16 {
    match key {
        RegistryKey::Item => pumpkin_data::item_id_remap::remap_item_id_for_version(id, version),
        RegistryKey::EntityType => {
            pumpkin_data::entity_id_remap::remap_entity_id_for_version(id, version)
        }
        _ => id,
    }
}

/// Writes one registry's tags: the generated ones, then any a mod's datapack declared.
///
/// A mod tag's mod-namespaced members are dropped by
/// [`network_tag_entries`](pumpkin_data::dynamic::network_tag_entries) -- ids for mod
/// content mean something else on a client that has the mods -- but the tag itself must
/// be sent: a client whose items resolve a tag key disconnects with `Missing tag TagKey`
/// when the server never mentions it.
fn write_registry_tags(
    mut write: impl Write,
    registry_key: RegistryKey,
    version: JavaMinecraftVersion,
) -> Result<(), WritingError> {
    let vanilla = get_registry_key_tags(version, registry_key);
    let mod_tags = pumpkin_data::dynamic::network_tag_entries(registry_key, version);
    let count = vanilla.map_or(0, |tags| tags.len()) + mod_tags.len();
    write.write_var_int(&count.try_into().map_err(|_| {
        WritingError::Message(format!("{count} isn't representable as a VarInt"))
    })?)?;

    if let Some(vanilla) = vanilla {
        for (name, tag) in vanilla.entries() {
            // This is technically a `ResourceLocation` but same thing
            write.write_string_bounded(name, u16::MAX as usize)?;
            let remapped_ids: Vec<u16> = tag
                .1
                .iter()
                .map(|&id| remap_tag_entry_id(registry_key, id, version))
                .collect();
            write.write_list(&remapped_ids, |p, id| p.write_var_int(&VarInt::from(*id)))?;
        }
    }
    for (name, ids) in &mod_tags {
        write.write_string_bounded(name, u16::MAX as usize)?;
        let remapped_ids: Vec<u16> = ids
            .iter()
            .map(|&id| remap_tag_entry_id(registry_key, id, version))
            .collect();
        write.write_list(&remapped_ids, |p, id| p.write_var_int(&VarInt::from(*id)))?;
    }
    Ok(())
}

impl ClientPacket for CUpdateTagsPlay<'_> {
    fn write_packet_data(
        &self,
        mut write: impl Write,
        version: &JavaMinecraftVersion,
    ) -> Result<(), WritingError> {
        if version < &JavaMinecraftVersion::V_1_13 {
            return Ok(());
        }

        if version < &JavaMinecraftVersion::V_1_17 {
            // 1.13 to 1.16.5 fixed category format without registry identifier strings:
            // 1.13 - 1.13.2: 3 categories (Block, Item, Fluid)
            // 1.14 - 1.16.5: 4 categories (Block, Item, Fluid, EntityType)
            let categories = if version < &JavaMinecraftVersion::V_1_14 {
                &[RegistryKey::Block, RegistryKey::Item, RegistryKey::Fluid][..]
            } else {
                &[
                    RegistryKey::Block,
                    RegistryKey::Item,
                    RegistryKey::Fluid,
                    RegistryKey::EntityType,
                ][..]
            };

            for &key in categories {
                write_registry_tags(&mut write, key, *version)?;
            }
            return Ok(());
        }

        let valid_keys: Vec<_> = self
            .tags
            .iter()
            .copied()
            .filter(|key| key.is_valid_for_version(*version))
            .collect();

        write.write_list(&valid_keys, |p, &registry_key| {
            p.write_string(&format!("minecraft:{}", registry_key.identifier_string()))?;
            write_registry_tags(p, registry_key, *version)
        })
    }
}
