//! Runtime-registered menu types.
//!
//! A menu type is what tells a client which screen to draw when the server opens a window.
//! Vanilla's are a flat list of names indexed by id, so — like block entity types — this
//! module owns the accessors for both halves rather than only the runtime one.
//!
//! Registering a menu type gets the client to draw the mod's own screen for it, because
//! the drawing lives in the mod's client half. What it does not get you is the window's
//! contents: slots, their layout, and what a click does are the server's job, and that is
//! ordinary container work rather than anything this registry provides.
//!
//! See the [module docs](super) for the lifecycle and for why entries are leaked.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::screen::MENU_TYPES;

use super::{RegistryError, validate_name};

/// The published registry. Written once by [`publish`], read everywhere after.
struct FrozenMenuTypes {
    /// Indexed by `id - base_menu_type_count()`.
    names: Vec<&'static str>,
    by_name: HashMap<&'static str, u16>,
}

/// Entries accepted but not yet published.
struct Staging {
    names: Vec<&'static str>,
}

static STAGING: Mutex<Option<Staging>> = Mutex::new(None);
static FROZEN: OnceLock<FrozenMenuTypes> = OnceLock::new();

/// The number of generated menu types. Runtime ids start here.
#[must_use]
pub fn base_menu_type_count() -> u16 {
    // Vanilla's list is far short of `u16::MAX`; the cast cannot lose anything.
    MENU_TYPES.len() as u16
}

/// Total menu types, generated plus registered.
#[must_use]
pub fn menu_type_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.names.len());
    base_menu_type_count().saturating_add(extra as u16)
}

/// Adds a menu type to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, or the id space is exhausted.
pub fn register_menu_type(name: String) -> Result<u16, RegistryError> {
    if super::is_frozen() {
        return Err(RegistryError::Frozen);
    }

    validate_name(&name)?;

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(|| Staging { names: Vec::new() });

    if staging.names.contains(&name.as_str()) || MENU_TYPES.contains(&name.as_str()) {
        return Err(RegistryError::DuplicateName(name));
    }

    let id = u16::try_from(base_menu_type_count() as usize + staging.names.len())
        .map_err(|_| RegistryError::OutOfIds(name.clone()))?;

    // Leaked on purpose: see the module docs. The registry outlives every reader.
    let name: &'static str = Box::leak(name.into_boxed_str());
    staging.names.push(name);

    Ok(id)
}

/// Publishes every staged menu type. Called by [`super::freeze`].
pub(super) fn publish() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take();

    let staged = staged.unwrap_or_else(|| Staging { names: Vec::new() });

    let base = base_menu_type_count();
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
    let _ = FROZEN.set(FrozenMenuTypes {
        names: staged.names,
        by_name,
    });
}

/// The name of a menu type, generated or registered.
#[must_use]
pub fn menu_type_name(id: u16) -> Option<&'static str> {
    if let Some(name) = MENU_TYPES.get(id as usize) {
        return Some(name);
    }
    registered_name(id)
}

/// The id of a menu type, generated or registered.
///
/// Generated types are matched by their bare name, registered ones by their namespaced one.
#[must_use]
pub fn menu_type_id(name: &str) -> Option<u16> {
    if let Some(index) = MENU_TYPES.iter().position(|candidate| *candidate == name) {
        // Vanilla's list is short enough that its indices fit a u16.
        return Some(index as u16);
    }
    registered_id(name)
}

// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
fn registered_name(id: u16) -> Option<&'static str> {
    let index = usize::from(id).checked_sub(usize::from(base_menu_type_count()))?;
    FROZEN.get()?.names.get(index).copied()
}

#[cold]
#[inline(never)]
fn registered_id(name: &str) -> Option<u16> {
    FROZEN.get()?.by_name.get(name).copied()
}
