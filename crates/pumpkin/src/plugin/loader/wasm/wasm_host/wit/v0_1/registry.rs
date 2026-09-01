//! Host side of the content registry: lets a plugin add blocks, items and entity types.
//!
//! Every definition copies a vanilla template and overrides what it needs. That keeps the
//! WIT surface small — a `Block` has fifteen fields and a full set of block states, almost
//! all of which custom content wants to inherit — and it means the API does not have to
//! grow a new field every time the generated data does.
//!
//! Registration only works while plugins are loading; the server freezes its registries
//! afterwards, and the window never reopens, so a hot-reloaded plugin cannot register
//! content. Everything here reports that as an ordinary error string rather than trapping,
//! so a plugin that registers too late gets a message it can log.

use pumpkin_data::{
    dynamic::{EntityTypeRegistration, ItemRegistration, register_entity_type, register_item},
    entity::EntityType,
    item::Item,
};

use crate::plugin::host::registry::{BlockDrop as HostBlockDrop, BlockProperty, BlockSpec};
use crate::plugin::loader::wasm::wasm_host::{state::PluginHostState, wit::v0_1::pumpkin};

use pumpkin::plugin::registry::{
    BlockDefinition, ChannelProtocol as WitChannelProtocol, EntityTypeDefinition, ItemDefinition,
    NetworkChannel as WitNetworkChannel,
};

use crate::net::java::neoforge::channels::{self, ChannelProtocol, ModdedChannel};

impl pumpkin::plugin::registry::Host for PluginHostState {
    async fn register_block(
        &mut self,
        definition: BlockDefinition,
    ) -> wasmtime::Result<Result<u32, String>> {
        let spec = block_spec_from_wit(definition);

        let registered = match crate::plugin::host::registry::register_block_spec(&spec) {
            Ok(registered) => registered,
            Err(err) => return Ok(Err(err)),
        };

        // Every hook the server has asks the registry for behaviour by block id, and a
        // registered block that answers nothing there has none at all.
        if let Some(server) = self.server.as_ref() {
            let behaviour: std::sync::Arc<dyn crate::block::BlockBehaviour> = std::sync::Arc::new(
                crate::plugin::api::block_behaviour::PluginBlockBehaviour::new(
                    registered.first_state,
                    registered.drops,
                ),
            );
            server
                .block_registry
                .set_plugin_block(registered.block_id, behaviour);
        }

        Ok(Ok(u32::from(registered.block_id.as_u16())))
    }

    async fn register_item(
        &mut self,
        definition: ItemDefinition,
    ) -> wasmtime::Result<Result<u32, String>> {
        let Some(template) = Item::from_registry_key(&definition.template) else {
            return Ok(Err(unknown_template("item", &definition.template)));
        };

        Ok(register_item(ItemRegistration {
            name: definition.id,
            item: template.clone(),
        })
        .map(u32::from)
        .map_err(|err| err.to_string()))
    }

    async fn register_entity_type(
        &mut self,
        definition: EntityTypeDefinition,
    ) -> wasmtime::Result<Result<u32, String>> {
        let Some(template) = EntityType::from_name(&definition.template) else {
            return Ok(Err(unknown_template("entity type", &definition.template)));
        };

        Ok(register_entity_type(EntityTypeRegistration {
            name: definition.id,
            entity_type: template.clone(),
        })
        .map(u32::from)
        .map_err(|err| err.to_string()))
    }

    async fn register_menu_type(&mut self, id: String) -> wasmtime::Result<Result<u32, String>> {
        Ok(pumpkin_data::dynamic::register_menu_type(id)
            .map(u32::from)
            .map_err(|err| err.to_string()))
    }

    async fn register_block_entity_type(
        &mut self,
        id: String,
    ) -> wasmtime::Result<Result<u32, String>> {
        Ok(pumpkin_data::dynamic::register_block_entity_type(id)
            .map(u32::from)
            .map_err(|err| err.to_string()))
    }

    async fn declare_network_channel(
        &mut self,
        channel: WitNetworkChannel,
    ) -> wasmtime::Result<Result<(), String>> {
        Ok(channels::declare(ModdedChannel {
            id: channel.id,
            protocol: match channel.protocol {
                WitChannelProtocol::Play => ChannelProtocol::Play,
                WitChannelProtocol::Configuration => ChannelProtocol::Configuration,
            },
            version: channel.version,
            serverbound: channel.serverbound,
        })
        .map_err(|err| err.to_string()))
    }

    async fn is_frozen(&mut self) -> wasmtime::Result<bool> {
        Ok(pumpkin_data::dynamic::is_frozen())
    }
}

/// Field-by-field copy from the WIT-generated type into the plain one the shared registry
/// logic in [`crate::plugin::host::registry`] works with. Wasm is the only loader that has
/// to do this: a WIT type is not something a JVM caller could construct anyway.
fn block_spec_from_wit(definition: BlockDefinition) -> BlockSpec {
    BlockSpec {
        id: definition.id,
        template: definition.template,
        hardness: definition.hardness,
        blast_resistance: definition.blast_resistance,
        luminance: definition.luminance,
        requires_tool: definition.requires_tool,
        properties: definition
            .properties
            .into_iter()
            .map(|property| BlockProperty {
                name: property.name,
                values: property.values,
            })
            .collect(),
        default_state: definition.default_state,
        item: definition.item,
        drops: definition
            .drops
            .into_iter()
            .map(|drop| HostBlockDrop {
                item: drop.item,
                min: drop.min,
                max: drop.max,
                from_state: drop.from_state,
                to_state: drop.to_state,
            })
            .collect(),
        block_entity: definition.block_entity,
    }
}

fn unknown_template(kind: &str, name: &str) -> String {
    format!("unknown {kind} template {name}")
}
