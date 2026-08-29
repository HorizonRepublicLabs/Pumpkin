package net.neoforged.neoforge.transfer.transaction;

public abstract class SnapshotJournal<T extends Object> {

    protected abstract T createSnapshot();

    protected abstract void revertToSnapshot(T snapshot);

    public SnapshotJournal() {
    }
}
