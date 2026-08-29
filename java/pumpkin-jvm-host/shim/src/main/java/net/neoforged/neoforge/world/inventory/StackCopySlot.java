package net.neoforged.neoforge.world.inventory;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public abstract class StackCopySlot extends Slot {

    public StackCopySlot(int slot, int x, int y) {
        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.<init>:(III)V");
    }

    protected abstract ItemStack getStackCopy();

    protected abstract void setStackCopy(ItemStack stack);

    public final ItemStack getItem() {
        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.getItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    public final void set(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.set:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public final void setChanged() {
        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.setChanged:()V");
    }

    public ItemStack remove(int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.remove:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    protected StackCopySlot() {
    }
}
