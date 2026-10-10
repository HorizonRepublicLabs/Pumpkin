use std::io::Write;

use crate::{ClientPacket, WritingError, ser::NetworkWriteExt};

use crate::codec::var_int::VarInt;
use pumpkin_data::{
    packet::clientbound::config::UPDATE_TAGS,
    tag::{RegistryKey, get_registry_key_tags},
};
use pumpkin_macros::java_packet;
use pumpkin_util::version::JavaMinecraftVersion;

#[java_packet(UPDATE_TAGS)]
pub struct CUpdateTags<'a> {
    pub tags: &'a [pumpkin_data::tag::RegistryKey],
}

impl<'a> CUpdateTags<'a> {
    #[must_use]
    pub const fn new(tags: &'a [RegistryKey]) -> Self {
        Self { tags }
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
    // `phf` is not a direct dependency here, so the map's `len` cannot be named.
    #[allow(clippy::redundant_closure_for_method_calls)]
    let count = vanilla.map_or(0, |tags| tags.len()) + mod_tags.len();
    write.write_var_int(&count.try_into().map_err(|_| {
        WritingError::Message(format!("{count} isn't representable as a VarInt"))
    })?)?;

    if let Some(vanilla) = vanilla {
        for (name, tag) in vanilla.entries() {
            // This is technically a `ResourceLocation` but same thing
            write.write_string_bounded(name, u16::MAX as usize)?;
            write.write_list(tag.1, |p, &id| p.write_var_int(&VarInt::from(id)))?;
        }
    }
    for (name, ids) in &mod_tags {
        write.write_string_bounded(name, u16::MAX as usize)?;
        write.write_list(ids, |p, &id| p.write_var_int(&VarInt::from(id)))?;
    }
    Ok(())
}

impl ClientPacket for CUpdateTags<'_> {
    fn write_packet_data(
        &self,
        mut write: impl Write,
        version: &JavaMinecraftVersion,
    ) -> Result<(), WritingError> {
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
