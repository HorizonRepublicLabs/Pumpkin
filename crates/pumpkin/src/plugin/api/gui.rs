use std::any::Any;
use std::sync::Arc;
use std::sync::RwLock;

use pumpkin_data::{item_stack::ItemStack, screen::WindowType};
use pumpkin_inventory::screen_handler::{
    InventoryPlayer, ScreenHandler, ScreenHandlerBehaviour, ScreenProperty,
};
use pumpkin_inventory::slot::NormalSlot;
use pumpkin_util::text::TextComponent;
use pumpkin_world::block::entities::PropertyDelegate;
use pumpkin_world::inventory::{Clearable, Inventory};

pub struct PluginGui {
    pub window_type: WindowType,
    /// A registered menu id and the bytes its constructor reads, when the screen is one a
    /// mod draws rather than one of vanilla's. `window_type` is then only a placeholder.
    pub modded_menu: Option<(u16, Vec<u8>)>,
    pub title: TextComponent,
    pub inventory: Arc<PluginInventory>,
    pub allow_grab_items: bool,
    pub allow_put_items: bool,
    /// The window's data values — progress bars and the like. Their meaning belongs to
    /// whatever draws the screen; the server only carries them.
    pub properties: Arc<PluginProperties>,
}

/// The data values behind a plugin's window.
///
/// Shared with the screen handler, which reads them every tick and sends whatever changed,
/// so writing one here is enough to move a progress bar on the client.
#[derive(Default)]
pub struct PluginProperties {
    values: std::sync::RwLock<Vec<i32>>,
}

impl PluginProperties {
    /// Sets one value, growing the list to fit an index nothing has reached yet.
    pub fn set(&self, index: usize, value: i32) {
        let mut values = self
            .values
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        if values.len() <= index {
            values.resize(index + 1, 0);
        }
        values[index] = value;
    }

    /// Reads one value back, or nothing if it was never set.
    #[must_use]
    pub fn get(&self, index: usize) -> Option<i32> {
        self.values
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .get(index)
            .copied()
    }
}

impl PropertyDelegate for PluginProperties {
    fn get_property(&self, index: i32) -> i32 {
        usize::try_from(index)
            .ok()
            .and_then(|index| self.get(index))
            .unwrap_or(0)
    }

    fn set_property(&self, index: i32, value: i32) {
        if let Ok(index) = usize::try_from(index) {
            self.set(index, value);
        }
    }

    fn get_properties_size(&self) -> i32 {
        let values = self
            .values
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        i32::try_from(values.len()).unwrap_or(i32::MAX)
    }
}

pub struct PluginInventory {
    pub slots: RwLock<Vec<ItemStack>>,
}

impl PluginInventory {
    #[must_use]
    pub fn new(size: usize) -> Self {
        Self {
            slots: RwLock::new(vec![ItemStack::EMPTY.clone(); size]),
        }
    }
}

impl Clearable for PluginInventory {
    fn clear(&self) {
        let mut slots = self
            .slots
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        slots.fill_with(|| ItemStack::EMPTY.clone());
    }
}

impl Inventory for PluginInventory {
    fn size(&self) -> usize {
        self.slots
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .len()
    }

    fn is_empty(&self) -> bool {
        let slots = self
            .slots
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        slots.iter().all(ItemStack::is_empty)
    }

    fn get_stack(&self, slot: usize) -> ItemStack {
        let slots = self
            .slots
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        slots
            .get(slot)
            .cloned()
            .unwrap_or_else(|| ItemStack::EMPTY.clone())
    }

    fn remove_stack(&self, slot: usize) -> ItemStack {
        let mut slots = self
            .slots
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        if slot < slots.len() {
            std::mem::replace(&mut slots[slot], ItemStack::EMPTY.clone())
        } else {
            ItemStack::EMPTY.clone()
        }
    }

    fn remove_stack_specific(&self, slot: usize, amount: u8) -> ItemStack {
        let mut slots = self
            .slots
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        if slot < slots.len() && !slots[slot].is_empty() && amount > 0 {
            slots[slot].split(amount)
        } else {
            ItemStack::EMPTY.clone()
        }
    }

    fn set_stack(&self, slot: usize, stack: ItemStack) {
        let mut slots = self
            .slots
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        if slot < slots.len() {
            slots[slot] = stack;
        }
    }

    fn on_open(&self) {}

    fn on_close(&self) {}

    fn as_any(&self) -> &dyn Any {
        self
    }
}

pub struct PluginScreenHandler {
    pub inventory: Arc<PluginInventory>,
    behaviour: ScreenHandlerBehaviour,
}

impl PluginScreenHandler {
    #[must_use]
    pub fn new(
        sync_id: u8,
        window_type: WindowType,
        modded_menu: Option<(u16, Vec<u8>)>,
        inventory: &Arc<PluginInventory>,
        properties: &Arc<PluginProperties>,
        allow_grab_items: bool,
        allow_put_items: bool,
    ) -> Self {
        let mut behaviour = ScreenHandlerBehaviour::new(sync_id, Some(window_type));
        behaviour.modded_menu = modded_menu;
        behaviour.allow_grab_items = allow_grab_items;
        behaviour.allow_put_items = allow_put_items;
        behaviour.container_slots = inventory.size();

        let mut handler = Self {
            inventory: inventory.clone(),
            behaviour,
        };

        for i in 0..inventory.size() {
            handler.add_slot(Arc::new(NormalSlot::new(inventory.clone(), i)));
        }

        // Tracked from the start, so a value the plugin sets later is noticed and sent.
        let count = properties.get_properties_size();
        for index in 0..count {
            handler.add_property(ScreenProperty::new(
                properties.clone(),
                u8::try_from(index).unwrap_or(u8::MAX),
            ));
        }

        handler
    }
}

impl ScreenHandler for PluginScreenHandler {
    fn on_closed(&mut self, player: &dyn InventoryPlayer) {
        self.default_on_closed(player);
        self.inventory.on_close();
    }

    fn as_any(&self) -> &dyn Any {
        self
    }

    fn as_any_mut(&mut self) -> &mut dyn Any {
        self
    }

    fn get_behaviour(&self) -> &ScreenHandlerBehaviour {
        &self.behaviour
    }

    fn get_behaviour_mut(&mut self) -> &mut ScreenHandlerBehaviour {
        &mut self.behaviour
    }

    fn quick_move(&mut self, _player: &dyn InventoryPlayer, _slot_index: i32) -> ItemStack {
        ItemStack::EMPTY.clone()
    }
}
