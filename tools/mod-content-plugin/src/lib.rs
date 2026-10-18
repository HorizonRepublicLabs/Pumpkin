//! Registers converted mods' blocks, items and channels.
//!
//! `pumpkin-mod-convert` writes one manifest per mod into this plugin's data folder,
//! `plugins/data/mod-content/<mod>.json`. On load each is registered in turn: channels,
//! block entity and menu types, then items (a block names the item that places it, so items come first), then blocks.
//! A bad entry is logged and skipped rather than failing the plugin, so one mod's mistake
//! does not unregister every other mod.

use pumpkin_plugin_api::{
    Context, Plugin, PluginMetadata, Result, permissions, register_plugin,
    registry::{self, ChannelProtocol},
    wit::pumpkin::plugin::registry::{BlockDefinition, BlockDrop, BlockProperty, register_block},
};
use serde_json::Value;

struct ModContent;

impl Plugin for ModContent {
    fn new() -> Self {
        Self
    }

    fn metadata(&self) -> PluginMetadata {
        PluginMetadata {
            name: "mod-content".into(),
            version: env!("CARGO_PKG_VERSION").into(),
            authors: vec![],
            description: env!("CARGO_PKG_DESCRIPTION").into(),
            dependencies: vec![],
            permissions: vec![permissions::FS_READ_DATA.into()],
        }
    }

    async fn on_load(&self, context: Context) -> Result<()> {
        let folder = context.get_data_folder();
        let mut paths: Vec<_> = std::fs::read_dir(&folder)
            .map_err(|e| format!("reading {folder}: {e}"))?
            .filter_map(|entry| entry.ok().map(|e| e.path()))
            .filter(|path| path.extension().is_some_and(|ext| ext == "json"))
            .collect();
        // Sorted, so ids come out the same every boot.
        paths.sort();
        for path in paths {
            let manifest: Value = match std::fs::read(&path)
                .map_err(|e| e.to_string())
                .and_then(|bytes| serde_json::from_slice(&bytes).map_err(|e| e.to_string()))
            {
                Ok(manifest) => manifest,
                Err(err) => {
                    tracing::error!("{}: {err}", path.display());
                    continue;
                }
            };
            register(&manifest);
        }
        Ok(())
    }
}

fn register(manifest: &Value) {
    let mod_id = manifest["mod_id"].as_str().unwrap_or("?");
    let list = |key: &str| manifest[key].as_array().cloned().unwrap_or_default();
    let text = |v: &Value, key: &str| v[key].as_str().unwrap_or_default().to_string();

    for channel in list("channels") {
        let protocol = if channel["phase"] == "configuration" {
            ChannelProtocol::Configuration
        } else {
            ChannelProtocol::Play
        };
        let result = registry::channel(
            text(&channel, "id"),
            protocol,
            text(&channel, "version"),
            channel["serverbound"].as_bool().unwrap_or(false),
        );
        if let Err(err) = result {
            tracing::warn!("{mod_id}: channel {}: {err}", text(&channel, "id"));
        }
    }

    // Before blocks and items, as a block can name its block entity type. Registered so the
    // registry sync tells a client the types its mod expects exist.
    for id in list("block_entity_types") {
        if let Some(id) = id.as_str()
            && let Err(err) = registry::register_block_entity_type(id)
        {
            tracing::warn!("{mod_id}: block entity type {id}: {err}");
        }
    }
    for id in list("menus") {
        if let Some(id) = id.as_str()
            && let Err(err) = registry::register_menu_type(id)
        {
            tracing::warn!("{mod_id}: menu {id}: {err}");
        }
    }

    let mut items = 0;
    for item in list("items") {
        match registry::register_item(&registry::item(text(&item, "id"), text(&item, "template"))) {
            Ok(_) => items += 1,
            Err(err) => tracing::warn!("{mod_id}: item {}: {err}", text(&item, "id")),
        }
    }

    let mut blocks = 0;
    for block in list("blocks") {
        match register_block(&block_definition(&block)) {
            Ok(_) => blocks += 1,
            Err(err) => tracing::warn!("{mod_id}: block {}: {err}", text(&block, "id")),
        }
    }
    tracing::info!("{mod_id}: registered {blocks} block(s) and {items} item(s)");
}

fn block_definition(block: &Value) -> BlockDefinition {
    let text = |v: &Value| v.as_str().map(str::to_string);
    let small = |v: &Value| v.as_u64().and_then(|n| u8::try_from(n).ok());
    let index = |v: &Value| v.as_u64().and_then(|n| u32::try_from(n).ok());
    BlockDefinition {
        id: text(&block["id"]).unwrap_or_default(),
        template: text(&block["template"]).unwrap_or_else(|| "stone".into()),
        hardness: block["hardness"].as_f64().map(|f| f as f32),
        blast_resistance: block["blast_resistance"].as_f64().map(|f| f as f32),
        luminance: small(&block["luminance"]),
        requires_tool: block["requires_tool"].as_bool(),
        properties: block["properties"]
            .as_array()
            .into_iter()
            .flatten()
            .map(|p| BlockProperty {
                name: text(&p["name"]).unwrap_or_default(),
                values: p["values"]
                    .as_array()
                    .into_iter()
                    .flatten()
                    .filter_map(text)
                    .collect(),
            })
            .collect(),
        default_state: index(&block["default_state"]).unwrap_or(0),
        item: text(&block["item"]),
        drops: block["drops"]
            .as_array()
            .into_iter()
            .flatten()
            .map(|d| BlockDrop {
                item: text(&d["item"]).unwrap_or_default(),
                min: small(&d["min"]).unwrap_or(1),
                max: small(&d["max"]).unwrap_or(1),
                from_state: index(&d["from_state"]),
                to_state: index(&d["to_state"]),
            })
            .collect(),
        block_entity: None,
    }
}

register_plugin!(ModContent);
