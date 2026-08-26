//! Runtime-registered blocks.
//!
//! Ids from [`BlockId::BASE_COUNT`] up live here. See the [module docs](super) for the
//! lifecycle and for why entries are leaked.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::{Block, BlockId, BlockState, BlockStateId};

use super::{RegistryError, validate_name};

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

/// Adds a block to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, the states are malformed, or the id space is exhausted.
pub fn register_block(registration: BlockRegistration) -> Result<BlockId, RegistryError> {
    if super::is_frozen() {
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

/// Publishes every staged block. Called by [`super::freeze`].
pub(super) fn publish() {
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
