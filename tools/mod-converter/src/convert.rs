//! Turns a mod jar's content into what Pumpkin needs to serve it.
//!
//! A mod is data plus code. The data — block states, item models, tags, loot tables,
//! recipes, worldgen — says what exists. The code says what it does, and is not converted:
//! every block becomes a copy of a vanilla template that looks like it should behave the
//! same way (an ore mines like an ore, a slab places like a slab). The report says, per
//! block and item, which guesses were made, so an operator can tell which content will
//! look right but act plain.
//!
//! Output is a content manifest the `mod-content` plugin registers from, and a datapack
//! of the jar's `data/` tree.

use std::collections::{BTreeMap, BTreeSet};

use serde_json::{Value, json};

/// Every file in the jar, by path.
pub type Jar = BTreeMap<String, Vec<u8>>;

/// What one jar converts to.
pub struct Conversion {
    pub mod_id: String,
    /// The manifest the `mod-content` plugin registers from.
    pub manifest: Value,
    /// Datapack files, by path inside the pack.
    pub datapack: Vec<(String, Vec<u8>)>,
    /// One line per guess or loss, for the operator.
    pub report: Vec<String>,
}

/// Converts one mod jar.
///
/// # Errors
/// If the jar has no `neoforge.mods.toml` naming a mod.
pub fn convert(jar: &Jar, dump: Option<&Value>) -> Result<Conversion, String> {
    let mod_id = mod_id(jar)?;
    let mut report = Vec::new();
    let tags = BlockTags::read(jar);

    let content = match dump {
        Some(dump) => from_dump(jar, &mod_id, dump, &tags, &mut report),
        None => from_files(jar, &tags, &mut report),
    };

    let (datapack, dropped, rewritten) = datapack(jar, &mod_id);
    if rewritten > 0 {
        report.push(format!(
            "{rewritten} recipe(s) of mod crafting variants became plain vanilla crafting"
        ));
    }
    if dropped > 0 {
        report.push(format!(
            "{dropped} recipe(s) of mod-defined types left out: their logic is the mod's code"
        ));
    }

    Ok(Conversion {
        manifest: json!({
            "format": 1,
            "mod_id": mod_id,
            "items": content.items,
            "blocks": content.blocks,
            "channels": content.channels,
            "block_entity_types": content.block_entity_types,
            "menus": content.menus,
        }),
        mod_id,
        datapack,
        report,
    })
}

/// What a mod registers, ready for the manifest.
struct Content {
    items: Vec<Value>,
    blocks: Vec<Value>,
    channels: Vec<Value>,
    block_entity_types: Vec<Value>,
    menus: Vec<Value>,
}

