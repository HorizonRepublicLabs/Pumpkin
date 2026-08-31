package net.neoforged.neoforge.transfer.transaction;

import dev.pumpkin.shim.Unimplemented;

public final class Transaction implements AutoCloseable, TransactionContext {

    public static Transaction openRoot() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.openRoot:()Lnet/neoforged/neoforge/transfer/transaction/Transaction;");
    }

    public static Transaction open(TransactionContext parent) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.open:(Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Lnet/neoforged/neoforge/transfer/transaction/Transaction;");
    }

    public static TransactionContext getCurrentOpenedTransaction() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.getCurrentOpenedTransaction:()Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;");
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

    boolean open;

    Transaction(TransactionManager manager, int depth, Class<?> callerClass) {
    }

    private void close(boolean wasAborted) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/Transaction.close:(Z)V");
    }

    public enum Lifecycle {

        NONE, OPEN, CLOSING, ROOT_CLOSING
    }

    public Transaction() {
    }
}
