//! Tags a mod's datapack declares, in the form a client can be told about them.
//!
//! The generated tables cover vanilla's tags. A mod brings its own — `#mekanism:atomic_
//! disassembler_ore`, `#mysticalagriculture:essences` — and the client needs to be told
//! they *exist*: an item whose data components resolve a tag key (Mekanism's atomic
//! disassembler resolves `mekanism:incorrect_for_disassembler`) throws
//! `Missing tag TagKey[...]` and drops the connection when the server never mentions it.
//!
//! # What is expressible, and what is not
//!
//! Tag contents go over the wire as numeric registry ids. A client that has the mods
//! installed numbers mod content by *its own* registry order, which is not this server's
//! dynamic order, so a mod-namespaced member cannot honestly be named to it — sending our
//! id would name a different block on the other side. Those members are dropped, and the
//! tag is sent with whatever vanilla members it has (often none). The tag exists, its
//! vanilla contents are true, and its mod contents are missing rather than wrong.
//!
//! Registries whose ids this module cannot resolve at all (damage types, game events)
//! yield their tags with empty contents, for the same reason: existence is what the
//! client needs.

use std::collections::{HashMap, HashSet};
use std::sync::RwLock;

use pumpkin_util::version::JavaMinecraftVersion;

use crate::tag::{RegistryKey, get_registry_key_tags};

/// Registry identifier (`block`, `item`, ...) -> tag name -> entries, as written in the
/// JSON: a leading `#` means a reference to another tag, resolved at query time.
pub type ModTags = HashMap<String, HashMap<String, Vec<String>>>;

static TAGS: RwLock<Option<ModTags>> = RwLock::new(None);

/// Replaces the installed tags. Called when datapacks (re)load.
pub fn install_mod_tags(tags: ModTags) {
    *TAGS
        .write()
        .unwrap_or_else(std::sync::PoisonError::into_inner) = Some(tags);
}

/// The mod tags for `key` that the client does not already have, each with the members
/// this server can name over the wire.
///
/// A tag the generated tables already carry is left out: a mod adding to a vanilla tag
/// adds mod content, which is exactly what cannot be expressed, so re-sending the tag
/// would only risk replacing a correct list with the same list.
#[must_use]
pub fn network_tag_entries(
    key: RegistryKey,
    version: JavaMinecraftVersion,
) -> Vec<(String, Vec<u16>)> {
    let guard = TAGS
        .read()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let Some(map) = guard.as_ref().and_then(|all| all.get(key.identifier_string())) else {
        return Vec::new();
    };
    let vanilla = get_registry_key_tags(version, key);
    let mut entries: Vec<(String, Vec<u16>)> = map
        .keys()
        .filter(|name| !vanilla.is_some_and(|v| v.contains_key(name.as_str())))
        .map(|name| {
            let mut ids = Vec::new();
            let mut visited = HashSet::new();
            collect_ids(map, name, key, version, &mut ids, &mut visited);
            ids.sort_unstable();
            ids.dedup();
            (name.clone(), ids)
        })
        .collect();
    // The wire format is a list, and a stable order keeps two boots of the same server
    // sending the same bytes.
    entries.sort_by(|a, b| a.0.cmp(&b.0));
    entries
}

fn collect_ids<'a>(
    tags: &'a HashMap<String, Vec<String>>,
    tag: &'a str,
    key: RegistryKey,
    version: JavaMinecraftVersion,
    ids: &mut Vec<u16>,
    visited: &mut HashSet<&'a str>,
) {
    if !visited.insert(tag) {
        return;
    }
    let Some(entries) = tags.get(tag) else {
        return;
    };
    for entry in entries {
        if let Some(nested) = entry.strip_prefix('#') {
            // A reference into vanilla resolves against the generated table; one into
            // another mod tag recurses here.
            // Tag names in the generated table are namespaced, as they are on the wire.
            let qualified = if nested.contains(':') {
                nested.to_string()
            } else {
                format!("minecraft:{nested}")
            };
            if let Some(vanilla) =
                get_registry_key_tags(version, key).and_then(|v| v.get(qualified.as_str()))
            {
                ids.extend_from_slice(vanilla.1);
            } else {
                collect_ids(tags, nested, key, version, ids, visited);
            }
        } else if let Some(id) = vanilla_member_id(key, entry) {
            ids.push(id);
        }
    }
}

/// The id of a vanilla member, or `None` for anything the client would number differently
/// — which is every mod-namespaced entry, deliberately.
fn vanilla_member_id(key: RegistryKey, entry: &str) -> Option<u16> {
    let name = match entry.split_once(':') {
        Some(("minecraft", path)) => path,
        Some(_) => return None,
        None => entry,
    };
    match key {
        #[cfg(feature = "block")]
        RegistryKey::Block => crate::Block::from_name(name).map(|block| block.id.as_u16()),
        #[cfg(feature = "item")]
        RegistryKey::Item => crate::item::Item::from_registry_key(name).map(|item| item.id),
        #[cfg(feature = "fluid")]
        RegistryKey::Fluid => crate::fluid::Fluid::from_registry_key(name).map(|fluid| fluid.id),
        #[cfg(feature = "entity_type")]
        RegistryKey::EntityType => {
            crate::entity::EntityType::from_name(name).map(|entity| entity.id)
        }
        _ => None,
    }
}

#[cfg(test)]
mod tests {
    use std::collections::HashMap;

    use pumpkin_util::version::JavaMinecraftVersion;

    use super::{install_mod_tags, network_tag_entries};
    use crate::tag::RegistryKey;

    // One test, deliberately: the store is process-wide, and two tests installing into
    // it in parallel would race.
    #[test]
    fn mod_tags_keep_vanilla_members_drop_the_rest_and_leave_vanilla_tags_alone() {
        let mut block = HashMap::new();
        block.insert(
            "examplemod:drills".to_string(),
            vec![
                "minecraft:stone".to_string(),
                "examplemod:ore".to_string(),
                "#examplemod:more".to_string(),
            ],
        );
        block.insert(
            "examplemod:more".to_string(),
            // A cycle back, plus one more vanilla member.
            vec![
                "#examplemod:drills".to_string(),
                "minecraft:dirt".to_string(),
            ],
        );
        block.insert(
            "minecraft:mineable/pickaxe".to_string(),
            vec!["examplemod:ore".to_string()],
        );
        let mut tags = HashMap::new();
        tags.insert("block".to_string(), block);
        install_mod_tags(tags);

        let entries = network_tag_entries(RegistryKey::Block, JavaMinecraftVersion::V_26_2);

        let drills = entries
            .iter()
            .find(|(name, _)| name == "examplemod:drills")
            .expect("the mod's own tag is sent");
        let stone = crate::Block::from_name("stone").unwrap().id.as_u16();
        let dirt = crate::Block::from_name("dirt").unwrap().id.as_u16();
        let mut expected = vec![stone, dirt];
        expected.sort_unstable();
        assert_eq!(drills.1, expected);

        // A vanilla tag the mod adds to is left to the generated table.
        assert!(
            !entries
                .iter()
                .any(|(name, _)| name == "minecraft:mineable/pickaxe")
        );
    }
}
