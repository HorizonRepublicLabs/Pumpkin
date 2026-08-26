//! Server half of Mystical Agriculture, as far as identity goes.
//!
//! The mod's Java jar runs on the client. This declares the matching server side: the
//! network channels the mod requires, and every block and item it registers, so that the
//! two ends agree on ids.
//!
//! # What this does and does not do
//!
//! It gets a client carrying the mod to **join**, and gives its content real ids that the
//! registry sync hands to the client. The content has no behaviour: crops do not grow,
//! altars do not infuse, machines do not run. Those live in ~284 Java files that would have
//! to be reimplemented here, one system at a time.
//!
//! Two things must match the client exactly or the join fails:
//!
//! - **Channel ids and versions**, from the mod's `NetworkHandler`. The mod calls
//!   `event.registrar("1")` without `.optional()`, so all four are required, and a client
//!   refuses a server that did not negotiate them.
//! - **Registry contents**, because a snapshot replaces the client's registry rather than
//!   adding to it. The lists in [`content`] are generated from the mod's own built assets
//!   for that reason.

mod content;

use pumpkin_plugin_api::{
    Context, Plugin, PluginMetadata, register_plugin,
    registry::{self, BlockDefinition, ChannelProtocol},
};

/// The mod's payload channels, from `NetworkHandler.onRegisterPayloadsHandlers`.
///
/// The flag is whether the *server* listens: three are `playToClient`, so the server only
/// sends them, and one is `playToServer`.
const CHANNELS: [(&str, bool); 4] = [
    ("mysticalagriculture:experience_pickup", false),
    ("mysticalagriculture:reload_ingredient_cache", false),
    ("mysticalagriculture:sync_essence_vessel_colors", false),
    ("mysticalagriculture:update_aoe_offset", true),
];

/// The `event.registrar("1")` argument in the mod. `NeoForge` compares this against the
/// client's, so it is not a version we get to choose.
const CHANNEL_VERSION: &str = "1";

const MOD_ID: &str = "mysticalagriculture";

struct MysticalAgriculturePlugin;

impl Plugin for MysticalAgriculturePlugin {
    fn new() -> Self {
        Self
    }

    fn metadata(&self) -> PluginMetadata {
        PluginMetadata {
            name: "mysticalagriculture".into(),
            version: "0.1.0".into(),
            authors: vec!["Pumpkin".into()],
            description: "Server-side identity for the Mystical Agriculture client mod.".into(),
            dependencies: vec![],
            permissions: vec![],
        }
    }

    fn on_load(&mut self, _context: Context) -> Result<(), String> {
        if registry::is_frozen() {
            return Err("registration has closed; this plugin needs a server restart".into());
        }

        for (id, serverbound) in CHANNELS {
            registry::channel(id, ChannelProtocol::Play, CHANNEL_VERSION, serverbound)?;
        }
        tracing::info!("declared {} network channels", CHANNELS.len());

        for (name, template) in content::BLOCKS {
            registry::register_block(&BlockDefinition::new(
                format!("{MOD_ID}:{name}"),
                template,
            ))?;
        }
        tracing::info!("registered {} blocks", content::BLOCKS.len());

        for (name, template) in content::ITEMS {
            registry::register_item(&registry::item(format!("{MOD_ID}:{name}"), template))?;
        }
        tracing::info!("registered {} items", content::ITEMS.len());

        for name in content::MENU_TYPES {
            registry::register_menu_type(&format!("{MOD_ID}:{name}"))?;
        }
        tracing::info!("registered {} menu types", content::MENU_TYPES.len());

        for name in content::BLOCK_ENTITY_TYPES {
            registry::register_block_entity_type(&format!("{MOD_ID}:{name}"))?;
        }
        tracing::info!(
            "registered {} block entity types",
            content::BLOCK_ENTITY_TYPES.len()
        );

        Ok(())
    }

    fn on_unload(&mut self, _context: Context) -> Result<(), String> {
        Ok(())
    }
}

register_plugin!(MysticalAgriculturePlugin);
