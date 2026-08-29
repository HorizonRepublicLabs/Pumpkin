package net.neoforged.neoforge.transfer.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.StacksResourceHandler;
import dev.pumpkin.shim.Unimplemented;

public class ItemStacksResourceHandler extends StacksResourceHandler<ItemStack, ItemResource> {

    public ItemStacksResourceHandler(int size) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.<init>:(I)V");
    }

    public ItemStacksResourceHandler(NonNullList<ItemStack> stacks) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.<init>:(Lnet/minecraft/core/NonNullList;)V");
    }

    public ItemResource getResourceFrom(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.getResourceFrom:(Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public int getAmountFrom(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.getAmountFrom:(Lnet/minecraft/world/item/ItemStack;)I");
    }

    protected ItemStack getStackFrom(ItemResource resource, int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.getStackFrom:(Lnet/neoforged/neoforge/transfer/item/ItemResource;I)Lnet/minecraft/world/item/ItemStack;");
    }

    protected int getCapacity(int index, ItemResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.getCapacity:(ILnet/neoforged/neoforge/transfer/item/ItemResource;)I");
    }

    protected ItemStack copyOf(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.copyOf:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean matches(ItemStack stack, ItemResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.matches:(Lnet/minecraft/world/item/ItemStack;Lnet/neoforged/neoforge/transfer/item/ItemResource;)Z");
    }

    public ItemStacksResourceHandler() {
    }
}
