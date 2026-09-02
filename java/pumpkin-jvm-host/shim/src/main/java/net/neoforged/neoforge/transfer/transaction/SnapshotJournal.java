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
    //
    // Enrollment is by key, not by value: a journal whose snapshot is legitimately
    // null (RootCommitJournal holds no state and exists only for its commit callback)
    // must still be enrolled. computeIfAbsent stores nothing when the mapping function
    // returns null, which silently dropped those journals -- they never reverted and
    // never committed, and a transporter's item vanished between the two.
    public void updateSnapshots(TransactionContext transaction) {
        if (transaction instanceof Transaction scope
                && !scope.pumpkinSnapshots.containsKey(this)) {
            scope.pumpkinSnapshots.put(this, createSnapshot());
        }
    }

    public SnapshotJournal() {
    }
}
