package net.neoforged.neoforge.transfer.access;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public interface ItemAccess {

    static ItemAccess forPlayerInteraction(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.forPlayerInteraction:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    static ItemAccess forInfiniteMaterials(Player player, ItemStack contents) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.forInfiniteMaterials:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    static ItemAccess forPlayerCursor(Player player, AbstractContainerMenu menu) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.forPlayerCursor:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/inventory/AbstractContainerMenu;)Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    static ItemAccess forPlayerSlot(Player player, int slot) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.forPlayerSlot:(Lnet/minecraft/world/entity/player/Player;I)Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    static ItemAccess forHandlerIndex(ResourceHandler<ItemResource> handler, int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.forHandlerIndex:(Lnet/neoforged/neoforge/transfer/ResourceHandler;I)Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    static ItemAccess forHandlerIndexStrict(ResourceHandler<ItemResource> handler, int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.forHandlerIndexStrict:(Lnet/neoforged/neoforge/transfer/ResourceHandler;I)Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    static ItemAccess forStack(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.forStack:(Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    default ItemAccess oneByOne() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.oneByOne:()Lnet/neoforged/neoforge/transfer/access/ItemAccess;");
    }

    default <T> T getCapability(ItemCapability<T, ItemAccess> capability) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;)Ljava/lang/Object;");
    }

    ItemResource getResource();

    int getAmount();

    int insert(ItemResource resource, int amount, TransactionContext transaction);

    int extract(ItemResource resource, int amount, TransactionContext transaction);

    default int exchange(ItemResource newResource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.exchange:(Lnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }
}
