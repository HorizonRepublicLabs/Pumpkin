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

    // Pumpkin divergence: real storage. The handler is where a machine's slots live, and
    // every accessor below reads and writes this list, the same shape vanilla keeps.
    protected final S emptyStack;

    protected NonNullList<S> stacks;

    protected final Codec<NonNullList<S>> codec = null;

    protected StacksResourceHandler(int size, S emptyStack, Codec<S> stackCodec) {
        this.emptyStack = emptyStack;
        this.stacks = NonNullList.withSize(size, emptyStack);
    }

    protected StacksResourceHandler(NonNullList<S> stacks, S emptyStack, Codec<S> stackCodec) {
        this.emptyStack = emptyStack;
        this.stacks = stacks;
    }

    private NonNullList<S> mutableCopyOf(Collection<S> list) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.mutableCopyOf:(Ljava/util/Collection;)Lnet/minecraft/core/NonNullList;");
    }

    // Pumpkin divergence: real body -- this is how deserialized contents land.
    protected void setStacks(NonNullList<S> stacks) {
        this.stacks = stacks;
    }

    public void serialize(ValueOutput output) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.serialize:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public void deserialize(ValueInput input) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.deserialize:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    // Pumpkin divergence: vanilla shape -- store the stack, tell the subclass.
    public void set(int index, T resource, int amount) {
        S previous = stacks.get(index);
        stacks.set(index, resource.isEmpty() ? emptyStack : getStackFrom(resource, amount));
        onContentsChanged(index, previous);
    }

    protected abstract T getResourceFrom(S stack);

    protected abstract int getAmountFrom(S stack);

    protected abstract S getStackFrom(T resource, int amount);

    protected abstract S copyOf(S stack);

    protected boolean matches(S stack, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.matches:(Ljava/lang/Object;Lnet/neoforged/neoforge/transfer/resource/Resource;)Z");
    }

    // Pumpkin divergence: vanilla's base answer; subclasses narrow.
    public boolean isValid(int index, T resource) {
        return true;
    }

    protected abstract int getCapacity(int index, T resource);

    // Pumpkin divergence: a notification hook; the base has nothing to notify.
    protected void onContentsChanged(int index, S previousContents) {
    }

    public int size() {
        return stacks.size();
    }

    public T getResource(int index) {
        return getResourceFrom(stacks.get(index));
    }

    public long getAmountAsLong(int index) {
        return getAmountFrom(stacks.get(index));
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

    public StacksResourceHandler() {
        this.emptyStack = null;
    }
}