/// From a registry dump (tools/mod-dumper): what the mod's code actually registered.
///
/// Exact where the data files can only guess: which ids exist (a mod ships model templates
/// that are not blocks or items, and builds some ids in code), the order its block states
/// are numbered in, and each block's hardness, light and placing item. The vanilla template
/// a block copies is still chosen by name, as it decides behaviour the dump cannot describe.
fn from_dump(
    jar: &Jar,
    mod_id: &str,
    dump: &Value,
    tags: &BlockTags,
    report: &mut Vec<String>,
) -> Content {
    let ours = |id: &str| id.split_once(':').is_some_and(|(ns, _)| ns == mod_id);
    let entries = |key: &str| -> Vec<(String, Value)> {
        dump[key]
            .as_object()
            .map(|map| {
                map.iter()
                    .filter(|(id, _)| ours(id))
                    .map(|(id, v)| (id.clone(), v.clone()))
                    .collect()
            })
            .unwrap_or_default()
    };
    let ids = |key: &str| -> Vec<Value> {
        dump[key]
            .as_array()
            .map(|list| {
                list.iter()
                    .filter(|id| id.as_str().is_some_and(ours))
                    .cloned()
                    .collect()
            })
            .unwrap_or_default()
    };

    let items = entries("items")
        .into_iter()
        .map(|(id, entry)| {
            let name = id.split_once(':').map_or(id.as_str(), |(_, n)| n);
            let template = if entry.get("block").is_some() {
                "stone"
            } else {
                item_template(name)
            };
            json!({ "id": id, "template": template })
        })
        .collect();

    let blocks = entries("blocks")
        .into_iter()
        .map(|(id, entry)| {
            let (ns, name) = id.split_once(':').unwrap_or(("", &id));
            let properties: Vec<(String, Vec<String>)> = entry["properties"]
                .as_array()
                .into_iter()
                .flatten()
                .map(|p| {
                    let values = p["values"].as_array().into_iter().flatten().filter_map(Value::as_str).map(str::to_string).collect();
                    (p["name"].as_str().unwrap_or_default().to_string(), values)
                })
                .collect();
            let item = entry["item"].as_str().map(str::to_string);
            let drops = drops(jar, ns, name, &properties, item.as_deref(), report);
            let mut def = json!({
                "id": id,
                "template": block_template(name, &id, tags).0,
                "properties": properties.iter().map(|(n, v)| json!({ "name": n, "values": v })).collect::<Vec<_>>(),
                "default_state": entry["default_state"],
                "hardness": entry["hardness"],
                "blast_resistance": entry["blast_resistance"],
                "luminance": entry["luminance"],
                "requires_tool": entry["requires_tool"],
                "drops": drops,
            });
            if let Some(item) = item {
                def["item"] = json!(item);
            }
            // Where a machine keeps its contents; without it nothing survives a window closing.
            if let Some(block_entity) = entry["block_entity"].as_str() {
                def["block_entity"] = json!(block_entity);
            }
            def
        })
        .collect();

    let channels = dump["channels"]
        .as_array()
        .into_iter()
        .flatten()
        .filter(|c| c["id"].as_str().is_some_and(ours))
        .map(|c| {
            json!({
                "id": c["id"],
                "phase": c["phase"],
                "version": c["version"],
                // The server listens unless the mod only ever sends it to clients.
                "serverbound": c["flow"].as_str() != Some("clientbound"),
            })
        })
        .collect();

    Content {
        items,
        blocks,
        channels,
        block_entity_types: ids("block_entity_types"),
        menus: ids("menus"),
    }
}

/// From the jar's data files alone, when there is no registry dump: blocks are the
/// blockstate files, items the item model definitions, channels read from bytecode.
fn from_files(jar: &Jar, tags: &BlockTags, report: &mut Vec<String>) -> Content {
    let blocks = names(jar, "blockstates");
    let item_names = names(jar, "items");

    let mut items = Vec::new();
    for (ns, name) in &item_names {
        // A block's own item places it, so it copies a block item rather than a material.
        let template = if blocks.contains(&(ns.clone(), name.clone())) {
            "stone"
        } else {
            item_template(name)
        };
        items.push(json!({ "id": format!("{ns}:{name}"), "template": template }));
    }

    let mut block_defs = Vec::new();
    for (ns, name) in &blocks {
        let id = format!("{ns}:{name}");
        let Some(state_json) = read_json(jar, &format!("assets/{ns}/blockstates/{name}.json"))
        else {
            report.push(format!("{id}: unreadable blockstate, skipped"));
            continue;
        };
        let (template, uses_template_states) = block_template(name, &id, tags);
        let properties = if uses_template_states {
            Vec::new()
        } else {
            properties(&state_json)
        };
        for (prop, values) in &properties {
            if !known_order(prop, values) {
                report.push(format!(
                    "{id}: property {prop} {values:?} in blockstate order; check it matches the mod's"
                ));
            }
        }
        let item = item_names
            .contains(&(ns.clone(), name.clone()))
            .then(|| id.clone());
        let drops = drops(jar, ns, name, &properties, item.as_deref(), report);

        let mut def = json!({
            "id": id,
            "template": template,
            "properties": properties
                .iter()
                .map(|(name, values)| json!({ "name": name, "values": values }))
                .collect::<Vec<_>>(),
            "default_state": default_state(&properties),
            "drops": drops,
        });
        if let Some(item) = item {
            def["item"] = json!(item);
        }
        if tags.needs_tool.contains(&id) {
            def["requires_tool"] = json!(true);
        }
        block_defs.push(def);
    }

    // Channels from the bytecode, so a client carrying the mod is not refused. Pumpkin does
    // not handle mod payloads, so it listens on none of them.
    let classes: Vec<_> = jar
        .iter()
        .filter(|(path, _)| {
            std::path::Path::new(path)
                .extension()
                .is_some_and(|ext| ext.eq_ignore_ascii_case("class"))
        })
        .filter_map(|(_, bytes)| crate::classfile::parse(bytes))
        .collect();
    let (channels, channel_report) = crate::channels::extract(&classes, &mod_version(jar));
    report.extend(channel_report);
    let channels = channels
        .iter()
        .map(
            |c| json!({ "id": c.id, "phase": c.phase, "version": c.version, "serverbound": false }),
        )
        .collect();

    Content {
        items,
        blocks: block_defs,
        channels,
        block_entity_types: Vec::new(),
        menus: Vec::new(),
    }
}

