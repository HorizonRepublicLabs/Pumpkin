//! Runtime-registered sound events.
//!
//! A sound event is a name the server can point at when it tells a client to play
//! something; the audio itself ships in the mod's client half, exactly like a menu type's
//! screen. Vanilla's are a flat list of names indexed by id — [`Sound::NAMES`] — so this
//! module owns the accessors for both halves, like menu and block entity types do.
//!
//! Registering one gets it an id that survives the protocol. Actually playing it to
//! clients is ordinary sound-packet work the server can do with that id.
//!
//! See the [module docs](super) for the lifecycle and for why entries are leaked.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::sound::Sound;

use super::{RegistryError, validate_name};

/// The published registry. Written once by [`publish`], read everywhere after.
struct FrozenSoundEvents {
    /// Indexed by `id - base_sound_event_count()`.
    names: Vec<&'static str>,
    by_name: HashMap<&'static str, u16>,
}

/// Entries accepted but not yet published.
struct Staging {
    names: Vec<&'static str>,
}

static STAGING: Mutex<Option<Staging>> = Mutex::new(None);
static FROZEN: OnceLock<FrozenSoundEvents> = OnceLock::new();

/// The number of generated sound events. Runtime ids start here.
#[must_use]
pub fn base_sound_event_count() -> u16 {
    // Vanilla's list is far short of `u16::MAX`; the cast cannot lose anything.
    Sound::NAMES.len() as u16
}

/// Total sound events, generated plus registered.
#[must_use]
pub fn sound_event_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.names.len());
    base_sound_event_count().saturating_add(extra as u16)
}

/// Adds a sound event to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, or the id space is exhausted.
pub fn register_sound_event(name: String) -> Result<u16, RegistryError> {
    if super::is_frozen() {
        return Err(RegistryError::Frozen);
    }

    validate_name(&name)?;

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(|| Staging { names: Vec::new() });

    if staging.names.contains(&name.as_str()) || Sound::NAMES.contains(&name.as_str()) {
        return Err(RegistryError::DuplicateName(name));
    }

    let id = u16::try_from(base_sound_event_count() as usize + staging.names.len())
        .map_err(|_| RegistryError::OutOfIds(name.clone()))?;

    // Leaked on purpose: see the module docs. The registry outlives every reader.
    let name: &'static str = Box::leak(name.into_boxed_str());
    staging.names.push(name);

    Ok(id)
}

/// Publishes every staged sound event. Called by [`super::freeze`].
pub(super) fn publish() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take();

    let staged = staged.unwrap_or_else(|| Staging { names: Vec::new() });

    let base = base_sound_event_count();
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
    let _ = FROZEN.set(FrozenSoundEvents {
        names: staged.names,
        by_name,
    });
}

/// The name of a sound event, generated or registered.
#[must_use]
pub fn sound_event_name(id: u16) -> Option<&'static str> {
    if let Some(name) = Sound::NAMES.get(id as usize) {
        return Some(name);
    }
    registered_name(id)
}

/// The id of a sound event, generated or registered.
///
/// Generated events are matched by their bare name, registered ones by their namespaced
/// one — the same split menu types make.
#[must_use]
pub fn sound_event_id(name: &str) -> Option<u16> {
    if let Some(sound) = Sound::from_name(name) {
        return Some(sound as u16);
    }
    registered_id(name)
}

// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
fn registered_name(id: u16) -> Option<&'static str> {
    let index = usize::from(id).checked_sub(usize::from(base_sound_event_count()))?;
    FROZEN.get()?.names.get(index).copied()
}

#[cold]
#[inline(never)]
fn registered_id(name: &str) -> Option<u16> {
    FROZEN.get()?.by_name.get(name).copied()
}
