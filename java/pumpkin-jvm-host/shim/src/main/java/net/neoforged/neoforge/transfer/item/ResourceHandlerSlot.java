package net.neoforged.neoforge.transfer.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.world.inventory.StackCopySlot;
import dev.pumpkin.shim.Unimplemented;

public class ResourceHandlerSlot extends StackCopySlot {

    public ResourceHandlerSlot(ResourceHandler<ItemResource> handler, IndexModifier<ItemResource> slotModifier, int handlerSlot, int xPosition, int yPosition) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.<init>:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/neoforged/neoforge/transfer/IndexModifier;III)V");
    }

    public boolean mayPlace(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.mayPlace:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected ItemStack getStackCopy() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.getStackCopy:()Lnet/minecraft/world/item/ItemStack;");
    }

    protected void setStackCopy(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.setStackCopy:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void onQuickCraft(ItemStack oldStackIn, ItemStack newStackIn) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.onQuickCraft:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public int getMaxStackSize() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.getMaxStackSize:()I");
    }

    public int getMaxStackSize(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.getMaxStackSize:(Lnet/minecraft/world/item/ItemStack;)I");
    }

    public boolean mayPickup(Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.mayPickup:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public ResourceHandler<ItemResource> getResourceHandler() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.getResourceHandler:()Lnet/neoforged/neoforge/transfer/ResourceHandler;");
    }

    public boolean isSameInventory(Slot other) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.isSameInventory:(Lnet/minecraft/world/inventory/Slot;)Z");
    }

    protected ResourceHandlerSlot() {
    }
}
