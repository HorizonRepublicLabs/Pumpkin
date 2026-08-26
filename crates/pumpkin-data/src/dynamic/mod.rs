//! Runtime-extensible registries.
//!
//! Vanilla content is generated at build time into fixed tables, and that stays true: ids
//! below the generated count resolve exactly as before, by indexing a `static` array or
//! falling through a generated `match`. This module owns everything above that range, so
//! content that only exists at runtime — a mod loader's content, a plugin's — can be given
//! real ids without the generated tables knowing about it.
//!
//! # Lifecycle
//!
//! Registration is a startup-only activity with two phases:
//!
//! 1. **Open.** The `register_*` functions append entries and hand back the id each was
//!    assigned.
//! 2. **Frozen.** [`freeze`] publishes every registry at once. Lookups see the new content
//!    from this point on, and further registration fails.
//!
//! Freezing before any world or connection work means readers never synchronise: the
//! published tables are immutable, so a lookup is an index into a slice behind a
//! [`OnceLock`](std::sync::OnceLock), and the generated range never touches that lock.
//!
//! Entries are leaked deliberately. Their `&'static` lifetime is what lets runtime content
//! flow through the same `&'static` API as generated content, and
//! a registry that only grows during startup and lives until exit has nothing to reclaim.
//!
//! # Feature gates
//!
//! Each registry is gated on the `pumpkin-data` feature that provides its type — `block`,
//! `item`, `entity_type`, `fluid` — because crates in this workspace enable them
//! independently.

use std::sync::OnceLock;

#[cfg(feature = "block")]
mod block_entity_types;
#[cfg(feature = "block")]
mod blocks;
#[cfg(feature = "entity_type")]
mod entity_types;
#[cfg(feature = "fluid")]
mod fluids;
#[cfg(feature = "item")]
mod items;

#[cfg(feature = "block")]
pub use block_entity_types::{
    base_block_entity_type_count, block_entity_type_count, block_entity_type_id,
    block_entity_type_name, is_block_entity_type, register_block_entity_type,
};
#[cfg(feature = "block")]
pub use blocks::{
    BlockRegistration, base_block_count, base_state_count, block_count, block_from_id,
    block_from_name, block_id_from_state_id, register_block, state_count, state_from_id,
};
#[cfg(feature = "entity_type")]
pub use entity_types::{
    EntityTypeRegistration, base_entity_type_count, entity_type_count, entity_type_from_id,
    entity_type_from_name, register_entity_type, registered_entity_types,
};
#[cfg(feature = "fluid")]
pub use fluids::{
    FluidRegistration, base_fluid_count, fluid_count, fluid_from_id, fluid_from_name,
    register_fluid,
};
#[cfg(feature = "item")]
pub use items::{
    ItemRegistration, base_item_count, item_count, item_from_id, item_from_name, register_item,
};

/// Set once [`freeze`] has run. Registration checks this; lookups check their own tables.
static FROZEN: OnceLock<()> = OnceLock::new();

/// Why a registration was rejected.
#[derive(Debug, PartialEq, Eq)]
pub enum RegistryError {
    /// [`freeze`] has already run.
    Frozen,
    /// The name is already taken by generated or previously registered content.
    DuplicateName(String),
    /// The name has no namespace, or uses the reserved `minecraft` namespace.
    InvalidName(String),
    /// A block declared no states, or its default index is out of range.
    InvalidStates(String),
    /// Adding this entry would push an id past what the protocol can carry.
    OutOfIds(String),
}

impl std::fmt::Display for RegistryError {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match self {
            Self::Frozen => write!(f, "the registry is frozen"),
            Self::DuplicateName(name) => write!(f, "{name} is already registered"),
            Self::InvalidName(name) => write!(
                f,
                "{name} must be namespaced and must not use the reserved `minecraft` \
                 namespace"
            ),
            Self::InvalidStates(name) => {
                write!(f, "block {name} has no states, or an invalid default state")
            }
            Self::OutOfIds(name) => write!(f, "no id space left to register {name}"),
        }
    }
}

impl std::error::Error for RegistryError {}

/// Whether the registries have been published.
#[must_use]
pub fn is_frozen() -> bool {
    FROZEN.get().is_some()
}

/// Publishes everything registered so far. Later registrations fail.
///
/// Calling this more than once is harmless; only the first call publishes.
pub fn freeze() {
    #[cfg(feature = "block")]
    block_entity_types::publish();
    #[cfg(feature = "block")]
    blocks::publish();
    #[cfg(feature = "entity_type")]
    entity_types::publish();
    #[cfg(feature = "fluid")]
    fluids::publish();
    #[cfg(feature = "item")]
    items::publish();

    // Set last: registration reads this, and must keep failing only after the tables are
    // in place rather than during the window where they are half-published.
    let _ = FROZEN.set(());
}

/// Rejects names that are unnamespaced or claim the namespace reserved for generated
/// content.
fn validate_name(name: &str) -> Result<(), RegistryError> {
    match name.split_once(':') {
        Some((namespace, path))
            if !namespace.is_empty() && !path.is_empty() && namespace != "minecraft" =>
        {
            Ok(())
        }
        _ => Err(RegistryError::InvalidName(name.to_string())),
    }
}

