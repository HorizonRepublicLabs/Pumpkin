package net.neoforged.neoforge.transfer.transaction;

import dev.pumpkin.shim.Unimplemented;

public final class RootCommitJournal extends SnapshotJournal<Void> {

    public RootCommitJournal(Runnable rootCommitCallback) {
    }

    protected Void createSnapshot() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/RootCommitJournal.createSnapshot:()Ljava/lang/Void;");
    }

    protected void revertToSnapshot(Void snapshot) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/RootCommitJournal.revertToSnapshot:(Ljava/lang/Void;)V");
    }

    protected void onRootCommit(Void originalState) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/RootCommitJournal.onRootCommit:(Ljava/lang/Void;)V");
    }

    public RootCommitJournal() {
    }
}
