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

        // The properties decide how many states there are, because that count has to match
        // the client's. Only a block that declares none falls back to the template's own.
        let state_count = definition
            .properties
            .iter()
            .try_fold(1usize, |total, property| {
                total.checked_mul(property.values.len().max(1))
            })
            .filter(|count| *count > 0)
            .unwrap_or(1);

        // Resolved before the states are built, because every one of them carries it.
        // Naming a type that was never registered is a mistake worth reporting rather
        // than quietly leaving the block with the template's, or with none at all.
        let block_entity_type = match definition.block_entity {
            // Registered in the same pass as the blocks, so it is still staged.
            Some(ref name) => match pumpkin_data::dynamic::registering_block_entity_type_id(name) {
                Some(id) => Some(id),
                None => return Ok(Err(unknown_template("block entity type", name))),
            },
            None => None,
        };

        let sources = template_states(template, state_count, definition.properties.is_empty());

        let states: Vec<BlockState> = sources
            .iter()
            .map(|state| copy_state(state, &definition, block_entity_type))
            .collect();

        let default_state_index = if definition.properties.is_empty() {
            // The template's default sits at a known offset in its own list, and the copy
            // preserves the order, so the same offset selects the copy's default.
            template
                .states
                .iter()
                .position(|state| state.id == template.default_state.id)
                .unwrap_or(0)
        } else {
            (definition.default_state as usize).min(states.len().saturating_sub(1))
        };

        let block = Block {
            hardness: definition.hardness.unwrap_or(template.hardness),
            blast_resistance: definition
                .blast_resistance
                .unwrap_or(template.blast_resistance),
            ..template.clone()
        };

        // Resolving by name means the plugin registers the item first and simply names it,
        // rather than having to thread ids around.
        let item_id = match definition.item {
            Some(ref name) => {
                // Generated items resolve by name; one registered moments ago is still
                // staged, so the registry is asked rather than the published tables.
                let id = Item::from_registry_key(name)
                    .map(|item| item.id)
                    .or_else(|| pumpkin_data::dynamic::registering_item_id(name));

                match id {
                    Some(id) => Some(id),
                    None => return Ok(Err(unknown_template("item", name))),
                }
            }
            None => None,
        };

        let properties = definition
            .properties
            .iter()
            .map(|property| (property.name.clone(), property.values.clone()))
            .collect();

        let drops = match resolve_drops(&definition.drops) {
            Ok(drops) => drops,
            Err(name) => return Ok(Err(unknown_template("item", &name))),
        };

        let registered = register_block(BlockRegistration {
            name: definition.id,
            block,
            states,
            default_state_index,
            item_id,
            properties,
        });

        // Every hook the server has asks the registry for behaviour by block id, and a
        // registered block that answers nothing there has none at all.
        if let Ok(registered) = registered
            && let Some(server) = self.server.as_ref()
        {
            let behaviour: &'static dyn crate::block::BlockBehaviour = Box::leak(Box::new(
                crate::plugin::api::block_behaviour::PluginBlockBehaviour::new(
                    registered.first_state.as_u16(),
                    drops,
                ),
            ));
            server
                .block_registry
                .set_plugin_block(registered.block_id, behaviour);
        }

        Ok(registered
            .map(|registered| u32::from(registered.block_id.as_u16()))
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
fn copy_state(
    template: &BlockState,
    definition: &BlockDefinition,
    block_entity_type: Option<u16>,
) -> BlockState {
    let luminance = definition.luminance;
    // Mining reads the state's hardness, not the block's, so a definition that set only
    // the block would break at whatever speed its template did.
    let hardness = definition.hardness.unwrap_or(template.hardness);
    let state = BlockState {
        // Replaced by the registry.
        id: BlockStateId::AIR,
        state_flags: template.state_flags,
        side_flags: template.side_flags,
        instrument: template.instrument,
        luminance: luminance.map_or(template.luminance, |value| value.min(15)),
        piston_behavior: template.piston_behavior.clone(),
        hardness,
        collision_shapes: template.collision_shapes,
        outline_shapes: template.outline_shapes,
        opacity: template.opacity,
        block_entity_type: block_entity_type.unwrap_or(template.block_entity_type),
    };

    match definition.requires_tool {
        Some(required) => state.with_tool_required(required),
        None => state,
    }
}

/// The template states each of a registration's states is copied from.
///
/// A block that declares properties decides its own state count, and gets the template's
/// states cycled so one with more states than its template still gets a full set rather
/// than running out. One that declares none takes the template's own.
fn template_states(
    template: &'static Block,
    state_count: usize,
    no_properties: bool,
) -> Vec<&'static BlockState> {
    if no_properties {
        return template.states.iter().collect();
    }
    (0..state_count)
        .map(|index| {
            template
                .states
                .get(index % template.states.len())
                .unwrap_or(template.default_state)
        })
        .collect()
}

