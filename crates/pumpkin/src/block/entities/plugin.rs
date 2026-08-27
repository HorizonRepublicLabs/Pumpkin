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
use std::sync::Arc;

use async_trait::async_trait;
use pumpkin_data::item_stack::ItemStack;
use pumpkin_nbt::compound::NbtCompound;
use pumpkin_util::math::position::BlockPos;
use pumpkin_world::inventory::{Clearable, Inventory, InventoryFuture, split_stack_slice};
use tokio::sync::Mutex;

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
    pub items: Mutex<Vec<ItemStack>>,
    /// Anything else the plugin wants to keep, saved alongside the items.
    pub data: Mutex<NbtCompound>,
    dirty: std::sync::atomic::AtomicBool,
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
            items: Mutex::new(vec![ItemStack::EMPTY.clone(); SLOTS]),
            data: Mutex::new(NbtCompound::new()),
            dirty: std::sync::atomic::AtomicBool::new(false),
        })
    }
}

/// The registry's own copy of a name, which lives as long as the process.
fn registered_name(id: &str) -> Option<&'static str> {
    let numeric = pumpkin_data::dynamic::block_entity_type_id(id)?;
    pumpkin_data::dynamic::block_entity_type_name(numeric)
}

#[async_trait]
impl BlockEntity for PluginBlockEntity {
    fn resource_location(&self) -> &'static str {
        self.id
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
            items: Mutex::new(items),
            data: Mutex::new(nbt.clone()),
            dirty: std::sync::atomic::AtomicBool::new(false),
        }
    }

    fn write_nbt<'a>(
        &'a self,
        nbt: &'a mut NbtCompound,
    ) -> std::pin::Pin<Box<dyn Future<Output = ()> + Send + 'a>> {
        Box::pin(async move {
            self.write_inventory_nbt(nbt, true).await;

            // Whatever the plugin kept, minus the keys the container itself owns.
            let data = self.data.lock().await;
            for (key, value) in data.child_tags.clone() {
                if !matches!(&*key, "id" | "x" | "y" | "z" | "Items") {
                    nbt.put(&key, value);
                }
            }
        })
    }

    fn is_dirty(&self) -> bool {
        self.dirty.load(std::sync::atomic::Ordering::Relaxed)
    }

    fn clear_dirty(&self) {
        self.dirty
            .store(false, std::sync::atomic::Ordering::Relaxed);
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

    fn is_empty(&self) -> InventoryFuture<'_, bool> {
        Box::pin(async move {
            let items = self.items.lock().await;
            items.iter().all(ItemStack::is_empty)
        })
    }

    fn get_stack(&self, slot: usize) -> InventoryFuture<'_, ItemStack> {
        Box::pin(async move {
            let items = self.items.lock().await;
            items.get(slot).cloned().unwrap_or(ItemStack::EMPTY.clone())
        })
    }

    fn remove_stack_specific(&self, slot: usize, amount: u8) -> InventoryFuture<'_, ItemStack> {
        Box::pin(async move {
            let mut items = self.items.lock().await;
            split_stack_slice(&mut items, slot, amount)
        })
    }

    fn remove_stack(&self, slot: usize) -> InventoryFuture<'_, ItemStack> {
        Box::pin(async move {
            let mut items = self.items.lock().await;
            items.get_mut(slot).map_or_else(
                || ItemStack::EMPTY.clone(),
                |stack| std::mem::replace(stack, ItemStack::EMPTY.clone()),
            )
        })
    }

    fn set_stack(&self, slot: usize, stack: ItemStack) -> InventoryFuture<'_, ()> {
        Box::pin(async move {
            let mut items = self.items.lock().await;
            if let Some(existing) = items.get_mut(slot) {
                *existing = stack;
            }
        })
    }

    fn mark_dirty(&self) {
        self.dirty.store(true, std::sync::atomic::Ordering::Relaxed);
    }

    fn as_any(&self) -> &dyn Any {
        self
    }
}

impl Clearable for PluginBlockEntity {
    fn clear(&self) -> InventoryFuture<'_, ()> {
        Box::pin(async move {
            let mut items = self.items.lock().await;
            for stack in items.iter_mut() {
                *stack = ItemStack::EMPTY.clone();
            }
        })
    }
}
