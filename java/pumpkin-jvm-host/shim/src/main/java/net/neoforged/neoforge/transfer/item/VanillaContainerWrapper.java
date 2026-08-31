package net.neoforged.neoforge.transfer.item;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public class VanillaContainerWrapper implements ResourceHandler<ItemResource> {

    public static ResourceHandler<ItemResource> of(Container container) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.of:(Lnet/minecraft/world/Container;)Lnet/neoforged/neoforge/transfer/ResourceHandler;");
    }

    VanillaContainerWrapper(Container container) {
    }

    public int size() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.size:()I");
    }

    public int insert(int index, ItemResource resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.insert:(ILnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public int extract(int index, ItemResource resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.extract:(ILnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public ItemResource getResource(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.getResource:(I)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public long getAmountAsLong(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.getAmountAsLong:(I)J");
    }

    public long getCapacityAsLong(int index, ItemResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.getCapacityAsLong:(ILnet/neoforged/neoforge/transfer/item/ItemResource;)J");
    }

    public boolean isValid(int index, ItemResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.isValid:(ILnet/neoforged/neoforge/transfer/item/ItemResource;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper.toString:()Ljava/lang/String;");
    }

    class SlotWrapper extends ItemStackResourceHandler {

        SlotWrapper(int index) {
        }

        protected ItemStack getStack() {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.getStack:()Lnet/minecraft/world/item/ItemStack;");
        }

        protected void setStack(ItemStack item) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.setStack:(Lnet/minecraft/world/item/ItemStack;)V");
        }

        protected boolean isValid(ItemResource resource) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.isValid:(Lnet/neoforged/neoforge/transfer/item/ItemResource;)Z");
        }

        protected int getCapacity(ItemResource resource) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.getCapacity:(Lnet/neoforged/neoforge/transfer/item/ItemResource;)I");
        }

        public int insert(int index, ItemResource resource, int amount, TransactionContext transaction) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.insert:(ILnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
        }

        public int extract(int index, ItemResource resource, int amount, TransactionContext transaction) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.extract:(ILnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
        }

        public void updateSnapshots(TransactionContext transaction) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.updateSnapshots:(Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)V");
        }

        protected void onRootCommit(ItemStack original) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.onRootCommit:(Lnet/minecraft/world/item/ItemStack;)V");
        }

        public String toString() {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/VanillaContainerWrapper$SlotWrapper.toString:()Ljava/lang/String;");
        }

        protected SlotWrapper() {
        }
    }

    public VanillaContainerWrapper() {
    }
}
