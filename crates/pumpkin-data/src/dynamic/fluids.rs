//! Runtime-registered fluids.
//!
//! Ids from [`Fluid::BASE_COUNT`] up live here. See the [module docs](super) for the
//! lifecycle and for why entries are leaked.
//!
//! `Fluid::from_state_id` is deliberately left alone. It maps ranges of *block* state ids
//! onto fluids, and a registered fluid has no block states until something places one, so
//! there is nothing to answer with. It keeps returning `None` above the generated range.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::fluid::{Fluid, FluidState};

use super::{RegistryError, validate_name};

/// A fluid to be added to the registry.
///
/// The `id` and `name` on `fluid`, and its `states`, are placeholders: the registry assigns
/// the first two and takes the states from this struct.
pub struct FluidRegistration {
    /// Namespaced name, e.g. `examplemod:quicksilver`. Must contain a namespace, and must
    /// not use `minecraft`, which is reserved for generated content.
    pub name: String,
    /// The fluid definition. `id`, `name` and `states` are overwritten.
    pub fluid: Fluid,
    /// Every state this fluid can take.
    pub states: Vec<FluidState>,
    /// Index into `states` for the fluid's default.
    pub default_state_index: u16,
}

/// The published registry. Written once by [`publish`], read everywhere after.
struct FrozenFluids {
    /// Indexed by `id - Fluid::BASE_COUNT`.
    fluids: Vec<&'static Fluid>,
    by_name: HashMap<&'static str, &'static Fluid>,
}

/// Entries accepted but not yet published.
struct Staging {
    fluids: Vec<&'static Fluid>,
    names: HashMap<String, ()>,
}

static STAGING: Mutex<Option<Staging>> = Mutex::new(None);
static FROZEN: OnceLock<FrozenFluids> = OnceLock::new();

/// The number of generated fluids. Runtime ids start here.
#[must_use]
pub fn base_fluid_count() -> u16 {
    Fluid::BASE_COUNT
}

/// Total fluids, generated plus registered.
#[must_use]
pub fn fluid_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.fluids.len());
    Fluid::BASE_COUNT.saturating_add(extra as u16)
}

/// Adds a fluid to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, the states are malformed, or the id space is exhausted.
pub fn register_fluid(registration: FluidRegistration) -> Result<u16, RegistryError> {
    if super::is_frozen() {
        return Err(RegistryError::Frozen);
    }

    let FluidRegistration {
        name,
        fluid,
        states,
        default_state_index,
    } = registration;

    validate_name(&name)?;
    if states.is_empty() || usize::from(default_state_index) >= states.len() {
        return Err(RegistryError::InvalidStates(name));
    }

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(|| Staging {
        fluids: Vec::new(),
        names: HashMap::new(),
    });

    if staging.names.contains_key(&name) || Fluid::from_registry_key(&name).is_some() {
        return Err(RegistryError::DuplicateName(name));
    }

    let id = u16::try_from(Fluid::BASE_COUNT as usize + staging.fluids.len())
        .map_err(|_| RegistryError::OutOfIds(name.clone()))?;

    // Leaked on purpose: see the module docs. The registry outlives every reader.
    let name: &'static str = Box::leak(name.into_boxed_str());
    let states: &'static [FluidState] = Box::leak(states.into_boxed_slice());
    let fluid: &'static Fluid = Box::leak(Box::new(Fluid {
        id,
        name,
        states,
        default_state_index,
        ..fluid
    }));

    staging.fluids.push(fluid);
    staging.names.insert(name.to_string(), ());

    Ok(id)
}

/// Publishes every staged fluid. Called by [`super::freeze`].
pub(super) fn publish() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take();

    let staged = staged.unwrap_or_else(|| Staging {
        fluids: Vec::new(),
        names: HashMap::new(),
    });

    let by_name = staged
        .fluids
        .iter()
        .map(|fluid| (fluid.name, *fluid))
        .collect();

    // Ignores the result: a second publish leaves the first in place.
    let _ = FROZEN.set(FrozenFluids {
        fluids: staged.fluids,
        by_name,
    });
}

/// Resolves a fluid id at or above [`base_fluid_count`].
// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
#[must_use]
pub fn fluid_from_id(id: u16) -> Option<&'static Fluid> {
    let index = usize::from(id).checked_sub(usize::from(Fluid::BASE_COUNT))?;
    FROZEN.get()?.fluids.get(index).copied()
}

/// Finds a registered fluid by its namespaced name.
#[cold]
#[inline(never)]
#[must_use]
pub fn fluid_from_name(name: &str) -> Option<&'static Fluid> {
    FROZEN.get()?.by_name.get(name).copied()
}
