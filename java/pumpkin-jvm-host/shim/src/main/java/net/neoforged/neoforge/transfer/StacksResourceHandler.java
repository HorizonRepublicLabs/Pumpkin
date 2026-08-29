package net.neoforged.neoforge.transfer;

import com.mojang.serialization.Codec;
import java.util.Collection;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import net.neoforged.neoforge.transfer.resource.Resource;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public abstract class StacksResourceHandler<S, T extends Resource> implements ResourceHandler<T>, ValueIOSerializable {

    protected final S emptyStack = null;

    protected NonNullList<S> stacks;

    protected final Codec<NonNullList<S>> codec = null;

    protected StacksResourceHandler(int size, S emptyStack, Codec<S> stackCodec) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.<init>:(ILjava/lang/Object;Lcom/mojang/serialization/Codec;)V");
    }

    protected StacksResourceHandler(NonNullList<S> stacks, S emptyStack, Codec<S> stackCodec) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.<init>:(Lnet/minecraft/core/NonNullList;Ljava/lang/Object;Lcom/mojang/serialization/Codec;)V");
    }

    private NonNullList<S> mutableCopyOf(Collection<S> list) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.mutableCopyOf:(Ljava/util/Collection;)Lnet/minecraft/core/NonNullList;");
    }

    protected void setStacks(NonNullList<S> stacks) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.setStacks:(Lnet/minecraft/core/NonNullList;)V");
    }

    public void serialize(ValueOutput output) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.serialize:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public void deserialize(ValueInput input) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.deserialize:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void set(int index, T resource, int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.set:(ILnet/neoforged/neoforge/transfer/resource/Resource;I)V");
    }

    protected abstract T getResourceFrom(S stack);

    protected abstract int getAmountFrom(S stack);

    protected abstract S getStackFrom(T resource, int amount);

    protected abstract S copyOf(S stack);

    protected boolean matches(S stack, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.matches:(Ljava/lang/Object;Lnet/neoforged/neoforge/transfer/resource/Resource;)Z");
    }

    public boolean isValid(int index, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.isValid:(ILnet/neoforged/neoforge/transfer/resource/Resource;)Z");
    }

    protected abstract int getCapacity(int index, T resource);

    protected void onContentsChanged(int index, S previousContents) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.onContentsChanged:(ILjava/lang/Object;)V");
    }

    public int size() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.size:()I");
    }

    public T getResource(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.getResource:(I)Lnet/neoforged/neoforge/transfer/resource/Resource;");
    }

    public long getAmountAsLong(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.getAmountAsLong:(I)J");
    }

    public long getCapacityAsLong(int index, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.getCapacityAsLong:(ILnet/neoforged/neoforge/transfer/resource/Resource;)J");
    }

    public int insert(int index, T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.insert:(ILnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public int extract(int index, T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.extract:(ILnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    private class StackJournal extends SnapshotJournal<S> {

        private StackJournal(int index) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler$StackJournal.<init>:(I)V");
        }

        protected S createSnapshot() {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler$StackJournal.createSnapshot:()Ljava/lang/Object;");
        }

        protected void revertToSnapshot(S snapshot) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler$StackJournal.revertToSnapshot:(Ljava/lang/Object;)V");
        }

        protected void onRootCommit(S originalState) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler$StackJournal.onRootCommit:(Ljava/lang/Object;)V");
        }

        protected StackJournal() {
        }
    }

    protected StacksResourceHandler() {
    }
}
