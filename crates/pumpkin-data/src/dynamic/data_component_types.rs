//! Runtime-registered data component types.
//!
//! A data component type is a name for a kind of value that can sit on an item stack --
//! `minecraft:max_damage`, `mysticalagriculture:watering_can_active`. Vanilla's are the
//! [`DataComponent`] enum, ids contiguous from zero, so registered names index past its
//! end and the accessors here answer for both halves, like the other flat registries.
//!
//! Registering one gets it an id that can survive the protocol. What it does not get is
//! the value's wire format: parsing a mod component's payload out of a client's creative
//! stack needs the component's codec, which lives in the mod's Java half. Until a slice
//! carries raw payloads through, a registered type is a name the server can acknowledge,
//! not data it can read.
//!
//! See the [module docs](super) for the lifecycle and for why entries are leaked.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::data_component::DataComponent;

use super::{RegistryError, validate_name};

/// The published registry. Written once by [`publish`], read everywhere after.
struct FrozenDataComponentTypes {
    /// Indexed by `id - base_data_component_type_count()`.
    names: Vec<&'static str>,
    by_name: HashMap<&'static str, u16>,
}

/// Entries accepted but not yet published.
struct Staging {
    names: Vec<&'static str>,
}

static STAGING: Mutex<Option<Staging>> = Mutex::new(None);
static FROZEN: OnceLock<FrozenDataComponentTypes> = OnceLock::new();

/// The number of generated data component types. Runtime ids start here.
///
/// Counted by walking [`DataComponent::try_from_id`] rather than hardcoded, so a
/// regeneration that adds vanilla components moves the runtime range automatically.
#[must_use]
pub fn base_data_component_type_count() -> u16 {
    static BASE: OnceLock<u16> = OnceLock::new();
    *BASE.get_or_init(|| {
        (0..=u8::MAX)
            .take_while(|id| DataComponent::try_from_id(*id).is_some())
            .count() as u16
    })
}

/// Total data component types, generated plus registered.
#[must_use]
pub fn data_component_type_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.names.len());
    base_data_component_type_count().saturating_add(extra as u16)
}

/// Adds a data component type to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, or the id space is exhausted.
pub fn register_data_component_type(name: String) -> Result<u16, RegistryError> {
    if super::is_frozen() {
        return Err(RegistryError::Frozen);
    }

    validate_name(&name)?;

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(|| Staging { names: Vec::new() });

    if staging.names.contains(&name.as_str()) || DataComponent::try_from_name(&name).is_some() {
        return Err(RegistryError::DuplicateName(name));
    }

    let id = u16::try_from(base_data_component_type_count() as usize + staging.names.len())
        .map_err(|_| RegistryError::OutOfIds(name.clone()))?;

    // Leaked on purpose: see the module docs. The registry outlives every reader.
    let name: &'static str = Box::leak(name.into_boxed_str());
    staging.names.push(name);

    Ok(id)
}

/// Publishes every staged data component type. Called by [`super::freeze`].
pub(super) fn publish() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take();

    let staged = staged.unwrap_or_else(|| Staging { names: Vec::new() });

    let base = base_data_component_type_count();
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
    let _ = FROZEN.set(FrozenDataComponentTypes {
        names: staged.names,
        by_name,
    });
}

/// The name of a registered data component type. Generated ones keep their enum.
#[must_use]
pub fn data_component_type_name(id: u16) -> Option<&'static str> {
    let index = usize::from(id).checked_sub(usize::from(base_data_component_type_count()))?;
    FROZEN.get()?.names.get(index).copied()
}

/// The id of a data component type, generated or registered.
#[must_use]
pub fn data_component_type_id(name: &str) -> Option<u16> {
    if let Some(component) = DataComponent::try_from_name(name) {
        return Some(u16::from(component.to_id()));
    }
    FROZEN.get()?.by_name.get(name).copied()
}
