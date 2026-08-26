//! Runtime-extensible block registry.
//!
//! Vanilla content is generated at build time into fixed tables, and that stays true:
//! ids `0..BASE_BLOCK_COUNT` are resolved by indexing a `static` array exactly as before.
//! This module owns everything above that range, so content that only exists at runtime
//! — a mod loader's blocks, a plugin's blocks — can be given real ids without the
//! generated tables knowing about it.
//!
//! # Lifecycle
//!
//! Registration is a startup-only activity with two phases:
//!
//! 1. **Open.** [`register_block`] appends entries and hands back the assigned [`BlockId`].
//! 2. **Frozen.** [`freeze`] publishes the tables. Lookups see the new content from this
//!    point on, and further registration fails.
//!
//! Freezing before any world or connection work means readers never synchronise: the
//! published tables are immutable, so a lookup is an index into a slice behind a
//! [`OnceLock`], and the vanilla range never touches that lock at all.
//!
//! Entries are leaked deliberately. Their `&'static` lifetime is what lets dynamic blocks
//! flow through the same `&'static Block` API as generated ones, and a registry that only
//! grows during startup and lives until exit has nothing to reclaim.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::{Block, BlockId, BlockState, BlockStateId};

/// A block to be added to the registry, along with all of its states.
///
/// The `id` on `block`, and the `id` on every state, are placeholders: the registry
/// assigns them. Everything else is used verbatim.
pub struct BlockRegistration {
    /// Namespaced name, e.g. `examplemod:ruby_block`. Must contain a namespace, and must
    /// not use `minecraft`, which is reserved for generated content.
    pub name: String,
    /// The block definition. `id`, `name`, `default_state` and `states` are overwritten.
    pub block: Block,
    /// Every state this block can take. Ids are assigned in order.
    pub states: Vec<BlockState>,
    /// Index into `states` for the state used when the block is placed plainly.
    pub default_state_index: usize,
}

/// Why a registration was rejected.
#[derive(Debug, PartialEq, Eq)]
pub enum RegistryError {
    /// [`freeze`] has already run.
    Frozen,
    /// The name is already taken by a generated or previously registered block.
    DuplicateName(String),
    /// The name has no namespace, or uses the reserved `minecraft` namespace.
    InvalidName(String),
    /// The block declared no states, or its default index is out of range.
    InvalidStates(String),
    /// Adding this block would push an id past what the protocol can carry.
    OutOfIds(String),
}

impl std::fmt::Display for RegistryError {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match self {
            Self::Frozen => write!(f, "the block registry is frozen"),
            Self::DuplicateName(name) => write!(f, "block {name} is already registered"),
            Self::InvalidName(name) => write!(
                f,
                "block name {name} must be namespaced and must not use the reserved \
                 `minecraft` namespace"
            ),
            Self::InvalidStates(name) => {
                write!(f, "block {name} has no states, or an invalid default state")
            }
            Self::OutOfIds(name) => write!(f, "no id space left to register block {name}"),
        }
    }
}

impl std::error::Error for RegistryError {}

/// The published registry. Written once by [`freeze`], read everywhere after.
struct FrozenBlocks {
    /// Indexed by `block_id - BASE_BLOCK_COUNT`.
    blocks: Vec<&'static Block>,
    /// Indexed by `state_id - BASE_STATE_COUNT`.
    states: Vec<&'static BlockState>,
    /// The owning block of each dynamic state, parallel to `states`.
    state_owners: Vec<BlockId>,
    by_name: HashMap<&'static str, &'static Block>,
}

/// Entries accepted but not yet published.
struct Staging {
    blocks: Vec<&'static Block>,
    states: Vec<&'static BlockState>,
    state_owners: Vec<BlockId>,
    names: HashMap<String, ()>,
}

static STAGING: Mutex<Option<Staging>> = Mutex::new(None);
static FROZEN: OnceLock<FrozenBlocks> = OnceLock::new();

/// The number of generated blocks. Dynamic ids start here.
#[must_use]
pub fn base_block_count() -> u16 {
    BlockId::BASE_COUNT
}

/// The number of generated block states. Dynamic state ids start here.
#[must_use]
pub fn base_state_count() -> u16 {
    BlockStateId::BASE_COUNT
}

/// Total blocks, generated plus registered.
#[must_use]
pub fn block_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.blocks.len());
    BlockId::BASE_COUNT.saturating_add(extra as u16)
}

/// Total block states, generated plus registered.
#[must_use]
pub fn state_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.states.len());
    BlockStateId::BASE_COUNT.saturating_add(extra as u16)
}

/// Whether the registry has been published.
#[must_use]
pub fn is_frozen() -> bool {
    FROZEN.get().is_some()
}

