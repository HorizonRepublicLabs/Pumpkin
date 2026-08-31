package net.minecraft.world.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractContainerMenu {

    // Pumpkin divergence: the slot list is real; every mod menu fills it via addSlot.
    public final NonNullList<Slot> slots = NonNullList.create();

    private MenuType<?> pumpkinMenuType;

    public int containerId;

    protected AbstractContainerMenu(MenuType<?> menuType, int containerId) {
        this.pumpkinMenuType = menuType;
        this.containerId = containerId;
    }

    public MenuType<?> getType() {
        return pumpkinMenuType;
    }

    // Pumpkin divergence: vanilla body -- number the slot, keep it.
    protected Slot addSlot(Slot slot) {
        slot.index = slots.size();
        slots.add(slot);
        return slot;
    }

    protected void addDataSlots(ContainerData container) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.addDataSlots:(Lnet/minecraft/world/inventory/ContainerData;)V");
    }

    public void addSlotListener(ContainerListener listener) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.addSlotListener:(Lnet/minecraft/world/inventory/ContainerListener;)V");
    }

    public void sendAllDataToRemote() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.sendAllDataToRemote:()V");
    }

    public void removeSlotListener(ContainerListener listener) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.removeSlotListener:(Lnet/minecraft/world/inventory/ContainerListener;)V");
    }

    public NonNullList<ItemStack> getItems() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.getItems:()Lnet/minecraft/core/NonNullList;");
    }

    public void broadcastChanges() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.broadcastChanges:()V");
    }

    public Slot getSlot(int index) {
        return slots.get(index);
    }

    public abstract ItemStack quickMoveStack(final Player player, final int slotIndex);

    public void clicked(int slotIndex, int buttonNum, ContainerInput containerInput, Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.clicked:(IILnet/minecraft/world/inventory/ContainerInput;Lnet/minecraft/world/entity/player/Player;)V");
    }

    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.canTakeItemForPickAll:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/inventory/Slot;)Z");
    }

    public void removed(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.removed:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public void slotsChanged(Container container) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.slotsChanged:(Lnet/minecraft/world/Container;)V");
    }

    public void setData(int id, int value) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.setData:(II)V");
    }

    public abstract boolean stillValid(Player player);

    // Pumpkin divergence: vanilla's merge algorithm, the workhorse every mod's
    // quickMoveStack leans on -- fill matching stacks first, then empty slots.
    protected boolean moveItemStackTo(ItemStack itemStack, int startSlot, int endSlot, boolean backwards) {
        boolean moved = false;
        int index = backwards ? endSlot - 1 : startSlot;
        while (itemStack.count() > 0 && (backwards ? index >= startSlot : index < endSlot)) {
            Slot slot = slots.get(index);
            ItemStack existing = slot.getItem();
            if (!existing.isEmpty() && existing.getItem() == itemStack.getItem()) {
                int total = existing.count() + itemStack.count();
                int max = Math.min(slot.getMaxStackSize(), 64);
                if (total <= max) {
                    slot.set(existing.copyWithCount(total));
                    pumpkinShrink(itemStack, itemStack.count());
                    moved = true;
                } else if (existing.count() < max) {
                    int adding = max - existing.count();
                    slot.set(existing.copyWithCount(max));
                    pumpkinShrink(itemStack, adding);
                    moved = true;
                }
            }
            index += backwards ? -1 : 1;
        }
        if (itemStack.count() > 0) {
            index = backwards ? endSlot - 1 : startSlot;
            while (backwards ? index >= startSlot : index < endSlot) {
                Slot slot = slots.get(index);
                if (!slot.hasItem() && slot.mayPlace(itemStack)) {
                    int placing = Math.min(itemStack.count(), slot.getMaxStackSize());
                    slot.set(itemStack.copyWithCount(placing));
                    pumpkinShrink(itemStack, placing);
                    moved = true;
                    if (itemStack.count() <= 0) {
                        break;
                    }
                }
                index += backwards ? -1 : 1;
            }
        }
        return moved;
    }

    // ItemStack counts are immutable in this shim (copyWithCount replaces); the caller's
    // stack is shrunk by swapping its contents through the carried reference the click
    // bridge owns. Tracked here as a mutable count on the wrapper.
    private void pumpkinShrink(ItemStack stack, int by) {
        stack.pumpkinShrink(by);
    }

    // Pumpkin divergence: the carried stack -- vanilla keeps it on the menu too.
    private ItemStack pumpkinCarried = ItemStack.EMPTY;

    public ItemStack getCarried() {
        return pumpkinCarried;
    }

    public void setCarried(ItemStack stack) {
        this.pumpkinCarried = stack;
    }

    public static boolean canItemQuickReplace(Slot slot, ItemStack itemStack, boolean ignoreSize) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.canItemQuickReplace:(Lnet/minecraft/world/inventory/Slot;Lnet/minecraft/world/item/ItemStack;Z)Z");
    }

    public static int getQuickCraftPlaceCount(int quickCraftSlotsSize, int quickCraftingType, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.getQuickCraftPlaceCount:(IILnet/minecraft/world/item/ItemStack;)I");
    }

    public boolean canDragTo(Slot slot) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.canDragTo:(Lnet/minecraft/world/inventory/Slot;)Z");
    }

    public AbstractContainerMenu() {
    }
}
