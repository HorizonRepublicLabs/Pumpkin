//! Loader-agnostic block registration.
//!
//! Every definition copies a vanilla template and overrides what it needs. That keeps the
//! surface small — a `Block` has fifteen fields and a full set of block states, almost all
//! of which custom content wants to inherit — and it means the API does not have to grow a
//! new field every time the generated data does.
//!
//! Registration only works while plugins are loading; the server freezes its registries
//! afterwards, and the window never reopens, so a hot-reloaded plugin cannot register
//! content. Everything here reports that as an ordinary error string rather than trapping,
//! so a plugin that registers too late gets a message it can log.

use pumpkin_data::{
    Block, BlockId, BlockState, BlockStateId,
    dynamic::{BlockRegistration, register_block},
    item::Item,
};

/// One property a block's state varies over, e.g. `age` with values `0`..`7`.
pub struct BlockProperty {
    /// Property name, exactly as the mod declares it.
    pub name: String,
    /// Every value the property can take, in the mod's own order.
    pub values: Vec<String>,
}

/// Something a block yields when it is broken.
///
/// A crop drops its essence only when grown, and its seed whatever state it is in, so a
/// drop names the states it applies to rather than the block as a whole.
pub struct BlockDrop {
    /// Namespaced id of the item, e.g. `examplemod:ruby`.
    pub item: String,
    /// Fewest dropped. A drop that can yield nothing has a minimum of zero.
    pub min: u8,
    /// Most dropped. Anything between this and the minimum is equally likely.
    pub max: u8,
    /// Index of the first state this applies to, into the state list the properties
    /// describe. Left unset it applies to every state.
    pub from_state: Option<u32>,
    /// Index of the last state this applies to, inclusive. Left unset it runs to the end.
    pub to_state: Option<u32>,
}

/// A block to add to the server's registry.
pub struct BlockSpec {
    /// Namespaced id, e.g. `examplemod:ruby_block`. The `minecraft` namespace is reserved.
    pub id: String,
    /// Vanilla block whose definition and block states are copied, e.g. `stone`.
    pub template: String,
    /// Replaces the template's hardness when set.
    pub hardness: Option<f32>,
    /// Replaces the template's blast resistance when set.
    pub blast_resistance: Option<f32>,
    /// Replaces the template's light emission, 0 to 15, when set.
    pub luminance: Option<u8>,
    /// Whether a tool is needed to get anything out of the block, when set.
    ///
    /// Left unset the template's rule applies, which is the template's and not the
    /// block's: a machine standing in for stone is mined like stone.
    pub requires_tool: Option<bool>,
    /// The properties the block's states vary over.
    ///
    /// The number of states is the product of the value counts, and it has to match the
    /// client's or the two ends disagree about which state is which. Leave this empty for
    /// a block with a single state; the template's own states are used only when it is.
    ///
    /// Minecraft orders a block's states by treating the properties as digits of a number,
    /// sorted by name with the first varying slowest. Declare them sorted by name to line
    /// up with the client.
    pub properties: Vec<BlockProperty>,
    /// Index of the state the block is placed in, into the state list the properties
    /// describe. Out-of-range values fall back to the first state.
    pub default_state: u32,
    /// The namespaced id of the item that places this block, if it has one.
    ///
    /// Register the item first. Not every block has one — a crop is placed by its seeds —
    /// and without this nothing can place the block, because the item a template carried
    /// belongs to the template.
    pub item: Option<String>,
    /// What the block yields when broken.
    ///
    /// A block with none drops nothing: the template's own drops belong to the template,
    /// and a registered block that inherited them would drop the wrong item.
    pub drops: Vec<BlockDrop>,
    /// The namespaced id of the block entity type placing this block creates, if any.
    ///
    /// Register the type first. A block without one has nowhere to keep anything: a
    /// machine's contents would live only in whatever window happened to be open and go
    /// when it closed. Leaving this unset inherits the template's, which is what a block
    /// standing in for a plain material wants.
    pub block_entity: Option<String>,
}

/// What registering a block spec produces, beyond the id every caller gets back.
///
/// Kept crate-private: turning this into behaviour means reaching a live
/// [`crate::server::Server`], and how a loader reaches one is its own business — the wasm
/// host keeps it in per-plugin state, and a JVM host will have its own way of getting there.
/// `first_state` especially cannot be recovered once [`register_block_spec`] has returned:
/// the dynamic registry only answers state queries once every plugin has loaded and the
/// registry freezes, so a caller that asked for it afterwards would silently be told
/// nothing.
pub(crate) struct RegisteredBlockSpec {
    pub(crate) block_id: BlockId,
    pub(crate) first_state: u16,
    pub(crate) drops: Vec<crate::plugin::api::block_behaviour::BlockDrop>,
}

/// Registers a block built from a vanilla template, returning the id it was assigned.
///
/// Errors are strings rather than a typed error because every caller — wasm, JVM — hands
/// them straight back to a plugin that can only log them.
pub fn register_block_spec(spec: &BlockSpec) -> Result<u32, String> {
    register_block_spec_with_behaviour(spec)
        .map(|registered| u32::from(registered.block_id.as_u16()))
}

