//! A block entity for a type registered at runtime.
//!
//! Generated block entities each have Rust behind them — a furnace smelts, a chest holds
//! things — and are built by matching on their name. A type a plugin registered has no such
//! code, so nothing could be built for it and its block had nowhere to keep anything.
//!
//! This is what gets built instead: a position, a stack of items and a bag of NBT, and no
//! behaviour of its own. That is enough for the thing that was missing, which is somewhere
//! for a machine's contents to live that belongs to the world rather than to whatever
//! window happens to be open. The plugin supplies the behaviour and this holds the state,
//! saved and loaded with its chunk like any other.

use std::any::Any;
use std::sync::{Arc, RwLock, atomic::AtomicBool, atomic::Ordering};

use pumpkin_data::item_stack::ItemStack;
use pumpkin_nbt::compound::NbtCompound;
use pumpkin_util::math::position::BlockPos;
use pumpkin_world::inventory::{Clearable, Inventory, split_stack_slice};

use super::BlockEntity;

/// How many slots a runtime-registered block entity has.
///
/// A fixed size avoids a registration having to declare one, and is generous enough for the
/// machines this exists for. Unused slots cost an empty stack each.
pub const SLOTS: usize = 27;

pub struct PluginBlockEntity {
    /// The registered type's namespaced name, which is what it is saved under.
    pub id: &'static str,
    pub position: BlockPos,
    pub items: RwLock<Vec<ItemStack>>,
    /// Anything else the plugin wants to keep, saved alongside the items.
    pub data: RwLock<NbtCompound>,
    dirty: AtomicBool,
}

impl PluginBlockEntity {
    /// Builds an empty one for a registered type.
    ///
    /// Returns nothing if the name is not a registered block entity type, so a stale name
    /// in a saved chunk does not become a block entity nothing understands.
    #[must_use]
    pub fn new(id: &str, position: BlockPos) -> Option<Self> {
        let id = registered_name(id)?;
        Some(Self {
            id,
            position,
            items: RwLock::new(vec![ItemStack::EMPTY.clone(); SLOTS]),
            data: RwLock::new(NbtCompound::new()),
            dirty: AtomicBool::new(false),
        })
    }
}

/// The registry's own copy of a name, which lives as long as the process.
fn registered_name(id: &str) -> Option<&'static str> {
    let numeric = pumpkin_data::dynamic::block_entity_type_id(id)?;
    pumpkin_data::dynamic::block_entity_type_name(numeric)
}

/// The tick hook a plugin host may install.
///
/// The generic entity cannot know how its behaviour is hosted -- the JVM loader routes
/// ticks into a mod's own ticker, a future host may do something else -- so whoever can
/// installs exactly one hook for the process. Entities without an installed hook simply
/// do not tick, which is also what they did before hooks existed.
pub type BlockEntityTickHook =
    Box<dyn Fn(&PluginBlockEntity, &Arc<crate::world::World>) + Send + Sync>;

static TICK_HOOK: std::sync::OnceLock<BlockEntityTickHook> = std::sync::OnceLock::new();

/// Installs the process-wide tick hook. The first caller wins; later calls are ignored,
/// which keeps a second plugin host from silently stealing the first one's ticks.
pub fn install_tick_hook(hook: BlockEntityTickHook) {
    let _ = TICK_HOOK.set(hook);
}

impl BlockEntity for PluginBlockEntity {
    fn resource_location(&self) -> &'static str {
        self.id
    }

    fn tick(&self, world: &Arc<crate::world::World>) {
        if let Some(hook) = TICK_HOOK.get() {
            hook(self, world);
        }
    }

    fn get_position(&self) -> BlockPos {
        self.position
    }

    fn from_nbt(nbt: &NbtCompound, position: BlockPos) -> Self
    where
        Self: Sized,
    {
        // Only reached through `block_entity_from_nbt`, which has already checked the name.
        let id = nbt.get_string("id").and_then(registered_name).unwrap_or("");

        let mut items = vec![ItemStack::EMPTY.clone(); SLOTS];
        pumpkin_world::inventory::sync_read_items_from_nbt(nbt, &mut items);

        Self {
            id,
            position,
            items: RwLock::new(items),
            data: RwLock::new(nbt.clone()),
            dirty: AtomicBool::new(false),
        }
    }

    fn write_nbt(&self, nbt: &mut NbtCompound) {
        self.write_inventory_nbt(nbt, true);

        // Whatever the plugin kept, minus the keys the container itself owns.
        let data = read(&self.data);
        for (key, value) in data.child_tags.clone() {
            if !matches!(&*key, "id" | "x" | "y" | "z" | "Items") {
                nbt.put(&key, value);
            }
        }
    }

    fn is_dirty(&self) -> bool {
        self.dirty.load(Ordering::Relaxed)
    }

    fn clear_dirty(&self) {
        self.dirty.store(false, Ordering::Relaxed);
    }

    fn get_inventory(self: Arc<Self>) -> Option<Arc<dyn Inventory>> {
        Some(self)
    }

    fn as_any(&self) -> &dyn Any {
        self
    }
}

impl Inventory for PluginBlockEntity {
    fn size(&self) -> usize {
        SLOTS
    }

    fn is_empty(&self) -> bool {
        read(&self.items).iter().all(ItemStack::is_empty)
    }

    fn get_stack(&self, slot: usize) -> ItemStack {
        read(&self.items)
            .get(slot)
            .cloned()
            .unwrap_or(ItemStack::EMPTY.clone())
    }

    fn remove_stack_specific(&self, slot: usize, amount: u8) -> ItemStack {
        split_stack_slice(&mut write(&self.items), slot, amount)
    }

    fn remove_stack(&self, slot: usize) -> ItemStack {
        write(&self.items).get_mut(slot).map_or_else(
            || ItemStack::EMPTY.clone(),
            |stack| std::mem::replace(stack, ItemStack::EMPTY.clone()),
        )
    }

    fn set_stack(&self, slot: usize, stack: ItemStack) {
        if let Some(existing) = write(&self.items).get_mut(slot) {
            *existing = stack;
        }
    }

    fn mark_dirty(&self) {
        self.dirty.store(true, Ordering::Relaxed);
    }

    fn as_any(&self) -> &dyn Any {
        self
    }
}

impl Clearable for PluginBlockEntity {
    fn clear(&self) {
        for stack in write(&self.items).iter_mut() {
            *stack = ItemStack::EMPTY.clone();
        }
    }
}

/// A poisoned lock here means another thread panicked mid-update, not that the contents are
/// unusable, so reads and writes carry on with what is there.
fn read<T>(lock: &RwLock<T>) -> std::sync::RwLockReadGuard<'_, T> {
    lock.read()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
}

fn write<T>(lock: &RwLock<T>) -> std::sync::RwLockWriteGuard<'_, T> {
    lock.write()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
}
