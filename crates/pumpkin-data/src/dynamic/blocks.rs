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
/// What a block registration gives back.
#[derive(Clone, Copy, Debug, PartialEq, Eq)]
pub struct Registered {
    /// The id the block was given.
    pub block_id: BlockId,
    /// The id the block's own states start at.
    ///
    /// Anything that names a state by where it sits in the block's list — a drop that
    /// applies to one age of a crop, say — needs this to turn one into the other.
    pub first_state: BlockStateId,
}

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
    /// The properties this block's states vary over, in the order they index states:
    /// sorted by name, first varying slowest, each value list in Minecraft's own order.
    pub properties: Vec<(String, Vec<String>)>,
    /// The item that places this block, if it has one.
    ///
    /// Not every block does — a crop is placed by its seeds, and some have no item at all.
    /// A block that inherited its template's item would be placed by the template's item,
    /// so this is set explicitly rather than copied.
    pub item_id: Option<u16>,
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
    /// The block each linked item places.
    by_item: HashMap<u16, &'static Block>,
    /// The properties of each block, for picking a state by meaning.
    properties: HashMap<BlockId, &'static [(String, Vec<String>)]>,
}

/// Entries accepted but not yet published.
struct Staging {
    blocks: Vec<&'static Block>,
    states: Vec<&'static BlockState>,
    state_owners: Vec<BlockId>,
    names: HashMap<String, ()>,
    properties: Vec<(BlockId, &'static [(String, Vec<String>)])>,
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
pub fn register_block(registration: BlockRegistration) -> Result<Registered, RegistryError> {
    if super::is_frozen() {
        return Err(RegistryError::Frozen);
    }

    let BlockRegistration {
        name,
        block,
        mut states,
        default_state_index,
        item_id,
        properties,
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
        properties: Vec::new(),
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
        item_id: item_id.unwrap_or(0),
        ..block
    }));

    // Leaked with the block, and read whenever something needs to pick a state by meaning
    // rather than by index.
    let properties: &'static [(String, Vec<String>)] = Box::leak(properties.into_boxed_slice());
    staging.properties.push((block_id, properties));

    staging.blocks.push(block);
    staging.states.extend(states.iter());
    staging
        .state_owners
        .extend(std::iter::repeat_n(block_id, states.len()));
    staging.names.insert(name.to_string(), ());

    Ok(Registered {
        block_id,
        // Handed back rather than looked up, because nothing can look it up yet: every
        // accessor reads the published registry, and publishing happens once every plugin
        // has loaded. A caller that asked for it here would silently be told zero.
        first_state: BlockStateId::from_raw(first_state as u16),
    })
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
        properties: Vec::new(),
    });

    let by_name = staged
        .blocks
        .iter()
        .map(|block| (block.name, *block))
        .collect();

    // Zero means the block has no item, so it is not something anything can place.
    let by_item = staged
        .blocks
        .iter()
        .filter(|block| block.item_id != 0)
        .map(|block| (block.item_id, *block))
        .collect();

    // Ignores the result: a second freeze leaves the first publication in place.
    let _ = FROZEN.set(FrozenBlocks {
        properties: staged.properties.into_iter().collect(),
        blocks: staged.blocks,
        states: staged.states,
        state_owners: staged.state_owners,
        by_name,
        by_item,
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

/// Whether a registered state is randomly ticked.
///
/// Answers for the ids the generated bitset does not cover. A state below that range never
/// reaches here, and one that was never registered is not ticked.
// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
#[must_use]
pub fn state_has_random_ticks(id: BlockStateId) -> bool {
    // The state already says so: it was copied from a generated one, flags and all, and
    // the flag and the generated bitset are two spellings of one fact.
    state_from_id(id).is_some_and(crate::BlockState::has_random_ticks)
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

/// The properties a registered block's states vary over.
#[must_use]
pub fn block_properties(id: BlockId) -> Option<&'static [(String, Vec<String>)]> {
    FROZEN.get()?.properties.get(&id).copied()
}

/// The state a registered block takes when its properties have the given values.
///
/// Properties are digits of a mixed-radix number, the first varying slowest, which is how
/// Minecraft numbers states. Anything not named keeps its first value, and an unknown
/// property or value is ignored rather than shifting everything after it.
#[must_use]
pub fn block_state_for(id: BlockId, values: &[(&str, &str)]) -> Option<BlockStateId> {
    let block = block_from_id(id)?;
    let properties = block_properties(id)?;

    let mut offset = 0usize;
    for (name, options) in properties {
        offset *= options.len();
        if let Some((_, wanted)) = values.iter().find(|(key, _)| key == name)
            && let Some(index) = options.iter().position(|value| value == wanted)
        {
            offset += index;
        }
    }

    block.states.get(offset).map(|state| state.id)
}

/// Finds the registered block an item places.
#[cold]
#[inline(never)]
#[must_use]
pub fn block_from_item_id(id: u16) -> Option<&'static Block> {
    FROZEN.get()?.by_item.get(&id).copied()
}

/// Finds a registered block by its namespaced name.
#[inline]
#[must_use]
pub fn block_from_name(name: &str) -> Option<&'static Block> {
    FROZEN.get()?.by_name.get(name).copied()
}