#[cfg(all(
    test,
    feature = "block",
    feature = "item",
    feature = "entity_type",
    feature = "fluid"
))]
mod tests {
    use super::*;
    use crate::{
        Block, BlockId, BlockState, BlockStateId, entity::EntityType, fluid::Fluid, item::Item,
    };

    fn sample_state() -> BlockState {
        let template = Block::STONE.default_state;
        BlockState {
            // Replaced by the registry.
            id: BlockStateId::AIR,
            state_flags: template.state_flags,
            side_flags: template.side_flags,
            instrument: template.instrument,
            luminance: template.luminance,
            piston_behavior: template.piston_behavior.clone(),
            hardness: template.hardness,
            collision_shapes: template.collision_shapes,
            outline_shapes: template.outline_shapes,
            opacity: template.opacity,
            block_entity_type: template.block_entity_type,
        }
    }

    fn sample(name: &str, state_count: usize) -> BlockRegistration {
        BlockRegistration {
            name: name.to_string(),
            block: Block::STONE.clone(),
            states: (0..state_count).map(|_| sample_state()).collect(),
            default_state_index: 0,
        }
    }

    /// The whole lifecycle lives in one test: the registries are process-global and freeze
    /// exactly once, so splitting this up would make the assertions order-dependent. Items
    /// ride along for the same reason.
    #[test]
    fn registry_lifecycle() {
        assert_eq!(
            register_block(sample("ruby_block", 1)),
            Err(RegistryError::InvalidName("ruby_block".to_string())),
            "a name without a namespace is rejected"
        );
        assert_eq!(
            register_block(sample("minecraft:ruby_block", 1)),
            Err(RegistryError::InvalidName(
                "minecraft:ruby_block".to_string()
            )),
            "the generated namespace is reserved"
        );
        assert_eq!(
            register_block(sample("examplemod:stateless", 0)),
            Err(RegistryError::InvalidStates(
                "examplemod:stateless".to_string()
            )),
            "a block must have at least one state"
        );

        let base_blocks = BlockId::BASE_COUNT;
        let base_states = BlockStateId::BASE_COUNT;

        let id = register_block(sample("examplemod:ruby_block", 2)).expect("registration succeeds");
        assert_eq!(
            id.as_u16(),
            base_blocks,
            "ids continue after generated data"
        );

        assert_eq!(
            register_block(sample("examplemod:ruby_block", 1)),
            Err(RegistryError::DuplicateName(
                "examplemod:ruby_block".to_string()
            ))
        );

        assert!(
            Block::from_name("examplemod:ruby_block").is_none(),
            "registrations are invisible until the registry is frozen"
        );

        let item_id = register_item(ItemRegistration {
            name: "examplemod:ruby".to_string(),
            item: Item::DIAMOND.clone(),
        })
        .expect("registration succeeds");
        assert_eq!(
            item_id,
            Item::BASE_COUNT,
            "ids continue after generated data"
        );
        assert_eq!(
            register_item(ItemRegistration {
                name: "minecraft:ruby".to_string(),
                item: Item::DIAMOND.clone(),
            }),
            Err(RegistryError::InvalidName("minecraft:ruby".to_string()))
        );
        assert!(
            Item::from_id(item_id).is_none(),
            "registrations are invisible until the registry is frozen"
        );

        let entity_id = register_entity_type(EntityTypeRegistration {
            name: "examplemod:ruby_golem".to_string(),
            entity_type: EntityType::ALLAY.clone(),
        })
        .expect("registration succeeds");
        assert_eq!(
            entity_id,
            EntityType::BASE_COUNT,
            "ids continue after generated data"
        );
        assert!(
            EntityType::from_raw(entity_id).is_none(),
            "registrations are invisible until the registry is frozen"
        );

        let fluid_id = register_fluid(FluidRegistration {
            name: "examplemod:quicksilver".to_string(),
            fluid: Fluid::WATER.clone(),
            states: Fluid::WATER.states.to_vec(),
            default_state_index: 0,
        })
        .expect("registration succeeds");
        assert_eq!(
            fluid_id,
            Fluid::BASE_COUNT,
            "ids continue after generated data"
        );
        assert_eq!(
            register_fluid(FluidRegistration {
                name: "examplemod:stateless".to_string(),
                fluid: Fluid::WATER.clone(),
                states: Vec::new(),
                default_state_index: 0,
            }),
            Err(RegistryError::InvalidStates(
                "examplemod:stateless".to_string()
            )),
            "a fluid must have at least one state"
        );

        let base_block_entities = base_block_entity_type_count();
        let block_entity_id = register_block_entity_type("examplemod:ruby_furnace".to_string())
            .expect("registration succeeds");
        assert_eq!(block_entity_id, base_block_entities);
        assert_eq!(
            register_block_entity_type("furnace".to_string()),
            Err(RegistryError::InvalidName("furnace".to_string())),
            "generated names are unnamespaced, so they can never collide"
        );

        freeze();

        let block = Block::from_name("examplemod:ruby_block").expect("registered block resolves");
        assert_eq!(block.id, id);
        assert_eq!(Block::from_id(id).name, "examplemod:ruby_block");
        assert_eq!(
            Block::from_registry_key("examplemod:ruby_block").map(|found| found.id),
            Some(id)
        );

        assert_eq!(block.states.len(), 2);
        for (offset, state) in block.states.iter().enumerate() {
            let state_id = state.id;
            assert_eq!(state_id.as_u16(), base_states + offset as u16);
            assert_eq!(BlockState::from_id(state_id).id, state_id);
            assert_eq!(BlockId::from_state_id(state_id), id);
            assert_eq!(Block::from_state_id(state_id).id, id);
        }
        assert_eq!(block.default_state.id, block.states[0].id);

        assert_eq!(BlockId::count(), base_blocks + 1);
        assert_eq!(BlockStateId::count(), base_states + 2);
        assert!(BlockId::new(base_blocks).is_some());
        assert!(BlockId::new(base_blocks + 1).is_none());

        // Generated content resolves exactly as before.
        assert_eq!(
            Block::from_name("stone").map(|b| b.id),
            Some(Block::STONE.id)
        );
        assert_eq!(Block::from_id(Block::STONE.id).name, "stone");
        assert_eq!(BlockState::from_id(BlockStateId::AIR).id, BlockStateId::AIR);

        let item = Item::from_id(item_id).expect("registered item resolves");
        assert_eq!(item.registry_key, "examplemod:ruby");
        assert_eq!(
            Item::from_registry_key("examplemod:ruby").map(|found| found.id),
            Some(item_id)
        );
        assert_eq!(item_count(), Item::BASE_COUNT + 1);

        // Generated items resolve exactly as before.
        assert_eq!(
            Item::from_registry_key("diamond").map(|found| found.id),
            Some(Item::DIAMOND.id)
        );
        assert_eq!(
            Item::from_id(Item::DIAMOND.id).map(|found| found.registry_key),
            Some("diamond")
        );

        let entity = EntityType::from_raw(entity_id).expect("registered entity type resolves");
        assert_eq!(entity.resource_name, "examplemod:ruby_golem");
        assert_eq!(
            EntityType::from_name("examplemod:ruby_golem").map(|found| found.id),
            Some(entity_id)
        );
        assert_eq!(entity_type_count(), EntityType::BASE_COUNT + 1);
        assert_eq!(
            EntityType::all().len(),
            EntityType::ALL.len() + 1,
            "all() covers generated and registered types"
        );

        // Generated entity types resolve exactly as before.
        assert_eq!(
            EntityType::from_name("allay").map(|found| found.id),
            Some(EntityType::ALLAY.id)
        );
        assert_eq!(
            EntityType::from_raw(EntityType::ALLAY.id).map(|found| found.resource_name),
            Some("allay")
        );

        let fluid = Fluid::from_id(fluid_id).expect("registered fluid resolves");
        assert_eq!(fluid.name, "examplemod:quicksilver");
        assert_eq!(
            Fluid::from_registry_key("examplemod:quicksilver").map(|found| found.id),
            Some(fluid_id)
        );
        assert_eq!(fluid_count(), Fluid::BASE_COUNT + 1);
        assert_eq!(
            Fluid::from_registry_key("water").map(|found| found.id),
            Some(Fluid::WATER.id),
            "generated fluids resolve exactly as before"
        );

        assert_eq!(
            block_entity_type_name(block_entity_id),
            Some("examplemod:ruby_furnace")
        );
        assert_eq!(
            block_entity_type_id("examplemod:ruby_furnace"),
            Some(block_entity_id)
        );
        assert!(is_block_entity_type("examplemod:ruby_furnace"));
        assert_eq!(
            block_entity_type_id("furnace"),
            Some(0),
            "generated block entity types keep their ids"
        );
        assert_eq!(block_entity_type_count(), base_block_entities + 1);

        assert!(is_frozen());
        assert_eq!(
            register_block(sample("examplemod:too_late", 1)),
            Err(RegistryError::Frozen)
        );
        assert_eq!(
            register_item(ItemRegistration {
                name: "examplemod:too_late".to_string(),
                item: Item::DIAMOND.clone(),
            }),
            Err(RegistryError::Frozen)
        );
        assert_eq!(
            register_entity_type(EntityTypeRegistration {
                name: "examplemod:too_late".to_string(),
                entity_type: EntityType::ALLAY.clone(),
            }),
            Err(RegistryError::Frozen)
        );
        assert_eq!(
            register_block_entity_type("examplemod:too_late".to_string()),
            Err(RegistryError::Frozen)
        );
    }

    #[test]
    fn names_must_be_namespaced_outside_the_reserved_namespace() {
        assert!(validate_name("examplemod:ruby").is_ok());

        for rejected in ["ruby", "minecraft:ruby", ":ruby", "examplemod:", ""] {
            assert_eq!(
                validate_name(rejected),
                Err(RegistryError::InvalidName(rejected.to_string())),
                "{rejected} should be rejected"
            );
        }
    }
}
