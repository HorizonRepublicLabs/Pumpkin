package net.neoforged.neoforge.transfer.transaction;

import dev.pumpkin.shim.Unimplemented;

public abstract class SnapshotJournal<T extends Object> {

    protected abstract T createSnapshot();

    protected abstract void revertToSnapshot(T snapshot);

    // Pumpkin divergence: NeoForge's own default -- a hook, empty unless overridden.
    protected void onRootCommit(T originalState) {
    }

    // Pumpkin divergence: real -- the first update inside a transaction snapshots
    // this journal's state into that transaction's scope; later updates are no-ops.
    public void updateSnapshots(TransactionContext transaction) {
        if (transaction instanceof Transaction scope) {
            scope.pumpkinSnapshots.computeIfAbsent(this, journal -> createSnapshot());
        }
    }

    public SnapshotJournal() {
    }
}