fn mod_id(jar: &Jar) -> Result<String, String> {
    if !jar.contains_key("META-INF/neoforge.mods.toml") {
        return Err("no META-INF/neoforge.mods.toml: not a NeoForge mod".into());
    }
    mods_value(jar, "modId").ok_or_else(|| "neoforge.mods.toml names no modId".into())
}

/// The mod's version: `version` in `neoforge.mods.toml`, or the jar manifest's
/// `Implementation-Version` when the toml defers to it.
fn mod_version(jar: &Jar) -> String {
    match mods_value(jar, "version") {
        Some(v) if !v.starts_with("${") => v,
        _ => jar
            .get("META-INF/MANIFEST.MF")
            .and_then(|m| {
                String::from_utf8_lossy(m).lines().find_map(|line| {
                    line.strip_prefix("Implementation-Version:")
                        .map(|v| v.trim().to_string())
                })
            })
            .unwrap_or_default(),
    }
}

/// The first `key = "value"` under `[[mods]]`; a dependency's keys come later in the file.
fn mods_value(jar: &Jar, key: &str) -> Option<String> {
    let toml = String::from_utf8_lossy(jar.get("META-INF/neoforge.mods.toml")?);
    let mut in_mods = false;
    for line in toml.lines().map(strip_comment) {
        if line.starts_with("[[") {
            in_mods = line == "[[mods]]";
        } else if in_mods
            && let Some((k, v)) = line.split_once('=')
            && k.trim() == key
        {
            let v = v.trim().trim_matches('"');
            if !v.is_empty() {
                return Some(v.to_string());
            }
        }
    }
    None
}

/// A TOML line without its trailing `# comment`, trimmed. A `#` inside a quoted string is
/// kept: `modId = "simulated" #mandatory` gives `modId = "simulated"`.
fn strip_comment(line: &str) -> &str {
    let mut quote = None;
    for (i, c) in line.char_indices() {
        match (quote, c) {
            (None, '"' | '\'') => quote = Some(c),
            (Some(q), _) if c == q => quote = None,
            (None, '#') => return line[..i].trim(),
            _ => {}
        }
    }
    line.trim()
}

/// `(namespace, name)` for every `assets/<ns>/<dir>/<name>.json`, minecraft excluded.
fn names(jar: &Jar, dir: &str) -> BTreeSet<(String, String)> {
    jar.keys()
        .filter_map(|path| {
            let mut parts = path.split('/');
            let (Some("assets"), Some(ns), Some(d), Some(file), None) = (
                parts.next(),
                parts.next(),
                parts.next(),
                parts.next(),
                parts.next(),
            ) else {
                return None;
            };
            let name = file.strip_suffix(".json")?;
            (d == dir && ns != "minecraft").then(|| (ns.to_string(), name.to_string()))
        })
        .collect()
}

fn read_json(jar: &Jar, path: &str) -> Option<Value> {
    serde_json::from_slice(jar.get(path)?).ok()
}

/// The vanilla tags a block's template is chosen from.
#[derive(Default)]
struct BlockTags {
    pickaxe: BTreeSet<String>,
    axe: BTreeSet<String>,
    shovel: BTreeSet<String>,
    hoe: BTreeSet<String>,
    needs_tool: BTreeSet<String>,
    ores: BTreeSet<String>,
}

impl BlockTags {
    fn read(jar: &Jar) -> Self {
        let set = |paths: &[&str]| {
            paths
                .iter()
                .filter_map(|path| read_json(jar, path))
                .flat_map(|tag| tag["values"].as_array().cloned().unwrap_or_default())
                .filter_map(|value| {
                    // Plain ids or { "id": ..., "required": false }; #tag references skipped.
                    let id = value.as_str().or_else(|| value["id"].as_str())?;
                    (!id.starts_with('#')).then(|| id.to_string())
                })
                .collect()
        };
        let tag = |name: &str| format!("data/minecraft/tags/block/{name}.json");
        Self {
            pickaxe: set(&[&tag("mineable/pickaxe")]),
            axe: set(&[&tag("mineable/axe")]),
            shovel: set(&[&tag("mineable/shovel")]),
            hoe: set(&[&tag("mineable/hoe")]),
            needs_tool: set(&[
                &tag("needs_stone_tool"),
                &tag("needs_iron_tool"),
                &tag("needs_diamond_tool"),
            ]),
            ores: set(&["data/c/tags/block/ores.json"]),
        }
    }
}

