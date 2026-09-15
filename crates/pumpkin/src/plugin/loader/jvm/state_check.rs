//! Checks a mod block's registered states against the model its own jar ships.
//!
//! Pumpkin numbers a registered block's states itself, from the properties the shim
//! reported, and the client numbers the same block's states from the mod's real code.
//! Nothing reconciles the two: the `NeoForge` registry sync carries block ids, not block
//! state ids. So a block Pumpkin gives fewer states than it really has shifts the id of
//! every block registered after it, and the client draws some other mod's block in its
//! place.
//!
//! A mod's `assets/<ns>/blockstates/<path>.json` names the property values its model
//! distinguishes, and it comes from the jar rather than from the shim, so it is the one
//! description of a block's states this server has that it did not derive itself.

use std::collections::{BTreeMap, BTreeSet};
use std::path::Path;

/// The property values a block's model distinguishes, keyed by property name.
pub type ModelProperties = BTreeMap<String, BTreeSet<String>>;

/// Reads the properties a blockstate model distinguishes.
///
/// `None` for a multipart model: it selects parts by condition rather than naming a
/// complete variant per state, so it says nothing about how many states the block has.
#[must_use]
pub fn model_properties(json: &str) -> Option<ModelProperties> {
    let value: serde_json::Value = serde_json::from_str(json).ok()?;
    let variants = value.get("variants")?.as_object()?;

    let mut model = ModelProperties::new();
    for key in variants.keys() {
        for pair in key.split(',') {
            // The one unkeyed variant a property-less block ships splits into nothing.
            let Some((name, value)) = pair.split_once('=') else {
                continue;
            };
            model
                .entry(name.to_string())
                .or_default()
                .insert(value.to_string());
        }
    }
    Some(model)
}

/// A way a block's model and its registration disagree about the block's states.
///
/// Only ever the direction that means states are missing. A property the block was
/// registered with and the model does not name is not reported: a property the model
/// draws the same way either way -- `waterlogged` on most blocks -- is legitimately
/// absent from the variant keys.
#[derive(Debug, PartialEq, Eq)]
pub enum Disagreement {
    /// The model tells states apart by a property the block does not have.
    MissingProperty { name: String },
    /// A registered property is missing values the model names.
    MissingValues { name: String, values: Vec<String> },
}

/// Every way the model names states the registration cannot represent.
#[must_use]
pub fn disagreements(
    model: &ModelProperties,
    declared: &[(String, Vec<String>)],
) -> Vec<Disagreement> {
    let mut found = Vec::new();
    for (name, drawn) in model {
        let Some((_, registered)) = declared.iter().find(|(declared, _)| declared == name) else {
            found.push(Disagreement::MissingProperty { name: name.clone() });
            continue;
        };

        let missing: Vec<String> = drawn
            .iter()
            .filter(|value| !registered.contains(value))
            .cloned()
            .collect();
        if !missing.is_empty() {
            found.push(Disagreement::MissingValues {
                name: name.clone(),
                values: missing,
            });
        }
    }
    found
}

/// A block's model JSON, searched across every extracted mod datapack.
#[must_use]
pub fn find_block_model(datapacks: &Path, block_name: &str) -> Option<String> {
    let (namespace, path) = block_name.split_once(':')?;
    for entry in std::fs::read_dir(datapacks).ok()?.flatten() {
        if !entry.file_name().to_string_lossy().starts_with("mod_") {
            continue;
        }
        let candidate = entry
            .path()
            .join("assets")
            .join(namespace)
            .join("blockstates")
            .join(format!("{path}.json"));
        if let Ok(json) = std::fs::read_to_string(&candidate) {
            return Some(json);
        }
    }
    None
}

