package net.neoforged.neoforge.transfer.access;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public interface ItemAccess {

    static ItemAccess forStack(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.forStack:(Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    default <T> T getCapability(ItemCapability<T, ItemAccess> capability) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;)Ljava/lang/Object;");
    }

    ItemResource getResource();

    int getAmount();

    int insert(ItemResource resource, int amount, TransactionContext transaction);

    int extract(ItemResource resource, int amount, TransactionContext transaction);
}
