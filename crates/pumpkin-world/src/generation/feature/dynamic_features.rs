//! Placed features registered at runtime, parsed from a mod's worldgen JSON.
//!
//! The generated feature tables are a closed world: every placed feature is an enum
//! variant, every biome's feature list a static slice. A mod's ore cannot join them, so
//! it lives here instead — a parallel list the chunk populator consults after the static
//! ones, keyed by generation step and gated by a biome tag.
//!
//! Deliberately small: only `minecraft:ore` configured features and the placement
//! modifiers vanilla ore placement uses (`count`, `in_square`, `height_range`,
//! `rarity_filter`) are parsed. A feature this module cannot express is reported to the
//! caller, which says so out loud rather than half-placing it. The `minecraft:biome`
//! modifier is deliberately dropped from the placement chain: the static machinery
//! answers it by looking the feature up in the biome's own static lists, which cannot
//! contain a runtime feature — biome gating happens in the populator against
//! [`DynamicFeature::biome_tag_ids`] instead.

use std::sync::RwLock;

use pumpkin_data::Block;
use pumpkin_data::tag::{self, RegistryKey};
use pumpkin_util::math::int_provider::IntProvider;
use pumpkin_util::y_offset::{Absolute, YOffset};
use serde_json::Value;

use crate::generation::height_provider::HeightProvider;
use crate::generation::height_provider::UniformHeightProvider;
use crate::generation::rule::RuleTest;
use crate::generation::rule::tag_match::TagMatchRuleTest;

use super::configured_features::ConfiguredFeature;
use super::features::ore::{OreFeature, OreTarget};
use super::placed_features::{
    CountPlacementModifier, Feature, PlacedFeature, PlacementModifier,
    RarityFilterPlacementModifier,
};

/// One runtime feature: what to place, when, and where.
pub struct DynamicFeature {
    /// Namespaced name, for logs.
    pub name: String,
    /// Index into the generation step loop; ores run at `UNDERGROUND_ORES`.
    pub step: usize,
    /// Biome ids this feature generates in, resolved from the modifier's biome tag.
    pub biome_tag_ids: &'static [u16],
    pub placed: PlacedFeature,
}

/// The generation step vanilla runs ore features at.
///
/// The step loop indexes `biome.features`; this is `GenerationStep::UNDERGROUND_ORES`'
/// position in that list. Mod biome modifiers name their step in Java code this host
/// does not run, but an ore feature at any other step would be a modding curiosity —
/// vanilla and `NeoForge` convention alike put ores here.
pub const UNDERGROUND_ORES_STEP: usize = 6;

static DYNAMIC_FEATURES: RwLock<Vec<DynamicFeature>> = RwLock::new(Vec::new());

/// Replaces the installed dynamic features. Called when datapacks (re)load.
pub fn install_dynamic_features(features: Vec<DynamicFeature>) {
    *DYNAMIC_FEATURES
        .write()
        .unwrap_or_else(std::sync::PoisonError::into_inner) = features;
}

/// Runs `f` for each installed feature at `step` whose biome list intersects
/// `biomes_in_chunk`.
pub fn for_each_at_step(step: usize, biomes_in_chunk: &[u8], mut f: impl FnMut(&DynamicFeature)) {
    let features = DYNAMIC_FEATURES
        .read()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    for feature in features.iter() {
        if feature.step == step
            && biomes_in_chunk
                .iter()
                .any(|biome| feature.biome_tag_ids.contains(&u16::from(*biome)))
        {
            f(feature);
        }
    }
}

/// Builds a [`DynamicFeature`] from the three JSON documents a datapack ore consists of.
///
/// # Errors
///
/// Returns a human-readable reason when any of the three cannot be expressed — an
/// unsupported feature type, a placement modifier beyond the ore set, an unknown block
/// or tag. The caller reports it; nothing is half-built.
pub fn parse_dynamic_ore(
    name: &str,
    placed_json: &str,
    configured_json: &str,
    biome_tag: &str,
) -> Result<DynamicFeature, String> {
    let placed: Value =
        serde_json::from_str(placed_json).map_err(|err| format!("placed feature: {err}"))?;
    let configured: Value = serde_json::from_str(configured_json)
        .map_err(|err| format!("configured feature: {err}"))?;

    let biome_tag_ids =
        resolve_biome_tag(biome_tag).ok_or_else(|| format!("unknown biome tag {biome_tag}"))?;

    let placement = parse_placement(&placed)?;
    let ore = parse_ore_config(&configured)?;

    Ok(DynamicFeature {
        name: name.to_string(),
        step: UNDERGROUND_ORES_STEP,
        biome_tag_ids,
        placed: PlacedFeature {
            feature: Feature::Inlined(Box::new(ConfiguredFeature::Ore(ore))),
            placement,
        },
    })
}

