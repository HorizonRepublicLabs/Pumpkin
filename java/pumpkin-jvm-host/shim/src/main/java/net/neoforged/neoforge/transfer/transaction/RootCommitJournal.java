package net.neoforged.neoforge.transfer.transaction;

import dev.pumpkin.shim.Unimplemented;

// Pumpkin divergence: real bodies. This journal carries no state of its own -- it
// exists to run one callback when the outermost transaction commits (a transporter
// scheduling its pulled stack), so the snapshot is nothing and the revert is nothing.
public final class RootCommitJournal extends SnapshotJournal<Void> {

    private final Runnable pumpkinRootCommit;

    public RootCommitJournal(Runnable rootCommitCallback) {
        this.pumpkinRootCommit = rootCommitCallback;
    }

    protected Void createSnapshot() {
        return null;
    }

    protected void revertToSnapshot(Void snapshot) {
    }

    protected void onRootCommit(Void originalState) {
        if (pumpkinRootCommit != null) {
            pumpkinRootCommit.run();
        }
    }

    public RootCommitJournal() {
        this.pumpkinRootCommit = null;
    }
}