/// Turns the drops a registration declared into ones the server can act on.
///
/// Resolved while the plugin is loading rather than when a block breaks, so a drop naming
/// an item that was never registered is reported where the mistake is.
///
/// # Errors
///
/// Returns the name of the first item that could not be resolved.
fn resolve_drops(
    declared: &[pumpkin::plugin::registry::BlockDrop],
) -> Result<Vec<crate::plugin::api::block_behaviour::BlockDrop>, String> {
    declared
        .iter()
        .map(|drop| {
            // Generated items resolve by name; one registered moments ago is still staged.
            Item::from_registry_key(&drop.item)
                .map(|item| item.id)
                .or_else(|| pumpkin_data::dynamic::registering_item_id(&drop.item))
                .map(|item_id| crate::plugin::api::block_behaviour::BlockDrop {
                    item_id,
                    min: drop.min,
                    max: drop.max,
                    from_state: drop.from_state.unwrap_or(0),
                    to_state: drop.to_state.unwrap_or(u32::MAX),
                })
                .ok_or_else(|| drop.item.clone())
        })
        .collect()
}

fn unknown_template(kind: &str, name: &str) -> String {
    format!("unknown {kind} template {name}")
}

#[cfg(test)]
mod tests {
    use super::*;

    /// A registration that overrides nothing, so every copied state keeps the template's.
    fn plain() -> BlockDefinition {
        BlockDefinition {
            id: "examplemod:thing".to_string(),
            template: "stone".to_string(),
            hardness: None,
            blast_resistance: None,
            luminance: None,
            requires_tool: None,
            properties: Vec::new(),
            default_state: 0,
            item: None,
            block_entity: None,
            drops: Vec::new(),
        }
    }

    fn lit(luminance: u8) -> BlockDefinition {
        BlockDefinition {
            luminance: Some(luminance),
            ..plain()
        }
    }

    #[test]
    fn copied_states_inherit_the_template() {
        let template = Block::STONE.default_state;
        let copy = copy_state(template, &plain(), None);

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

        assert_eq!(copy_state(template, &lit(7), None).luminance, 7);
        assert_eq!(
            copy_state(template, &lit(200), None).luminance,
            15,
            "light level cannot exceed 15"
        );
    }

    #[test]
    fn hardness_and_tool_rules_reach_the_states_mining_reads() {
        let template = Block::STONE.default_state;

        let softer = BlockDefinition {
            hardness: Some(3.5),
            requires_tool: Some(true),
            ..plain()
        };
        let copy = copy_state(template, &softer, None);
        assert!((copy.hardness - 3.5).abs() < f32::EPSILON);
        assert!(copy.tool_required());

        // Mining reads the state, so a definition that set only the block would break at
        // whatever speed its template did.
        let freed = BlockDefinition {
            requires_tool: Some(false),
            ..plain()
        };
        assert!(!copy_state(template, &freed, None).tool_required());
    }

    #[test]
    fn a_template_with_many_states_copies_all_of_them() {
        // Stone has one state; something with properties exercises the list copy.
        let template = Block::OAK_LOG;
        assert!(template.states.len() > 1, "test needs a multi-state block");

        let copies: Vec<_> = template
            .states
            .iter()
            .map(|state| copy_state(state, &plain(), None))
            .collect();

        assert_eq!(copies.len(), template.states.len());
        for (copy, original) in copies.iter().zip(template.states) {
            assert_eq!(copy.state_flags, original.state_flags);
        }
    }
}
