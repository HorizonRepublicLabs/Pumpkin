package net.neoforged.neoforge.world.inventory;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public abstract class StackCopySlot extends Slot {

    // Pumpkin divergence: the ctor feeds Slot's real fields; the container is null
    // because this slot reads through getStackCopy instead.
    public StackCopySlot(int slot, int x, int y) {
        super(null, slot, x, y);
    }

    protected abstract ItemStack getStackCopy();

    protected abstract void setStackCopy(ItemStack stack);

    // Pumpkin divergence: NeoForge body -- the whole point of the class.
    public final ItemStack getItem() {
        return getStackCopy();
    }

    public final void set(ItemStack stack) {
        setStackCopy(stack);
    }

    public final void setChanged() {
        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.setChanged:()V");
    }

    public ItemStack remove(int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.remove:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public StackCopySlot() {
    }
}