/// The vanilla block a mod block copies, and whether that block's own states are used.
///
/// Shaped blocks — slabs, stairs, walls — vary over properties their blockstate files
/// often leave out (`waterlogged` changes no model), so their states come from the
/// vanilla shape, which the mod's block extends and so shares.
fn block_template(name: &str, id: &str, tags: &BlockTags) -> (&'static str, bool) {
    const SHAPES: [(&str, &str); 8] = [
        ("_slab", "stone_slab"),
        ("_stairs", "stone_stairs"),
        ("_wall", "cobblestone_wall"),
        ("_fence_gate", "oak_fence_gate"),
        ("_fence", "oak_fence"),
        ("_trapdoor", "oak_trapdoor"),
        ("_door", "oak_door"),
        ("_pane", "glass_pane"),
    ];
    if let Some((_, template)) = SHAPES.iter().find(|(suffix, _)| name.ends_with(suffix)) {
        return (template, true);
    }
    let template = if name.contains("crop") {
        "wheat"
    } else if tags.ores.contains(id) || name.ends_with("_ore") {
        if name.contains("deepslate") {
            "deepslate_iron_ore"
        } else {
            "iron_ore"
        }
    } else if name.contains("glass") {
        "glass"
    } else if name.ends_with("_leaves") {
        "oak_leaves"
    } else if name.ends_with("_log") || name.ends_with("_wood") {
        "oak_log"
    } else if tags.axe.contains(id) {
        "oak_planks"
    } else if tags.shovel.contains(id) {
        "dirt"
    } else if tags.hoe.contains(id) {
        "hay_block"
    } else if tags.pickaxe.contains(id) && name.ends_with("_block") {
        "iron_block"
    } else {
        "stone"
    };
    (template, false)
}

/// The vanilla item a plain mod item copies, by what its name says it is.
fn item_template(name: &str) -> &'static str {
    const BY_SUFFIX: [(&str, &str); 13] = [
        ("_sword", "iron_sword"),
        ("_pickaxe", "iron_pickaxe"),
        ("_axe", "iron_axe"),
        ("_shovel", "iron_shovel"),
        ("_hoe", "iron_hoe"),
        ("_helmet", "iron_helmet"),
        ("_chestplate", "iron_chestplate"),
        ("_leggings", "iron_leggings"),
        ("_boots", "iron_boots"),
        ("_bow", "bow"),
        ("_seeds", "wheat_seeds"),
        ("_bucket", "bucket"),
        ("_shears", "shears"),
    ];
    BY_SUFFIX
        .iter()
        .find(|(suffix, _)| name.ends_with(suffix))
        .map_or("iron_ingot", |(_, template)| template)
}

/// The properties a blockstate file varies over, sorted by name as Minecraft orders them.
fn properties(state: &Value) -> Vec<(String, Vec<String>)> {
    let mut seen: BTreeMap<String, Vec<String>> = BTreeMap::new();
    let mut add = |name: &str, value: &str| {
        let values = seen.entry(name.to_string()).or_default();
        if !values.iter().any(|v| v == value) {
            values.push(value.to_string());
        }
    };
    if let Some(variants) = state["variants"].as_object() {
        for key in variants.keys() {
            for pair in key.split(',').filter(|p| !p.is_empty()) {
                if let Some((name, value)) = pair.split_once('=') {
                    add(name, value);
                }
            }
        }
    }
    for part in state["multipart"].as_array().into_iter().flatten() {
        walk(&part["when"], &mut add);
    }
    seen.into_iter()
        .map(|(name, values)| {
            let values = ordered(&name, values);
            (name, values)
        })
        .collect()
}