/// Adds a block to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, the states are malformed, or the id space is exhausted.
pub fn register_block(registration: BlockRegistration) -> Result<BlockId, RegistryError> {
    if is_frozen() {
        return Err(RegistryError::Frozen);
    }

    let BlockRegistration {
        name,
        block,
        mut states,
        default_state_index,
    } = registration;

    validate_name(&name)?;
    if states.is_empty() || default_state_index >= states.len() {
        return Err(RegistryError::InvalidStates(name));
    }

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(|| Staging {
        blocks: Vec::new(),
        states: Vec::new(),
        state_owners: Vec::new(),
        names: HashMap::new(),
    });

    if staging.names.contains_key(&name) || Block::from_name(&name).is_some() {
        return Err(RegistryError::DuplicateName(name));
    }

    let block_id = u16::try_from(BlockId::BASE_COUNT as usize + staging.blocks.len())
        .ok()
        .map(BlockId::from_raw)
        .ok_or_else(|| RegistryError::OutOfIds(name.clone()))?;

    let first_state = BlockStateId::BASE_COUNT as usize + staging.states.len();
    let last_state = first_state
        .checked_add(states.len() - 1)
        .filter(|last| u16::try_from(*last).is_ok())
        .ok_or_else(|| RegistryError::OutOfIds(name.clone()))?;
    debug_assert!(last_state >= first_state);

    for (offset, state) in states.iter_mut().enumerate() {
        // `first_state + offset <= last_state`, which was just range-checked.
        #[allow(clippy::cast_possible_truncation)]
        let id = (first_state + offset) as u16;
        state.id = BlockStateId::from_raw(id);
    }

    // Leaked on purpose: see the module docs. The registry outlives every reader.
    let name: &'static str = Box::leak(name.into_boxed_str());
    let states: &'static [BlockState] = Box::leak(states.into_boxed_slice());
    let default_state = &states[default_state_index];

    let block: &'static Block = Box::leak(Box::new(Block {
        id: block_id,
        name,
        default_state,
        states,
        ..block
    }));

    staging.blocks.push(block);
    staging.states.extend(states.iter());
    staging
        .state_owners
        .extend(std::iter::repeat_n(block_id, states.len()));
    staging.names.insert(name.to_string(), ());

    Ok(block_id)
}

/// Publishes every registered block. Later registrations fail.
///
/// Calling this more than once is harmless; only the first call publishes.
pub fn freeze() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take();

    let staged = staged.unwrap_or_else(|| Staging {
        blocks: Vec::new(),
        states: Vec::new(),
        state_owners: Vec::new(),
        names: HashMap::new(),
    });

    let by_name = staged
        .blocks
        .iter()
        .map(|block| (block.name, *block))
        .collect();

    // Ignores the result: a second freeze leaves the first publication in place.
    let _ = FROZEN.set(FrozenBlocks {
        blocks: staged.blocks,
        states: staged.states,
        state_owners: staged.state_owners,
        by_name,
    });
}

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

/// Resolves a block id at or above [`base_block_count`].
// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
#[must_use]
pub fn block_from_id(id: BlockId) -> Option<&'static Block> {
    let index = usize::from(id.as_u16()).checked_sub(usize::from(BlockId::BASE_COUNT))?;
    FROZEN.get()?.blocks.get(index).copied()
}

/// Resolves a state id at or above [`base_state_count`].
// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
#[must_use]
pub fn state_from_id(id: BlockStateId) -> Option<&'static BlockState> {
    let index = usize::from(id.as_u16()).checked_sub(usize::from(BlockStateId::BASE_COUNT))?;
    FROZEN.get()?.states.get(index).copied()
}

/// Finds the block owning a state id at or above [`base_state_count`].
// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
#[must_use]
pub fn block_id_from_state_id(id: BlockStateId) -> Option<BlockId> {
    let index = usize::from(id.as_u16()).checked_sub(usize::from(BlockStateId::BASE_COUNT))?;
    FROZEN.get()?.state_owners.get(index).copied()
}

/// Finds a registered block by its namespaced name.
#[inline]
#[must_use]
pub fn block_from_name(name: &str) -> Option<&'static Block> {
    FROZEN.get()?.by_name.get(name).copied()
}

#[cfg(test)]
mod tests {
    use super::*;

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

    /// The whole lifecycle lives in one test: the registry is process-global and freezes
    /// exactly once, so splitting this up would make the assertions order-dependent.
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

        assert!(is_frozen());
        assert_eq!(
            register_block(sample("examplemod:too_late", 1)),
            Err(RegistryError::Frozen)
        );
    }
}
