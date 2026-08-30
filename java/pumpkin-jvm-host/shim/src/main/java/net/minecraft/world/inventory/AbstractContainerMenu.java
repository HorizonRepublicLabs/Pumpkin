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

    public NonNullList<ItemStack> getItems() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.getItems:()Lnet/minecraft/core/NonNullList;");
    }

    public Slot getSlot(int index) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.getSlot:(I)Lnet/minecraft/world/inventory/Slot;");
    }

    public abstract ItemStack quickMoveStack(final Player player, final int slotIndex);

    public void slotsChanged(Container container) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.slotsChanged:(Lnet/minecraft/world/Container;)V");
    }

    public void setData(int id, int value) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.setData:(II)V");
    }

    public abstract boolean stillValid(Player player);

    protected boolean moveItemStackTo(ItemStack itemStack, int startSlot, int endSlot, boolean backwards) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.moveItemStackTo:(Lnet/minecraft/world/item/ItemStack;IIZ)Z");
    }

    public AbstractContainerMenu() {
    }
}
