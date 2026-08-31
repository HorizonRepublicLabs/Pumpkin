package net.minecraft.world;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class SimpleContainer implements Container, StackedContentsCompatible {

    public SimpleContainer(int size) {
    }

    public SimpleContainer(ItemStack... itemstacks) {
    }

    public ItemStack getItem(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.getItem:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItem(int slot, int count) {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.removeItem:(II)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItemNoUpdate(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.removeItemNoUpdate:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public void setItem(int slot, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.setItem:(ILnet/minecraft/world/item/ItemStack;)V");
    }

    public void setItem(int slot, ItemStack itemStack, boolean insideTransaction) {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.setItem:(ILnet/minecraft/world/item/ItemStack;Z)V");
    }

    public void setChanged() {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.setChanged:()V");
    }

    public int getContainerSize() {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.getContainerSize:()I");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.isEmpty:()Z");
    }

    public boolean stillValid(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.stillValid:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public void clearContent() {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.clearContent:()V");
    }

    public void fillStackedContents(StackedItemContents contents) {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.fillStackedContents:(Lnet/minecraft/world/entity/player/StackedItemContents;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.toString:()Ljava/lang/String;");
    }

    public NonNullList<ItemStack> getItems() {
        throw Unimplemented.forMember("net/minecraft/world/SimpleContainer.getItems:()Lnet/minecraft/core/NonNullList;");
    }

    public SimpleContainer() {
    }
}
