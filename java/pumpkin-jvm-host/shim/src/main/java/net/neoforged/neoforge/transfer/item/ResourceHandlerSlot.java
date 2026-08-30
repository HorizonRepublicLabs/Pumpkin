package net.neoforged.neoforge.transfer.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.world.inventory.StackCopySlot;
import dev.pumpkin.shim.Unimplemented;

public class ResourceHandlerSlot extends StackCopySlot {

    // Pumpkin divergence: the handler and slot index are kept; the copy accessors below
    // read and write through them, which is this class's whole job.
    private ResourceHandler<ItemResource> pumpkinHandler;

    private int pumpkinHandlerSlot;

    public ResourceHandlerSlot(ResourceHandler<ItemResource> handler, IndexModifier<ItemResource> slotModifier, int handlerSlot, int xPosition, int yPosition) {
        super(handlerSlot, xPosition, yPosition);
        this.pumpkinHandler = handler;
        this.pumpkinHandlerSlot = handlerSlot;
    }

    public boolean mayPlace(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.mayPlace:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected ItemStack getStackCopy() {
        ItemResource resource = pumpkinHandler.getResource(pumpkinHandlerSlot);
        if (resource == null || resource.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return resource.toStack(pumpkinHandler.getAmountAsInt(pumpkinHandlerSlot));
    }

    protected void setStackCopy(ItemStack stack) {
        if (pumpkinHandler instanceof net.neoforged.neoforge.transfer.StacksResourceHandler<?, ItemResource> stacks) {
            stacks.set(pumpkinHandlerSlot, ItemResource.of(stack), stack.count());
        } else {
            throw dev.pumpkin.shim.Unimplemented.forMember(
                    "net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.setStackCopy (non-stack handler)");
        }
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

    public ResourceHandlerSlot() {
    }
}
