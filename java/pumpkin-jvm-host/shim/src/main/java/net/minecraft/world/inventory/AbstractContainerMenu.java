package net.minecraft.world.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractContainerMenu {

    protected AbstractContainerMenu(MenuType<?> menuType, int containerId) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.<init>:(Lnet/minecraft/world/inventory/MenuType;I)V");
    }

    public MenuType<?> getType() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.getType:()Lnet/minecraft/world/inventory/MenuType;");
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

    protected AbstractContainerMenu() {
    }
}
