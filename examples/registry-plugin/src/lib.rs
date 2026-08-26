//! Registers custom content at load, to prove the registry path end to end.
//!
//! Everything here happens in `on_load`, which is the only window where registration is
//! open: the server freezes its registries once every plugin has loaded.

use pumpkin_plugin_api::{
    Context, Plugin, PluginMetadata, register_plugin,
    registry::{self, BlockDefinition},
};

struct RegistryPlugin;

impl Plugin for RegistryPlugin {
    fn new() -> Self {
        Self
    }

    fn metadata(&self) -> PluginMetadata {
        PluginMetadata {
            name: "registry-plugin".into(),
            version: "0.1.0".into(),
            authors: vec!["Pumpkin".into()],
            description: "Registers an example block, item and entity type.".into(),
            dependencies: vec![],
            permissions: vec![],
        }
    }

    fn on_load(&mut self, _context: Context) -> Result<(), String> {
        // A hot reload lands after the freeze, so say so plainly rather than reporting a
        // confusing "already registered" further down.
        if registry::is_frozen() {
            return Err(
                "registration has closed; registered content needs a server restart".into(),
            );
        }

        let block = registry::register_block(
            &BlockDefinition::new("examplemod:ruby_block", "stone")
                .hardness(4.0)
                .luminance(7),
        )?;
        tracing::info!("registered block examplemod:ruby_block as id {block}");

        let item = registry::register_item(&registry::item("examplemod:ruby", "diamond"))?;
        tracing::info!("registered item examplemod:ruby as id {item}");

        let entity =
            registry::register_entity_type(&registry::entity_type("examplemod:ruby_golem", "allay"))?;
        tracing::info!("registered entity type examplemod:ruby_golem as id {entity}");

        Ok(())
    }

    fn on_unload(&mut self, _context: Context) -> Result<(), String> {
        Ok(())
    }
}

register_plugin!(RegistryPlugin);
