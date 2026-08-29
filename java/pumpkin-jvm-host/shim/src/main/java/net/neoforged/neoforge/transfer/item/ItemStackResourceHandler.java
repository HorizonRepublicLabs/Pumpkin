package net.neoforged.neoforge.transfer.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public abstract class ItemStackResourceHandler extends SnapshotJournal<ItemStack> implements ResourceHandler<ItemResource>, ValueIOSerializable {

    protected abstract ItemStack getStack();

    protected abstract void setStack(ItemStack stack);

    public final int size() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.size:()I");
    }

    public int insert(int index, ItemResource resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.insert:(ILnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public int extract(int index, ItemResource resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.extract:(ILnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public ItemResource getResource(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.getResource:(I)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public long getAmountAsLong(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.getAmountAsLong:(I)J");
    }

    public long getCapacityAsLong(int index, ItemResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.getCapacityAsLong:(ILnet/neoforged/neoforge/transfer/item/ItemResource;)J");
    }

    public boolean isValid(int index, ItemResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.isValid:(ILnet/neoforged/neoforge/transfer/item/ItemResource;)Z");
    }

    protected ItemStack createSnapshot() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.createSnapshot:()Lnet/minecraft/world/item/ItemStack;");
    }

    protected void revertToSnapshot(ItemStack snapshot) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.revertToSnapshot:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void serialize(ValueOutput output) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.serialize:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public void deserialize(ValueInput input) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.deserialize:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStackResourceHandler.toString:()Ljava/lang/String;");
    }

    public ItemStackResourceHandler() {
    }
}
