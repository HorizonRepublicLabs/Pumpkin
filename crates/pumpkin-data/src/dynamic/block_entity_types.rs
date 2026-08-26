//! Runtime-registered block entity types.
//!
//! Unlike the other registries, generated block entity types are a flat list of names
//! indexed by id rather than a table of structs, so this module owns the accessors for both
//! halves: [`block_entity_type_name`] and [`block_entity_type_id`] answer for generated and
//! registered types alike, and callers should use them instead of indexing
//! [`BLOCK_ENTITY_TYPES`](crate::block_properties::BLOCK_ENTITY_TYPES) directly.
//!
//! Registering a type gets it an id and a name — enough for it to survive the protocol —
//! but not an implementation. `create_block_entity` builds concrete Rust types by matching
//! on generated names and will not know a registered one, so behaviour still needs a hook
//! that does not exist yet.
//!
//! See the [module docs](super) for the lifecycle and for why entries are leaked.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::block_properties::BLOCK_ENTITY_TYPES;

use super::{RegistryError, validate_name};

/// The published registry. Written once by [`publish`], read everywhere after.
struct FrozenBlockEntityTypes {
    /// Indexed by `id - base_block_entity_type_count()`.
    names: Vec<&'static str>,
    by_name: HashMap<&'static str, u16>,
}

/// Entries accepted but not yet published.
struct Staging {
    names: Vec<&'static str>,
}

static STAGING: Mutex<Option<Staging>> = Mutex::new(None);
static FROZEN: OnceLock<FrozenBlockEntityTypes> = OnceLock::new();

/// The number of generated block entity types. Runtime ids start here.
#[must_use]
pub fn base_block_entity_type_count() -> u16 {
    // The generated list is far short of `u16::MAX`; the cast cannot lose anything.
    BLOCK_ENTITY_TYPES.len() as u16
}

/// Total block entity types, generated plus registered.
#[must_use]
pub fn block_entity_type_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.names.len());
    base_block_entity_type_count().saturating_add(extra as u16)
}

/// Adds a block entity type to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, or the id space is exhausted.
pub fn register_block_entity_type(name: String) -> Result<u16, RegistryError> {
    if super::is_frozen() {
        return Err(RegistryError::Frozen);
    }

    validate_name(&name)?;

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(|| Staging { names: Vec::new() });

    if staging.names.contains(&name.as_str()) || BLOCK_ENTITY_TYPES.contains(&name.as_str()) {
        return Err(RegistryError::DuplicateName(name));
    }

    let id = u16::try_from(base_block_entity_type_count() as usize + staging.names.len())
        .map_err(|_| RegistryError::OutOfIds(name.clone()))?;

    // Leaked on purpose: see the module docs. The registry outlives every reader.
    let name: &'static str = Box::leak(name.into_boxed_str());
    staging.names.push(name);

    Ok(id)
}

/// Publishes every staged block entity type. Called by [`super::freeze`].
pub(super) fn publish() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take();

    let staged = staged.unwrap_or_else(|| Staging { names: Vec::new() });

    let base = base_block_entity_type_count();
    let by_name = staged
        .names
        .iter()
        .enumerate()
        .map(|(offset, name)| {
            // `offset` is bounded by the id check made at registration.
            (*name, base.saturating_add(offset as u16))
        })
        .collect();

    // Ignores the result: a second publish leaves the first in place.
    let _ = FROZEN.set(FrozenBlockEntityTypes {
        names: staged.names,
        by_name,
    });
}

/// The name of a block entity type, generated or registered.
#[must_use]
pub fn block_entity_type_name(id: u16) -> Option<&'static str> {
    if let Some(name) = BLOCK_ENTITY_TYPES.get(id as usize) {
        return Some(name);
    }
    registered_name(id)
}

/// The id of a block entity type, generated or registered.
///
/// Generated types are matched by their bare name, registered ones by their namespaced one.
#[must_use]
pub fn block_entity_type_id(name: &str) -> Option<u16> {
    if let Some(index) = BLOCK_ENTITY_TYPES
        .iter()
        .position(|candidate| *candidate == name)
    {
        // The generated list is short enough that its indices fit a u16.
        return Some(index as u16);
    }
    registered_id(name)
}

/// Whether a name belongs to any block entity type.
#[must_use]
pub fn is_block_entity_type(name: &str) -> bool {
    block_entity_type_id(name).is_some()
}

// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
fn registered_name(id: u16) -> Option<&'static str> {
    let index = usize::from(id).checked_sub(usize::from(base_block_entity_type_count()))?;
    FROZEN.get()?.names.get(index).copied()
}

#[cold]
#[inline(never)]
fn registered_id(name: &str) -> Option<u16> {
    FROZEN.get()?.by_name.get(name).copied()
}
