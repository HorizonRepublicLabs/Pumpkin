//! Minimal block loot-table reading for JVM-registered blocks.
//!
//! A mod ships a loot table per block; Pumpkin's plugin blocks carry a flat list of
//! [`BlockDrop`]s. This module reads the former into the latter, keeping exactly the part
//! a plain, tool-in-hand break can honour and counting what it cannot:
//!
//! * `minecraft:item` entries become drops, with `minecraft:set_count` bounds when the
//!   entry carries them.
//! * `minecraft:alternatives` takes the first child without conditions — the fallback a
//!   vanilla evaluation lands on when no silk-touch or fortune predicate matches. A
//!   conditional child (the silk-touch branch) is deliberately not modelled: plugin
//!   drops have no notion of the breaking tool yet.
//! * Everything else — nested tables, dynamic entries, pool conditions beyond
//!   `survives_explosion` — is skipped and counted, so the caller can say out loud how
//!   much of the table it kept.
//!
//! Pool `rolls` are treated as one: every mod table seen so far rolls once, and silently
//! multiplying drops would be worse than dropping the extra rolls loudly.

use serde_json::Value;

/// One drop read out of a loot table: an item name and its count bounds.
#[derive(Debug, PartialEq, Eq)]
pub struct ParsedDrop {
    /// Namespaced item id, e.g. `mysticalagriculture:inferium_essence`.
    pub item: String,
    /// Fewest dropped.
    pub min: u8,
    /// Most dropped, inclusive.
    pub max: u8,
}

/// What a table parse produced: the drops kept and the entries it could not model.
#[derive(Debug, Default)]
pub struct ParsedTable {
    pub drops: Vec<ParsedDrop>,
    /// Entries whose shape this parser does not model (nested tables, exotic types).
    pub skipped_entries: usize,
}

/// Reads a block loot table's JSON into the drops a plain break yields.
///
/// Returns `None` when the input is not JSON at all; a table that parses but contains
/// nothing modellable comes back with empty `drops` and a nonzero `skipped_entries`.
#[must_use]
pub fn parse_block_loot_table(json: &str) -> Option<ParsedTable> {
    let value: Value = serde_json::from_str(json).ok()?;
    let mut result = ParsedTable::default();

    let Some(pools) = value.get("pools").and_then(Value::as_array) else {
        return Some(result);
    };
    for pool in pools {
        let Some(entries) = pool.get("entries").and_then(Value::as_array) else {
            continue;
        };
        for entry in entries {
            parse_entry(entry, &mut result);
        }
    }
    Some(result)
}

fn parse_entry(entry: &Value, result: &mut ParsedTable) {
    match entry.get("type").and_then(Value::as_str) {
        Some("minecraft:item" | "item") => {
            let Some(name) = entry.get("name").and_then(Value::as_str) else {
                result.skipped_entries += 1;
                return;
            };
            let (min, max) = count_bounds(entry);
            result.drops.push(ParsedDrop {
                item: name.to_string(),
                min,
                max,
            });
        }
        Some("minecraft:alternatives" | "alternatives") => {
            // The unconditional child is the branch a plain break lands on. The
            // conditional siblings (silk touch, shears) are skipped and counted -- the
            // drop model has no tool to test them against.
            let Some(children) = entry.get("children").and_then(Value::as_array) else {
                result.skipped_entries += 1;
                return;
            };
            let fallback = children
                .iter()
                .find(|child| child.get("conditions").is_none());
            match fallback {
                Some(child) => {
                    result.skipped_entries += children.len() - 1;
                    parse_entry(child, result);
                }
                None => result.skipped_entries += children.len(),
            }
        }
        _ => result.skipped_entries += 1,
    }
}

/// The `minecraft:set_count` bounds on an entry, or 1..=1 without one.
fn count_bounds(entry: &Value) -> (u8, u8) {
    let Some(functions) = entry.get("functions").and_then(Value::as_array) else {
        return (1, 1);
    };
    for function in functions {
        if function.get("function").and_then(Value::as_str) != Some("minecraft:set_count") {
            continue;
        }
        let Some(count) = function.get("count") else {
            continue;
        };
        if let Some(fixed) = count.as_f64() {
            let fixed = clamp_count(fixed);
            return (fixed, fixed);
        }
        let min = count.get("min").and_then(Value::as_f64).map(clamp_count);
        let max = count.get("max").and_then(Value::as_f64).map(clamp_count);
        if let (Some(min), Some(max)) = (min, max) {
            return (min, max.max(min));
        }
    }
    (1, 1)
}

#[allow(clippy::cast_possible_truncation, clippy::cast_sign_loss)]
const fn clamp_count(count: f64) -> u8 {
    count.clamp(0.0, 255.0) as u8
}

#[cfg(test)]
mod tests {
    use super::{ParsedDrop, parse_block_loot_table};

    #[test]
    fn a_plain_drop_itself_table_yields_one_drop() {
        let table = r#"{
            "type": "minecraft:block",
            "pools": [{
                "rolls": 1,
                "entries": [{"type": "minecraft:item", "name": "examplemod:ruby_block"}],
                "conditions": [{"condition": "minecraft:survives_explosion"}]
            }]
        }"#;
        let parsed = parse_block_loot_table(table).unwrap();
        assert_eq!(
            parsed.drops,
            vec![ParsedDrop {
                item: "examplemod:ruby_block".into(),
                min: 1,
                max: 1
            }]
        );
        assert_eq!(parsed.skipped_entries, 0);
    }

    #[test]
    fn alternatives_take_the_unconditional_fallback() {
        let table = r#"{
            "pools": [{
                "entries": [{
                    "type": "minecraft:alternatives",
                    "children": [
                        {"type": "minecraft:item", "name": "examplemod:ore",
                         "conditions": [{"condition": "minecraft:match_tool"}]},
                        {"type": "minecraft:item", "name": "examplemod:essence",
                         "functions": [{"function": "minecraft:set_count",
                                        "count": {"min": 2.0, "max": 4.0}}]}
                    ]
                }]
            }]
        }"#;
        let parsed = parse_block_loot_table(table).unwrap();
        assert_eq!(
            parsed.drops,
            vec![ParsedDrop {
                item: "examplemod:essence".into(),
                min: 2,
                max: 4
            }]
        );
        // The silk-touch branch is the one skipped.
        assert_eq!(parsed.skipped_entries, 1);
    }

    #[test]
    fn an_unmodellable_entry_is_counted_not_invented() {
        let table = r#"{
            "pools": [{
                "entries": [{"type": "minecraft:loot_table", "value": "examplemod:other"}]
            }]
        }"#;
        let parsed = parse_block_loot_table(table).unwrap();
        assert!(parsed.drops.is_empty());
        assert_eq!(parsed.skipped_entries, 1);
    }

    #[test]
    fn garbage_is_none_an_empty_table_is_empty() {
        assert!(parse_block_loot_table("not json").is_none());
        let parsed = parse_block_loot_table("{}").unwrap();
        assert!(parsed.drops.is_empty());
        assert_eq!(parsed.skipped_entries, 0);
    }
}
