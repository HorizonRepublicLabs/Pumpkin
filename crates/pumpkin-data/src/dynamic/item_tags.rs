//! Item tags loaded at runtime, from mod datapacks.
//!
//! The generated tag tables cover vanilla; a mod's `#mysticalagriculture:essences` -- and
//! the members a mod adds to a `c:` tag -- live in datapack JSON. The datapack loader
//! installs them here, and ingredient matching asks [`item_tag_contains`] alongside the
//! generated tables.
//!
//! Entries are stored as written: an entry starting with `#` references another tag and
//! is resolved at query time, with a visited set breaking cycles.

use std::collections::{HashMap, HashSet};
use std::sync::RwLock;

static TAGS: RwLock<Option<HashMap<String, Vec<String>>>> = RwLock::new(None);

/// Replaces the installed tags. Called when datapacks (re)load.
pub fn install_item_tags(tags: HashMap<String, Vec<String>>) {
    *TAGS
        .write()
        .unwrap_or_else(std::sync::PoisonError::into_inner) = Some(tags);
}

/// Whether `item` (namespaced) is a member of `tag` (namespaced, no leading `#`).
#[must_use]
pub fn item_tag_contains(tag: &str, item: &str) -> bool {
    let guard = TAGS
        .read()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let Some(tags) = guard.as_ref() else {
        return false;
    };
    let mut visited = HashSet::new();
    contains(tags, tag, item, &mut visited)
}

fn contains<'a>(
    tags: &'a HashMap<String, Vec<String>>,
    tag: &'a str,
    item: &str,
    visited: &mut HashSet<&'a str>,
) -> bool {
    if !visited.insert(tag) {
        return false;
    }
    let Some(entries) = tags.get(tag) else {
        return false;
    };
    entries.iter().any(|entry| {
        entry.strip_prefix('#').map_or(entry == item, |nested| {
            contains(tags, nested, item, visited)
        })
    })
}

#[cfg(test)]
mod tests {
    use std::collections::HashMap;

    use super::{install_item_tags, item_tag_contains};

    #[test]
    fn membership_follows_nested_references_and_survives_cycles() {
        let mut tags = HashMap::new();
        tags.insert(
            "examplemod:gems".to_string(),
            vec![
                "examplemod:ruby".to_string(),
                "#examplemod:rare".to_string(),
            ],
        );
        tags.insert(
            "examplemod:rare".to_string(),
            // A cycle back to gems, plus a real member.
            vec![
                "#examplemod:gems".to_string(),
                "examplemod:diamond".to_string(),
            ],
        );
        install_item_tags(tags);

        assert!(item_tag_contains("examplemod:gems", "examplemod:ruby"));
        assert!(item_tag_contains("examplemod:gems", "examplemod:diamond"));
        assert!(!item_tag_contains("examplemod:gems", "examplemod:coal"));
        assert!(!item_tag_contains("examplemod:unknown", "examplemod:ruby"));
    }
}
