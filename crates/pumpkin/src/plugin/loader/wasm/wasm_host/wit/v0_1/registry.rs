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
    Block, BlockState, BlockStateId,
    dynamic::{
        BlockRegistration, EntityTypeRegistration, ItemRegistration, register_block,
        register_entity_type, register_item,
    },
    entity::EntityType,
    item::Item,
};

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
        let Some(template) = Block::from_name(&definition.template) else {
            return Ok(Err(unknown_template("block", &definition.template)));
        };

        let states: Vec<BlockState> = template
            .states
            .iter()
            .map(|state| copy_state(state, definition.luminance))
            .collect();

        // The template's default state sits at a known offset in its own list, and the copy
        // preserves the order, so the same offset selects the copy's default.
        let default_state_index = template
            .states
            .iter()
            .position(|state| state.id == template.default_state.id)
            .unwrap_or(0);

        let block = Block {
            hardness: definition.hardness.unwrap_or(template.hardness),
            blast_resistance: definition
                .blast_resistance
                .unwrap_or(template.blast_resistance),
            ..template.clone()
        };

        Ok(register_block(BlockRegistration {
            name: definition.id,
            block,
            states,
            default_state_index,
        })
        .map(|id| u32::from(id.as_u16()))
        .map_err(|err| err.to_string()))
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

/// `BlockState` is not `Clone`, and copying it by hand is what lets a registered block
/// inherit a template's full state list.
fn copy_state(template: &BlockState, luminance: Option<u8>) -> BlockState {
    BlockState {
        // Replaced by the registry.
        id: BlockStateId::AIR,
        state_flags: template.state_flags,
        side_flags: template.side_flags,
        instrument: template.instrument,
        luminance: luminance.map_or(template.luminance, |value| value.min(15)),
        piston_behavior: template.piston_behavior.clone(),
        hardness: template.hardness,
        collision_shapes: template.collision_shapes,
        outline_shapes: template.outline_shapes,
        opacity: template.opacity,
        block_entity_type: template.block_entity_type,
    }
}

fn unknown_template(kind: &str, name: &str) -> String {
    format!("unknown {kind} template {name}")
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn copied_states_inherit_the_template() {
        let template = Block::STONE.default_state;
        let copy = copy_state(template, None);

        assert_eq!(copy.state_flags, template.state_flags);
        assert_eq!(copy.opacity, template.opacity);
        assert_eq!(copy.piston_behavior, template.piston_behavior);
        assert_eq!(copy.luminance, template.luminance);
        assert_eq!(
            copy.id,
            BlockStateId::AIR,
            "the registry assigns the real state id"
        );
    }

    #[test]
    fn luminance_overrides_the_template_and_clamps_to_the_vanilla_range() {
        let template = Block::STONE.default_state;

        assert_eq!(copy_state(template, Some(7)).luminance, 7);
        assert_eq!(
            copy_state(template, Some(200)).luminance,
            15,
            "light level cannot exceed 15"
        );
    }

    #[test]
    fn a_template_with_many_states_copies_all_of_them() {
        // Stone has one state; something with properties exercises the list copy.
        let template = Block::OAK_LOG;
        assert!(template.states.len() > 1, "test needs a multi-state block");

        let copies: Vec<_> = template
            .states
            .iter()
            .map(|state| copy_state(state, None))
            .collect();

        assert_eq!(copies.len(), template.states.len());
        for (copy, original) in copies.iter().zip(template.states) {
            assert_eq!(copy.state_flags, original.state_flags);
        }
    }
}