/// Collects every `property=value` a multipart `when` clause mentions.
fn walk(when: &Value, add: &mut dyn FnMut(&str, &str)) {
    let Some(map) = when.as_object() else {
        return;
    };
    for (key, value) in map {
        if key == "OR" || key == "AND" {
            for inner in value.as_array().into_iter().flatten() {
                walk(inner, add);
            }
        } else if let Some(values) = value.as_str() {
            for v in values.split('|') {
                add(key, v.trim_start_matches('!'));
            }
        } else if let Some(b) = value.as_bool() {
            add(key, if b { "true" } else { "false" });
        }
    }
}

const DIRECTIONS: [&str; 6] = ["down", "up", "north", "south", "west", "east"];

/// Puts values in the order Minecraft's property types declare them.
fn ordered(name: &str, mut values: Vec<String>) -> Vec<String> {
    if values.iter().all(|v| v == "true" || v == "false") {
        return vec!["true".into(), "false".into()];
    }
    if values.iter().all(|v| v.parse::<u32>().is_ok()) {
        values.sort_by_key(|v| v.parse::<u32>().unwrap_or_default());
        // A range property lists every step, even ones no model mentions.
        let (lo, hi) = (
            values[0].parse::<u32>().unwrap_or(0),
            values[values.len() - 1].parse::<u32>().unwrap_or(0),
        );
        return (lo..=hi).map(|v| v.to_string()).collect();
    }
    let order: &[&str] = match name {
        "facing" | "horizontal_facing" => &DIRECTIONS,
        "axis" => &["x", "y", "z"],
        _ => return values,
    };
    values.sort_by_key(|v| order.iter().position(|o| o == v).unwrap_or(usize::MAX));
    values
}

/// Whether `values` came in an order known to match the mod's, rather than file order.
fn known_order(name: &str, values: &[String]) -> bool {
    values.iter().all(|v| v == "true" || v == "false")
        || values.iter().all(|v| v.parse::<u32>().is_ok())
        || matches!(name, "facing" | "horizontal_facing" | "axis")
}

/// The state a block is placed in: off, empty, north and upright, by Minecraft's numbering
/// (properties as digits, the first varying slowest).
fn default_state(properties: &[(String, Vec<String>)]) -> u32 {
    let mut index = 0usize;
    for (name, values) in properties {
        let preferred = values
            .iter()
            .position(|v| v == "false" || v == "north" || (name == "axis" && v == "y"))
            .unwrap_or(0);
        index = index * values.len() + preferred;
    }
    u32::try_from(index).unwrap_or(0)
}

/// What breaking the block yields, read from its loot table.
///
/// Silk-touch alternatives, fortune and explosion decay are code-shaped and dropped; what
/// remains is the plain drop and its count. A drop gated on a single-property state (a crop
/// at full age) keeps that gate.
fn drops(
    jar: &Jar,
    ns: &str,
    name: &str,
    properties: &[(String, Vec<String>)],
    item: Option<&str>,
    report: &mut Vec<String>,
) -> Vec<Value> {
    let id = format!("{ns}:{name}");
    let Some(table) = read_json(jar, &format!("data/{ns}/loot_table/blocks/{name}.json")) else {
        // No table: the block drops itself if it has an item, as a plain block would.
        return item
            .map(|item| vec![json!({ "item": item, "min": 1, "max": 1 })])
            .unwrap_or_default();
    };

    let mut out = Vec::new();
    for pool in table["pools"].as_array().into_iter().flatten() {
        for entry in pool["entries"].as_array().into_iter().flatten() {
            let candidates: Vec<&Value> = match entry["type"].as_str() {
                Some("minecraft:alternatives") => entry["children"]
                    .as_array()
                    .map(|c| c.iter().collect())
                    .unwrap_or_default(),
                _ => vec![entry],
            };
            // The first alternative a plain tool would take.
            let Some(chosen) = candidates.into_iter().find(|c| {
                c["type"] == "minecraft:item" && !has_condition(c, "minecraft:match_tool")
            }) else {
                continue;
            };
            let Some(item) = chosen["name"].as_str() else {
                continue;
            };
            let (min, max) = count(chosen);
            let mut drop = json!({ "item": item, "min": min, "max": max });
            if let Some(range) = state_gate(chosen, entry, properties) {
                drop["from_state"] = json!(range);
                drop["to_state"] = json!(range);
            } else if has_condition(chosen, "minecraft:block_state_property")
                || has_condition(entry, "minecraft:block_state_property")
            {
                report.push(format!(
                    "{id}: drop of {item} depends on state; now always drops"
                ));
            }
            out.push(drop);
        }
    }
    out
}

