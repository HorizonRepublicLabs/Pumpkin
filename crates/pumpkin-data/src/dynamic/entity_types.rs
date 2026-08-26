//! Runtime-registered entity types.
//!
//! Ids from [`EntityType::BASE_COUNT`] up live here. See the [module docs](super) for the
//! lifecycle and for why entries are leaked.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::entity::EntityType;

use super::{RegistryError, validate_name};

/// An entity type to be added to the registry.
///
/// The `id` and `resource_name` on `entity_type` are placeholders: the registry assigns
/// them. Everything else is used verbatim.
pub struct EntityTypeRegistration {
    /// Namespaced name, e.g. `examplemod:ruby_golem`. Must contain a namespace, and must
    /// not use `minecraft`, which is reserved for generated content.
    pub name: String,
    /// The entity type definition. `id` and `resource_name` are overwritten.
    pub entity_type: EntityType,
}

/// The published registry. Written once by [`publish`], read everywhere after.
struct FrozenEntityTypes {
    /// Indexed by `id - EntityType::BASE_COUNT`.
    entity_types: Vec<&'static EntityType>,
    by_name: HashMap<&'static str, &'static EntityType>,
}

/// Entries accepted but not yet published.
struct Staging {
    entity_types: Vec<&'static EntityType>,
    names: HashMap<String, ()>,
}

static STAGING: Mutex<Option<Staging>> = Mutex::new(None);
static FROZEN: OnceLock<FrozenEntityTypes> = OnceLock::new();

/// The number of generated entity types. Runtime ids start here.
#[must_use]
pub fn base_entity_type_count() -> u16 {
    EntityType::BASE_COUNT
}

/// Total entity types, generated plus registered.
#[must_use]
pub fn entity_type_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.entity_types.len());
    EntityType::BASE_COUNT.saturating_add(extra as u16)
}

/// Every registered entity type, in id order. Empty until [`super::freeze`] has run.
#[must_use]
pub fn registered_entity_types() -> &'static [&'static EntityType] {
    FROZEN.get().map_or(&[], |frozen| &frozen.entity_types)
}

/// Adds an entity type to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, or the id space is exhausted.
pub fn register_entity_type(registration: EntityTypeRegistration) -> Result<u16, RegistryError> {
    if super::is_frozen() {
        return Err(RegistryError::Frozen);
    }

    let EntityTypeRegistration { name, entity_type } = registration;
    validate_name(&name)?;

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(|| Staging {
        entity_types: Vec::new(),
        names: HashMap::new(),
    });

    if staging.names.contains_key(&name) || EntityType::from_name(&name).is_some() {
        return Err(RegistryError::DuplicateName(name));
    }

    let id = u16::try_from(EntityType::BASE_COUNT as usize + staging.entity_types.len())
        .map_err(|_| RegistryError::OutOfIds(name.clone()))?;

    // Leaked on purpose: see the module docs. The registry outlives every reader.
    let resource_name: &'static str = Box::leak(name.into_boxed_str());
    let entity_type: &'static EntityType = Box::leak(Box::new(EntityType {
        id,
        resource_name,
        ..entity_type
    }));

    staging.entity_types.push(entity_type);
    staging.names.insert(resource_name.to_string(), ());

    Ok(id)
}

/// Publishes every staged entity type. Called by [`super::freeze`].
pub(super) fn publish() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take();

    let staged = staged.unwrap_or_else(|| Staging {
        entity_types: Vec::new(),
        names: HashMap::new(),
    });

    let by_name = staged
        .entity_types
        .iter()
        .map(|entity_type| (entity_type.resource_name, *entity_type))
        .collect();

    // Ignores the result: a second publish leaves the first in place.
    let _ = FROZEN.set(FrozenEntityTypes {
        entity_types: staged.entity_types,
        by_name,
    });
}

/// Resolves an entity type id at or above [`base_entity_type_count`].
// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
#[must_use]
pub fn entity_type_from_id(id: u16) -> Option<&'static EntityType> {
    let index = usize::from(id).checked_sub(usize::from(EntityType::BASE_COUNT))?;
    FROZEN.get()?.entity_types.get(index).copied()
}

/// Finds a registered entity type by its namespaced name.
#[cold]
#[inline(never)]
#[must_use]
pub fn entity_type_from_name(name: &str) -> Option<&'static EntityType> {
    FROZEN.get()?.by_name.get(name).copied()
}