/// Checks every registered mod block against the model its jar ships, and says so.
///
/// Reports rather than refuses: a disagreement means some block's states are wrong, but
/// the server still runs, and an operator who cannot start at all is worse off than one
/// told which block to look at. Blocks whose jar ships no model, or a multipart one, are
/// counted as unchecked rather than as agreeing.
pub fn report(datapacks: &Path) {
    use pumpkin_data::{Block, BlockId};

    let mut checked = 0usize;
    let mut unchecked = 0usize;
    let mut disagreeing = 0usize;

    for raw in BlockId::BASE_COUNT..BlockId::count() {
        let Some(id) = BlockId::new(raw) else {
            continue;
        };
        let name = Block::from_id(id).name;
        let Some(declared) = pumpkin_data::dynamic::block_properties(id) else {
            continue;
        };

        let Some(model) = find_block_model(datapacks, name)
            .as_deref()
            .and_then(model_properties)
        else {
            unchecked += 1;
            continue;
        };
        checked += 1;

        let found = disagreements(&model, declared);
        if found.is_empty() {
            continue;
        }
        disagreeing += 1;
        for disagreement in found {
            match disagreement {
                Disagreement::MissingProperty { name: property } => tracing::warn!(
                    "{name}: its model tells states apart by `{property}`, which the block \
                     was not registered with, so every block registered after it is \
                     numbered wrong for the client"
                ),
                Disagreement::MissingValues {
                    name: property,
                    values,
                } => tracing::warn!(
                    "{name}: its model draws `{property}` as {values:?}, values the \
                     registered property does not have"
                ),
            }
        }
    }

    if checked > 0 || unchecked > 0 {
        tracing::info!(
            "Mod block states: {checked} block(s) checked against their models, \
             {disagreeing} disagree; {unchecked} had no variants model to check"
        );
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn set<const N: usize>(values: [&str; N]) -> BTreeSet<String> {
        values.iter().map(|value| (*value).to_string()).collect()
    }

    #[test]
    fn variant_keys_become_the_properties_the_model_distinguishes() {
        let json = r#"{"variants": {
            "facing=north,active=false": {"model": "mekanism:block/a"},
            "facing=south,active=true": {"model": "mekanism:block/b"}
        }}"#;

        let model = model_properties(json).expect("a variants model");

        assert_eq!(model["facing"], set(["north", "south"]));
        assert_eq!(model["active"], set(["false", "true"]));
    }

    #[test]
    fn a_block_with_one_unkeyed_variant_distinguishes_no_properties() {
        // What a block with no properties at all ships. It has one state, and the model
        // names no property to tell states apart -- which is not the same as unreadable.
        let json = r#"{"variants": {"": {"model": "mekanism:block/plain"}}}"#;

        let model = model_properties(json).expect("a variants model");

        assert!(model.is_empty());
    }

    fn declared<const N: usize>(properties: [(&str, &[&str]); N]) -> Vec<(String, Vec<String>)> {
        properties
            .iter()
            .map(|(name, values)| {
                (
                    (*name).to_string(),
                    values.iter().map(|value| (*value).to_string()).collect(),
                )
            })
            .collect()
    }

    #[test]
    fn a_property_the_model_names_and_the_block_lacks_is_reported() {
        // The shape that shifts every later block's state ids: the model tells eight
        // states apart, the block was registered with two.
        let model = ModelProperties::from([
            (
                "facing".to_string(),
                set(["north", "south", "east", "west"]),
            ),
            ("active".to_string(), set(["false", "true"])),
        ]);

        let found = disagreements(&model, &declared([("active", &["false", "true"][..])]));

        assert_eq!(
            found,
            vec![Disagreement::MissingProperty {
                name: "facing".to_string()
            }]
        );
    }

    #[test]
    fn values_a_registered_property_is_missing_are_reported() {
        // Half a property is as bad as none of it: four facings drawn, two registered.
        let model = ModelProperties::from([(
            "facing".to_string(),
            set(["north", "south", "east", "west"]),
        )]);

        let found = disagreements(&model, &declared([("facing", &["north", "south"][..])]));

        assert_eq!(
            found,
            vec![Disagreement::MissingValues {
                name: "facing".to_string(),
                values: vec!["east".to_string(), "west".to_string()],
            }]
        );
    }

    #[test]
    fn a_property_the_model_does_not_draw_is_not_a_disagreement() {
        // `waterlogged` changes no model, so it is absent from the variant keys of most
        // blocks that have it. Reporting that would drown the real findings in noise.
        let model = ModelProperties::from([("facing".to_string(), set(["north"]))]);

        let found = disagreements(
            &model,
            &declared([
                ("facing", &["north"][..]),
                ("waterlogged", &["false", "true"][..]),
            ]),
        );

        assert_eq!(found, vec![]);
    }

    #[test]
    fn a_block_model_is_found_in_whichever_mod_datapack_holds_it() {
        let root = std::env::temp_dir().join(format!(
            "pumpkin-state-check-{}-{:?}",
            std::process::id(),
            std::thread::current().id()
        ));
        let blockstates = root
            .join("mod_mekanism")
            .join("assets")
            .join("mekanism")
            .join("blockstates");
        std::fs::create_dir_all(&blockstates).unwrap();
        std::fs::write(
            blockstates.join("elite_enriching_factory.json"),
            r#"{"variants": {"facing=north": {}}}"#,
        )
        .unwrap();

        let found = find_block_model(&root, "mekanism:elite_enriching_factory");
        let missing = find_block_model(&root, "mekanism:no_such_block");

        std::fs::remove_dir_all(&root).unwrap();
        assert!(found.is_some_and(|json| json.contains("facing=north")));
        assert_eq!(missing, None);
    }

    #[test]
    fn a_multipart_model_names_no_variants_to_check() {
        let json = r#"{"multipart": [{"apply": {"model": "mekanism:block/cable"}}]}"#;

        assert_eq!(model_properties(json), None);
    }
}