/// The biome ids behind a `#tag` reference, trying the plain and `c:` spellings.
fn resolve_biome_tag(tag_name: &str) -> Option<&'static [u16]> {
    let bare = tag_name.trim_start_matches('#');
    let candidates = if bare.contains(':') {
        vec![bare.to_string()]
    } else {
        // NeoForge convention: an unprefixed tag in mod JSON usually means the `c:`
        // (common) namespace, and vanilla's spelling is the fallback.
        vec![format!("c:{bare}"), format!("minecraft:{bare}")]
    };
    for candidate in candidates {
        if let Some(ids) = tag::get_tag_ids(RegistryKey::WorldgenBiome, &candidate) {
            return Some(ids);
        }
    }
    None
}

fn parse_placement(placed: &Value) -> Result<Vec<PlacementModifier>, String> {
    let mut placement = Vec::new();
    let modifiers = placed
        .get("placement")
        .and_then(Value::as_array)
        .ok_or("placed feature has no placement list")?;
    for modifier in modifiers {
        let kind = modifier
            .get("type")
            .and_then(Value::as_str)
            .ok_or("placement modifier without a type")?;
        match kind.trim_start_matches("minecraft:") {
            "count" => {
                let count = modifier
                    .get("count")
                    .and_then(Value::as_i64)
                    .ok_or("count modifier without a constant count")?;
                placement.push(PlacementModifier::Count(CountPlacementModifier {
                    count: IntProvider::Constant(
                        i32::try_from(count)
                            .map_err(|_| format!("count {count} does not fit an i32"))?,
                    ),
                }));
            }
            "rarity_filter" => {
                let chance = modifier
                    .get("chance")
                    .and_then(Value::as_i64)
                    .ok_or("rarity_filter without a chance")?;
                placement.push(PlacementModifier::RarityFilter(
                    RarityFilterPlacementModifier {
                        chance: u32::try_from(chance)
                            .map_err(|_| format!("chance {chance} does not fit a u32"))?,
                    },
                ));
            }
            "in_square" => placement.push(PlacementModifier::InSquare(
                super::placed_features::SquarePlacementModifier,
            )),
            "height_range" => {
                placement.push(PlacementModifier::HeightRange(parse_height_range(
                    modifier,
                )?));
            }
            // Biome gating happens against the modifier's tag in the populator; the
            // static biome filter can only find features in the generated tables.
            "biome" => {}
            other => return Err(format!("unsupported placement modifier {other}")),
        }
    }
    Ok(placement)
}

fn parse_height_range(
    modifier: &Value,
) -> Result<crate::generation::feature::placed_features::HeightRangePlacementModifier, String> {
    let height = modifier
        .get("height")
        .ok_or("height_range without height")?;
    if height.get("type").and_then(Value::as_str) != Some("minecraft:uniform") {
        return Err("only uniform height providers are supported".into());
    }
    let bound = |key: &str| -> Result<YOffset, String> {
        let value = height
            .get(key)
            .ok_or_else(|| format!("uniform height without {key}"))?;
        let absolute = value
            .get("absolute")
            .and_then(Value::as_i64)
            .ok_or("only absolute height bounds are supported")?;
        Ok(YOffset::Absolute(Absolute {
            absolute: i16::try_from(absolute)
                .map_err(|_| format!("height {absolute} does not fit an i16"))?,
        }))
    };
    Ok(
        crate::generation::feature::placed_features::HeightRangePlacementModifier {
            height: HeightProvider::Uniform(UniformHeightProvider {
                min_inclusive: bound("min_inclusive")?,
                max_inclusive: bound("max_inclusive")?,
            }),
        },
    )
}