fn conditions(value: &Value) -> impl Iterator<Item = &Value> {
    value["conditions"].as_array().into_iter().flatten()
}

fn has_condition(value: &Value, kind: &str) -> bool {
    conditions(value).any(|c| c["condition"] == kind)
}

/// `(min, max)` from a `set_count` function; one when there is none.
fn count(entry: &Value) -> (u8, u8) {
    let clamp = |v: &Value| v.as_f64().map_or(1, |f| f.clamp(0.0, 64.0) as u8);
    for function in entry["functions"].as_array().into_iter().flatten() {
        if function["function"] == "minecraft:set_count" {
            let count = &function["count"];
            if count.is_number() {
                return (clamp(count), clamp(count));
            }
            return (clamp(&count["min"]), clamp(&count["max"]));
        }
    }
    (1, 1)
}

/// The state index a `block_state_property` condition names, for single-property blocks.
fn state_gate(chosen: &Value, entry: &Value, properties: &[(String, Vec<String>)]) -> Option<u32> {
    let [(name, values)] = properties else {
        return None;
    };
    conditions(chosen)
        .chain(conditions(entry))
        .filter(|c| c["condition"] == "minecraft:block_state_property")
        .find_map(|c| {
            let wanted = c["properties"][name.as_str()].as_str()?;
            values
                .iter()
                .position(|v| v == wanted)
                .and_then(|i| u32::try_from(i).ok())
        })
}

/// What a recipe file becomes in the datapack.
enum Recipe {
    /// A vanilla type, kept as is.
    Keep,
    /// A mod's variant of vanilla crafting, rewritten to the vanilla type it extends.
    Rewritten(Vec<u8>),
    /// A mod-defined type: the mod's code runs it, so it cannot be kept.
    Drop,
}

fn recipe(bytes: &[u8]) -> Recipe {
    let Ok(mut recipe) = serde_json::from_slice::<Value>(bytes) else {
        return Recipe::Drop;
    };
    let Some(kind) = recipe["type"].as_str() else {
        return Recipe::Drop;
    };
    // An id without a namespace is in `minecraft`.
    let (ns, path) = kind.split_once(':').unwrap_or(("minecraft", kind));
    if ns == "minecraft" {
        return Recipe::Keep;
    }
    // `cucumber:shaped_transfer_damage` and the like are shaped crafting plus a mod-side
    // extra; the grid is vanilla's, only the extra is lost.
    let vanilla = if path.contains("shapeless") && recipe.get("ingredients").is_some() {
        "minecraft:crafting_shapeless"
    } else if path.contains("shaped") && recipe.get("pattern").is_some() {
        "minecraft:crafting_shaped"
    } else {
        return Recipe::Drop;
    };
    recipe["type"] = json!(vanilla);
    serde_json::to_vec_pretty(&recipe).map_or(Recipe::Drop, Recipe::Rewritten)
}

/// The jar's `data/` tree as a datapack: mod-typed recipes left out, mod variants of
/// crafting rewritten to vanilla's.
///
/// Returns the files, how many recipes were left out and how many rewritten.
fn datapack(jar: &Jar, mod_id: &str) -> (Vec<(String, Vec<u8>)>, usize, usize) {
    let mut files = Vec::new();
    let (mut dropped, mut rewritten) = (0, 0);
    for (path, bytes) in jar {
        if !path.starts_with("data/") || path.ends_with('/') || path.starts_with("data/neoforge/") {
            continue;
        }
        let is_recipe = path.contains("/recipe/")
            && std::path::Path::new(path)
                .extension()
                .is_some_and(|ext| ext.eq_ignore_ascii_case("json"));
        let bytes = if is_recipe {
            match recipe(bytes) {
                Recipe::Keep => bytes.clone(),
                Recipe::Rewritten(bytes) => {
                    rewritten += 1;
                    bytes
                }
                Recipe::Drop => {
                    dropped += 1;
                    continue;
                }
            }
        } else {
            bytes.clone()
        };
        files.push((path.clone(), bytes));
    }
    let mcmeta = jar.get("pack.mcmeta").cloned().unwrap_or_else(|| {
        serde_json::to_vec_pretty(&json!({
            "pack": { "description": format!("{mod_id}, converted for Pumpkin"), "pack_format": 94 }
        }))
        .unwrap_or_default()
    });
    files.push(("pack.mcmeta".into(), mcmeta));
    (files, dropped, rewritten)
}

