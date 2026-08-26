//! Runtime-registered items.
//!
//! Ids from [`Item::BASE_COUNT`] up live here. See the [module docs](super) for the
//! lifecycle and for why entries are leaked.

use std::collections::HashMap;
use std::sync::{Mutex, OnceLock};

use crate::item::Item;

use super::{RegistryError, validate_name};

/// An item to be added to the registry.
///
/// The `id` and `registry_key` on `item` are placeholders: the registry assigns them.
/// Everything else, `components` above all, is used verbatim.
pub struct ItemRegistration {
    /// Namespaced name, e.g. `examplemod:ruby`. Must contain a namespace, and must not use
    /// `minecraft`, which is reserved for generated content.
    pub name: String,
    /// The item definition. `id` and `registry_key` are overwritten.
    pub item: Item,
}

/// The published registry. Written once by [`publish`], read everywhere after.
struct FrozenItems {
    /// Indexed by `id - Item::BASE_COUNT`.
    items: Vec<&'static Item>,
    by_name: HashMap<&'static str, &'static Item>,
}

/// Entries accepted but not yet published.
struct Staging {
    items: Vec<&'static Item>,
    names: HashMap<String, ()>,
}

static STAGING: Mutex<Option<Staging>> = Mutex::new(None);
static FROZEN: OnceLock<FrozenItems> = OnceLock::new();

/// The number of generated items. Runtime ids start here.
#[must_use]
pub fn base_item_count() -> u16 {
    Item::BASE_COUNT
}

/// Total items, generated plus registered.
#[must_use]
pub fn item_count() -> u16 {
    let extra = FROZEN.get().map_or(0, |frozen| frozen.items.len());
    Item::BASE_COUNT.saturating_add(extra as u16)
}

/// Adds an item to the registry and returns the id it was given.
///
/// # Errors
///
/// Returns [`RegistryError`] if the registry is frozen, the name is unusable or already
/// taken, or the id space is exhausted.
pub fn register_item(registration: ItemRegistration) -> Result<u16, RegistryError> {
    if super::is_frozen() {
        return Err(RegistryError::Frozen);
    }

    let ItemRegistration { name, item } = registration;
    validate_name(&name)?;

    let mut guard = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner);
    let staging = guard.get_or_insert_with(|| Staging {
        items: Vec::new(),
        names: HashMap::new(),
    });

    if staging.names.contains_key(&name) || Item::from_registry_key(&name).is_some() {
        return Err(RegistryError::DuplicateName(name));
    }

    let id = u16::try_from(Item::BASE_COUNT as usize + staging.items.len())
        .map_err(|_| RegistryError::OutOfIds(name.clone()))?;

    // Leaked on purpose: see the module docs. The registry outlives every reader.
    let registry_key: &'static str = Box::leak(name.into_boxed_str());
    let item: &'static Item = Box::leak(Box::new(Item {
        id,
        registry_key,
        ..item
    }));

    staging.items.push(item);
    staging.names.insert(registry_key.to_string(), ());

    Ok(id)
}

/// Publishes every staged item. Called by [`super::freeze`].
pub(super) fn publish() {
    let staged = STAGING
        .lock()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .take();

    let staged = staged.unwrap_or_else(|| Staging {
        items: Vec::new(),
        names: HashMap::new(),
    });

    let by_name = staged
        .items
        .iter()
        .map(|item| (item.registry_key, *item))
        .collect();

    // Ignores the result: a second publish leaves the first in place.
    let _ = FROZEN.set(FrozenItems {
        items: staged.items,
        by_name,
    });
}

/// Resolves an item id at or above [`base_item_count`].
// Only reachable for runtime-registered content, so keep it out of the hot path's
// instruction stream and let the generated-range branch fall through.
#[cold]
#[inline(never)]
#[must_use]
pub fn item_from_id(id: u16) -> Option<&'static Item> {
    let index = usize::from(id).checked_sub(usize::from(Item::BASE_COUNT))?;
    FROZEN.get()?.items.get(index).copied()
}

/// Finds a registered item by its namespaced name.
#[cold]
#[inline(never)]
#[must_use]
pub fn item_from_name(name: &str) -> Option<&'static Item> {
    FROZEN.get()?.by_name.get(name).copied()
}