/// Does everything [`register_block_spec`] does, plus everything a loader needs to give the
/// block real behaviour. See [`RegisteredBlockSpec`] for why that is not just folded into a
/// richer public return type.
pub(crate) fn register_block_spec_with_behaviour(
    spec: &BlockSpec,
) -> Result<RegisteredBlockSpec, String> {
    let Some(template) = Block::from_name(&spec.template) else {
        return Err(unknown_template("block", &spec.template));
    };

    // The properties decide how many states there are, because that count has to match
    // the client's. Only a block that declares none falls back to the template's own.
    let state_count = spec
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
    let block_entity_type = match spec.block_entity {
        // Registered in the same pass as the blocks, so it is still staged.
        Some(ref name) => match pumpkin_data::dynamic::registering_block_entity_type_id(name) {
            Some(id) => Some(id),
            None => return Err(unknown_template("block entity type", name)),
        },
        None => None,
    };

    let sources = template_states(template, state_count, spec.properties.is_empty());

    let states: Vec<BlockState> = sources
        .iter()
        .map(|state| copy_state(state, spec, block_entity_type))
        .collect();

    let default_state_index = if spec.properties.is_empty() {
        // The template's default sits at a known offset in its own list, and the copy
        // preserves the order, so the same offset selects the copy's default.
        template
            .states
            .iter()
            .position(|state| state.id == template.default_state.id)
            .unwrap_or(0)
    } else {
        (spec.default_state as usize).min(states.len().saturating_sub(1))
    };

    let block = Block {
        hardness: spec.hardness.unwrap_or(template.hardness),
        blast_resistance: spec.blast_resistance.unwrap_or(template.blast_resistance),
        ..template.clone()
    };

    // Resolving by name means the plugin registers the item first and simply names it,
    // rather than having to thread ids around.
    let item_id = match spec.item {
        Some(ref name) => {
            // Generated items resolve by name; one registered moments ago is still
            // staged, so the registry is asked rather than the published tables.
            let id = Item::from_registry_key(name)
                .map(|item| item.id)
                .or_else(|| pumpkin_data::dynamic::registering_item_id(name));

            match id {
                Some(id) => Some(id),
                None => return Err(unknown_template("item", name)),
            }
        }
        None => None,
    };

    let properties = spec
        .properties
        .iter()
        .map(|property| (property.name.clone(), property.values.clone()))
        .collect();

    let drops = resolve_drops(&spec.drops).map_err(|name| unknown_template("item", &name))?;

    let registered = register_block(BlockRegistration {
        name: spec.id.clone(),
        block,
        states,
        default_state_index,
        item_id,
        properties,
    })
    .map_err(|err| err.to_string())?;

    Ok(RegisteredBlockSpec {
        block_id: registered.block_id,
        first_state: registered.first_state.as_u16(),
        drops,
    })
}

/// `BlockState` is not `Clone`, and copying it by hand is what lets a registered block
/// inherit a template's full state list.
fn copy_state(
    template: &BlockState,
    spec: &BlockSpec,
    block_entity_type: Option<u16>,
) -> BlockState {
    let luminance = spec.luminance;
    // Mining reads the state's hardness, not the block's, so a definition that set only
    // the block would break at whatever speed its template did.
    let hardness = spec.hardness.unwrap_or(template.hardness);
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

    match spec.requires_tool {
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
    declared: &[BlockDrop],
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
    use super::{BlockSpec, register_block_spec};
    use pumpkin_data::{Block, BlockStateId};

    /// A registration that overrides nothing, so every copied state keeps the template's.
    fn plain() -> BlockSpec {
        BlockSpec {
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

    fn lit(luminance: u8) -> BlockSpec {
        BlockSpec {
            luminance: Some(luminance),
            ..plain()
        }
    }

    #[test]
    fn copied_states_inherit_the_template() {
        let template = Block::STONE.default_state;
        let copy = super::copy_state(template, &plain(), None);

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

        assert_eq!(super::copy_state(template, &lit(7), None).luminance, 7);
        assert_eq!(
            super::copy_state(template, &lit(200), None).luminance,
            15,
            "light level cannot exceed 15"
        );
    }

    #[test]
    fn hardness_and_tool_rules_reach_the_states_mining_reads() {
        let template = Block::STONE.default_state;

        let softer = BlockSpec {
            hardness: Some(3.5),
            requires_tool: Some(true),
            ..plain()
        };
        let copy = super::copy_state(template, &softer, None);
        assert!((copy.hardness - 3.5).abs() < f32::EPSILON);
        assert!(copy.tool_required());

        // Mining reads the state, so a definition that set only the block would break at
        // whatever speed its template did.
        let freed = BlockSpec {
            requires_tool: Some(false),
            ..plain()
        };
        assert!(!super::copy_state(template, &freed, None).tool_required());
    }

    #[test]
    fn a_state_copied_from_a_crop_is_still_randomly_ticked() {
        // Growing is the one thing a crop does on its own, and it only gets the chance if
        // the copy carries the template's ticking.
        let wheat = Block::WHEAT.default_state;
        assert!(
            wheat.has_random_ticks(),
            "test needs a template that is randomly ticked"
        );
        assert!(super::copy_state(wheat, &plain(), None).has_random_ticks());
    }

    #[test]
    fn a_template_with_many_states_copies_all_of_them() {
        // Stone has one state; something with properties exercises the list copy.
        let template = Block::OAK_LOG;
        assert!(template.states.len() > 1, "test needs a multi-state block");

        let copies: Vec<_> = template
            .states
            .iter()
            .map(|state| super::copy_state(state, &plain(), None))
            .collect();

        assert_eq!(copies.len(), template.states.len());
        for (copy, original) in copies.iter().zip(template.states) {
            assert_eq!(copy.state_flags, original.state_flags);
        }
    }

    #[test]
    fn an_unknown_template_is_reported_rather_than_registered() {
        let spec = BlockSpec {
            id: "testmod:ruby_block".to_owned(),
            template: "definitely_not_a_block".to_owned(),
            hardness: None,
            blast_resistance: None,
            luminance: None,
            requires_tool: None,
            properties: Vec::new(),
            default_state: 0,
            item: None,
            drops: Vec::new(),
            block_entity: None,
        };

        let error = register_block_spec(&spec).expect_err("an unknown template cannot register");
        assert!(error.contains("definitely_not_a_block"), "got: {error}");
    }
}