#[cfg(test)]
mod tests {
    use super::*;

    fn jar(files: &[(&str, &str)]) -> Jar {
        files
            .iter()
            .map(|(p, c)| ((*p).to_string(), c.as_bytes().to_vec()))
            .collect()
    }

    const TOML: (&str, &str) = (
        "META-INF/neoforge.mods.toml",
        "[[mods]]\n  modId=\"ex\"\n[[dependencies.ex]]\n  modId=\"neoforge\"\n",
    );

    #[test]
    fn an_ore_becomes_an_iron_ore_that_drops_its_plain_alternative() {
        let jar = jar(&[
            TOML,
            (
                "assets/ex/blockstates/ruby_ore.json",
                r#"{"variants":{"":{"model":"x"}}}"#,
            ),
            ("assets/ex/items/ruby_ore.json", "{}"),
            ("assets/ex/items/ruby.json", "{}"),
            (
                "data/minecraft/tags/block/needs_iron_tool.json",
                r#"{"values":["ex:ruby_ore"]}"#,
            ),
            (
                "data/ex/loot_table/blocks/ruby_ore.json",
                r#"{"pools":[{"entries":[{"type":"minecraft:alternatives","children":[
                    {"type":"minecraft:item","name":"ex:ruby_ore","conditions":[{"condition":"minecraft:match_tool"}]},
                    {"type":"minecraft:item","name":"ex:ruby","functions":[{"function":"minecraft:set_count","count":{"min":1.0,"max":3.0}}]}
                ]}]}]}"#,
            ),
        ]);
        let c = convert(&jar, None).unwrap();
        assert_eq!(c.mod_id, "ex");
        let block = &c.manifest["blocks"][0];
        assert_eq!(block["template"], "iron_ore");
        assert_eq!(block["item"], "ex:ruby_ore");
        assert_eq!(block["requires_tool"], true);
        assert_eq!(
            block["drops"],
            json!([{ "item": "ex:ruby", "min": 1, "max": 3 }])
        );
        let items: Vec<&str> = c.manifest["items"]
            .as_array()
            .unwrap()
            .iter()
            .map(|i| i["template"].as_str().unwrap())
            .collect();
        assert_eq!(
            items,
            ["iron_ingot", "stone"],
            "ruby is a material, ruby_ore places its block"
        );
    }

    #[test]
    fn a_crop_keeps_its_ages_and_drops_essence_only_when_grown() {
        let jar = jar(&[
            TOML,
            (
                "assets/ex/blockstates/ruby_crop.json",
                r#"{"variants":{"age=0":{},"age=7":{},"age=3":{}}}"#,
            ),
            (
                "data/ex/loot_table/blocks/ruby_crop.json",
                r#"{"pools":[{"entries":[{"type":"minecraft:item","name":"ex:ruby_essence",
                    "conditions":[{"condition":"minecraft:block_state_property","properties":{"age":"7"}}]}]}]}"#,
            ),
        ]);
        let block = &convert(&jar, None).unwrap().manifest["blocks"][0];
        assert_eq!(block["template"], "wheat");
        assert_eq!(
            block["properties"][0]["values"],
            json!(["0", "1", "2", "3", "4", "5", "6", "7"])
        );
        assert_eq!(block["drops"][0]["from_state"], 7);
        assert_eq!(block["default_state"], 0);
    }

    #[test]
    fn states_are_numbered_like_minecraft() {
        let props = properties(
            &json!({ "variants": { "lit=true,facing=east": {}, "lit=false,facing=north": {}, "facing=south,lit=true": {}, "facing=west,lit=true": {} } }),
        );
        assert_eq!(
            props[0],
            (
                "facing".into(),
                vec!["north".into(), "south".into(), "west".into(), "east".into()]
            )
        );
        assert_eq!(
            props[1],
            ("lit".into(), vec!["true".into(), "false".into()])
        );
        // facing=north (0) slowest, lit=false (1): 0 * 2 + 1.
        assert_eq!(default_state(&props), 1);
    }

    #[test]
    fn shaped_blocks_take_their_states_from_the_vanilla_shape() {
        let jar = jar(&[
            TOML,
            (
                "assets/ex/blockstates/ruby_stairs.json",
                r#"{"variants":{"facing=east,half=top,shape=straight":{}}}"#,
            ),
        ]);
        let block = &convert(&jar, None).unwrap().manifest["blocks"][0];
        assert_eq!(block["template"], "stone_stairs");
        assert_eq!(block["properties"], json!([]));
    }

    #[test]
    fn mod_typed_recipes_stay_out_of_the_datapack() {
        let jar = jar(&[
            TOML,
            (
                "data/ex/recipe/a.json",
                r#"{"type":"minecraft:crafting_shaped"}"#,
            ),
            ("data/ex/recipe/b.json", r#"{"type":"ex:infusion"}"#),
            ("data/ex/recipe/c.json", r#"{"type":"crafting_shapeless"}"#),
            (
                "data/ex/recipe/d.json",
                r#"{"type":"ex:shaped_keep_nbt","pattern":["X"]}"#,
            ),
            ("data/neoforge/data_maps/x.json", "{}"),
        ]);
        let c = convert(&jar, None).unwrap();
        let paths: Vec<&str> = c.datapack.iter().map(|(p, _)| p.as_str()).collect();
        assert_eq!(
            paths,
            [
                "data/ex/recipe/a.json",
                "data/ex/recipe/c.json",
                "data/ex/recipe/d.json",
                "pack.mcmeta"
            ]
        );
        let rewritten: Value = serde_json::from_slice(&c.datapack[2].1).unwrap();
        assert_eq!(rewritten["type"], "minecraft:crafting_shaped");
        assert_eq!(
            c.report.len(),
            2,
            "one line for the rewrite, one for the drop"
        );
    }

    #[test]
    fn a_dump_decides_what_exists_and_how_states_are_numbered() {
        let jar = jar(&[
            TOML,
            // A template the mod's code points crops at: not a block of its own.
            (
                "assets/ex/blockstates/crop_template.json",
                r#"{"variants":{"age=0":{}}}"#,
            ),
            ("assets/ex/items/essence_template.json", "{}"),
        ]);
        let dump = json!({
            "blocks": {
                "ex:iron_crop": {
                    "properties": [{ "name": "age", "values": ["0", "1"] }],
                    "default_state": 0, "hardness": 0.0, "blast_resistance": 0.0,
                    "luminance": 0, "requires_tool": false, "item": "ex:iron_seeds"
                },
                "other:thing": { "properties": [], "default_state": 0 }
            },
            "items": { "ex:iron_seeds": { "block": "ex:iron_crop" }, "ex:iron_essence": {} },
            "block_entity_types": ["ex:machine"],
            "menus": ["ex:machine"],
            "channels": [{ "id": "ex:sync", "phase": "play", "version": "3", "flow": "clientbound", "optional": false }]
        });
        let m = convert(&jar, Some(&dump)).unwrap().manifest;

        let block_ids: Vec<&str> = m["blocks"]
            .as_array()
            .unwrap()
            .iter()
            .map(|b| b["id"].as_str().unwrap())
            .collect();
        assert_eq!(
            block_ids,
            ["ex:iron_crop"],
            "templates and other mods' blocks are left out"
        );
        assert_eq!(m["blocks"][0]["item"], "ex:iron_seeds");
        assert_eq!(m["blocks"][0]["template"], "wheat");
        let items: Vec<(&str, &str)> = m["items"]
            .as_array()
            .unwrap()
            .iter()
            .map(|i| (i["id"].as_str().unwrap(), i["template"].as_str().unwrap()))
            .collect();
        assert_eq!(
            items,
            [
                ("ex:iron_essence", "iron_ingot"),
                ("ex:iron_seeds", "stone")
            ]
        );
        assert_eq!(
            m["channels"],
            json!([{ "id": "ex:sync", "phase": "play", "version": "3", "serverbound": false }])
        );
        assert_eq!(m["block_entity_types"], json!(["ex:machine"]));
    }

    #[test]
    fn comments_after_tables_and_values_are_ignored() {
        let mut jar = Jar::new();
        jar.insert(
            "META-INF/neoforge.mods.toml".into(),
            b"[[mods]] #mandatory\nmodId = \"simulated\" #mandatory\ndisplayName=\"A # B\"\n".to_vec(),
        );
        assert_eq!(mods_value(&jar, "modId").as_deref(), Some("simulated"));
        assert_eq!(mods_value(&jar, "displayName").as_deref(), Some("A # B"));
    }
}
