package net.neoforged.neoforge.transfer.transaction;

import dev.pumpkin.shim.Unimplemented;

public abstract class SnapshotJournal<T extends Object> {

    protected abstract T createSnapshot();

    protected abstract void revertToSnapshot(T snapshot);

    protected void onRootCommit(T originalState) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/SnapshotJournal.onRootCommit:(Ljava/lang/Object;)V");
    }

    public void updateSnapshots(TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/SnapshotJournal.updateSnapshots:(Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)V");
    }

    public SnapshotJournal() {
    }
}