fn parse_ore_config(configured: &Value) -> Result<OreFeature, String> {
    let kind = configured.get("type").and_then(Value::as_str);
    if kind != Some("minecraft:ore") {
        // A mod's own feature type is a piece of the mod's Java code: its config may
        // look like an ore's and mean something else entirely. Reading it as an ore
        // would put blocks in the world the mod never asked for, so it is refused by
        // name instead.
        return Err(format!(
            "{} is the mod's own feature type, and what it places is decided by the \
             mod's own code; only minecraft:ore is placed here",
            kind.unwrap_or("a feature without a type")
        ));
    }
    let config = configured.get("config").ok_or("ore without config")?;
    let size = config
        .get("size")
        .and_then(Value::as_i64)
        .ok_or("ore without size")?;
    #[allow(clippy::cast_possible_truncation)]
    let discard = config
        .get("discard_chance_on_air_exposure")
        .and_then(Value::as_f64)
        .unwrap_or(0.0) as f32;

    let mut targets = Vec::new();
    let raw_targets = config
        .get("targets")
        .and_then(Value::as_array)
        .ok_or("ore without targets")?;
    for target in raw_targets {
        let state_name = target
            .pointer("/state/Name")
            .and_then(Value::as_str)
            .ok_or("ore target without a state name")?;
        let block = Block::from_name(state_name)
            .ok_or_else(|| format!("{state_name} is not a registered block"))?;

        let predicate = target.get("target").ok_or("ore target without a rule")?;
        let rule = match predicate
            .get("predicate_type")
            .and_then(Value::as_str)
            .map(|kind| kind.trim_start_matches("minecraft:"))
        {
            Some("tag_match") => {
                let tag_name = predicate
                    .get("tag")
                    .and_then(Value::as_str)
                    .ok_or("tag_match without a tag")?;
                let values = tag::get_tag_values(RegistryKey::Block, tag_name)
                    .ok_or_else(|| format!("unknown block tag {tag_name}"))?;
                let ids = tag::get_tag_ids(RegistryKey::Block, tag_name)
                    .ok_or_else(|| format!("unknown block tag {tag_name}"))?;
                RuleTest::TagMatch(TagMatchRuleTest { tag: (values, ids) })
            }
            Some("always_true") => RuleTest::AlwaysTrue,
            other => return Err(format!("unsupported ore rule {other:?}")),
        };

        targets.push(OreTarget {
            target: rule,
            state: block.default_state,
        });
    }

    Ok(OreFeature {
        size: i32::try_from(size).map_err(|_| format!("size {size} does not fit an i32"))?,
        discard_chance_on_air_exposure: discard,
        targets,
    })
}

#[cfg(test)]
mod tests {
    use super::{UNDERGROUND_ORES_STEP, parse_dynamic_ore};

    const PLACED: &str = r#"{
        "feature": "examplemod:ruby_ore",
        "placement": [
            {"type": "minecraft:count", "count": 12},
            {"type": "minecraft:in_square"},
            {"type": "minecraft:height_range", "height": {
                "type": "minecraft:uniform",
                "min_inclusive": {"absolute": -60},
                "max_inclusive": {"absolute": 24}
            }},
            {"type": "minecraft:biome"}
        ]
    }"#;

    const CONFIGURED: &str = r#"{
        "type": "minecraft:ore",
        "config": {
            "size": 8,
            "discard_chance_on_air_exposure": 0.0,
            "targets": [{
                "state": {"Name": "minecraft:iron_ore"},
                "target": {"predicate_type": "minecraft:tag_match",
                           "tag": "minecraft:stone_ore_replaceables"}
            }]
        }
    }"#;

    #[test]
    fn a_vanilla_shaped_ore_parses_whole() {
        let feature = parse_dynamic_ore("examplemod:ruby_ore", PLACED, CONFIGURED, "#is_overworld")
            .expect("parses");
        assert_eq!(feature.step, UNDERGROUND_ORES_STEP);
        assert!(!feature.biome_tag_ids.is_empty());
        // count, in_square, height_range survive; the biome modifier is dropped in
        // favour of the tag gate.
        assert_eq!(feature.placed.placement.len(), 3);
    }

    #[test]
    fn an_unsupported_feature_type_is_refused_with_a_reason() {
        let Err(err) = parse_dynamic_ore(
            "examplemod:tree",
            PLACED,
            r#"{"type": "minecraft:tree", "config": {}}"#,
            "#is_overworld",
        ) else {
            panic!("a tree should not parse as an ore");
        };
        assert!(err.contains("minecraft:ore"), "{err}");
    }

    #[test]
    fn an_unknown_biome_tag_is_refused() {
        let Err(err) = parse_dynamic_ore("examplemod:ruby_ore", PLACED, CONFIGURED, "#no_such_tag")
        else {
            panic!("an unknown biome tag should not parse");
        };
        assert!(err.contains("biome tag"), "{err}");
    }
}
