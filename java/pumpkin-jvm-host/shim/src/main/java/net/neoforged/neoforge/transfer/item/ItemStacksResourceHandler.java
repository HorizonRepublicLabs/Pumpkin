package net.neoforged.neoforge.transfer.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.StacksResourceHandler;
import dev.pumpkin.shim.Unimplemented;

public class ItemStacksResourceHandler extends StacksResourceHandler<ItemStack, ItemResource> {

    // Pumpkin divergence: real bodies -- the item flavour of the storage above.
    public ItemStacksResourceHandler(int size) {
        super(size, ItemStack.EMPTY, null);
    }

    public ItemStacksResourceHandler(NonNullList<ItemStack> stacks) {
    }

    public ItemResource getResourceFrom(ItemStack stack) {
        return stack == null || stack.isEmpty() ? ItemResource.EMPTY : ItemResource.of(stack);
    }

    public int getAmountFrom(ItemStack stack) {
        return stack == null ? 0 : stack.count();
    }

    protected ItemStack getStackFrom(ItemResource resource, int amount) {
        return resource.toStack(amount);
    }

    protected int getCapacity(int index, ItemResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.getCapacity:(ILnet/neoforged/neoforge/transfer/item/ItemResource;)I");
    }

    protected ItemStack copyOf(ItemStack stack) {
        return stack.copyWithCount(stack.count());
    }

    public boolean matches(ItemStack stack, ItemResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.matches:(Lnet/minecraft/world/item/ItemStack;Lnet/neoforged/neoforge/transfer/item/ItemResource;)Z");
    }

    public ItemStacksResourceHandler() {
    }
}
