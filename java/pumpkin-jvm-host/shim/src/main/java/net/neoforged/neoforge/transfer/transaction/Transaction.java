package net.neoforged.neoforge.transfer.transaction;

import dev.pumpkin.shim.Unimplemented;

public final class Transaction implements AutoCloseable, TransactionContext {

    public static Transaction openRoot() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.openRoot:()Lnet/neoforged/neoforge/transfer/transaction/Transaction;");
    }

    public void commit() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.commit:()V");
    }

    public void close() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.close:()V");
    }

    public int depth() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.depth:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.toString:()Ljava/lang/String;");
    }

    Transaction(TransactionManager manager, int depth, Class<?> callerClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.<init>:(Lnet/neoforged/neoforge/transfer/transaction/TransactionManager;ILjava/lang/Class;)V");
    }

    private void close(boolean wasAborted) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.close:(Z)V");
    }

    public enum Lifecycle {

        NONE, OPEN, CLOSING, ROOT_CLOSING
    }

    protected Transaction() {
    }
}
