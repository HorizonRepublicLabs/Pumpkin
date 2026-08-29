package net.minecraft.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class Slot {

    public final Container container = Stubs.of(Container.class, "net/minecraft/world/Container");

    public int index;

    public final int x = 0;

    public final int y = 0;

    public Slot(Container container, int slot, int x, int y) {
    }

    public void onQuickCraft(ItemStack picked, ItemStack original) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.onQuickCraft:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V");
    }

    protected void onQuickCraft(ItemStack picked, int count) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.onQuickCraft:(Lnet/minecraft/world/item/ItemStack;I)V");
    }

    public void onTake(Player player, ItemStack carried) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.onTake:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public boolean mayPlace(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.mayPlace:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public ItemStack getItem() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.getItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean hasItem() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.hasItem:()Z");
    }

    public void setByPlayer(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.setByPlayer:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void setByPlayer(ItemStack itemStack, ItemStack previous) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.setByPlayer:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void set(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.set:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void setChanged() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.setChanged:()V");
    }

    public int getMaxStackSize() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.getMaxStackSize:()I");
    }

    public int getMaxStackSize(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.getMaxStackSize:(Lnet/minecraft/world/item/ItemStack;)I");
    }

    public ItemStack remove(int amount) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.remove:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean isActive() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.isActive:()Z");
    }

    public Slot() {
    }
}
