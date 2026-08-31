package net.neoforged.neoforge.transfer.transaction;

// Pumpkin divergence: a real implementation. The transaction system is pure library
// logic -- a per-thread stack of scopes, each holding the first snapshot every journal
// took inside it; closing without commit reverts them, committing hands them to the
// parent scope (or fires onRootCommit at the root).
public final class Transaction implements AutoCloseable, TransactionContext {

    private static final ThreadLocal<java.util.ArrayDeque<Transaction>> STACK =
            ThreadLocal.withInitial(java.util.ArrayDeque::new);

    final java.util.LinkedHashMap<SnapshotJournal<?>, Object> pumpkinSnapshots =
            new java.util.LinkedHashMap<>();

    private final int pumpkinDepth;

    private boolean committed;

    public static Transaction openRoot() {
        java.util.ArrayDeque<Transaction> stack = STACK.get();
        if (!stack.isEmpty()) {
            throw new IllegalStateException(
                    "A transaction is already open on this thread; use open(parent).");
        }
        Transaction transaction = new Transaction(null, 0, null);
        stack.push(transaction);
        return transaction;
    }

    public static Transaction open(TransactionContext parent) {
        java.util.ArrayDeque<Transaction> stack = STACK.get();
        if (stack.isEmpty() || stack.peek() != parent) {
            throw new IllegalStateException("Parent is not the current open transaction.");
        }
        Transaction transaction = new Transaction(null, parent.depth() + 1, null);
        stack.push(transaction);
        return transaction;
    }

    public static TransactionContext getCurrentOpenedTransaction() {
        return STACK.get().peek();
    }

    public void commit() {
        committed = true;
    }

    @Override
    public void close() {
        java.util.ArrayDeque<Transaction> stack = STACK.get();
        if (stack.peek() != this) {
            throw new IllegalStateException("Closing a transaction that is not the innermost.");
        }
        stack.pop();
        if (!committed) {
            java.util.ArrayList<java.util.Map.Entry<SnapshotJournal<?>, Object>> entries =
                    new java.util.ArrayList<>(pumpkinSnapshots.entrySet());
            for (int i = entries.size() - 1; i >= 0; i--) {
                pumpkinRevert(entries.get(i).getKey(), entries.get(i).getValue());
            }
            return;
        }
        Transaction parent = stack.peek();
        if (parent != null) {
            // The parent keeps its own older snapshot where it has one; otherwise it
            // inherits ours, so an abort above still reverts to the true original.
            for (var entry : pumpkinSnapshots.entrySet()) {
                parent.pumpkinSnapshots.putIfAbsent(entry.getKey(), entry.getValue());
            }
            return;
        }
        for (var entry : pumpkinSnapshots.entrySet()) {
            pumpkinRootCommit(entry.getKey(), entry.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void pumpkinRevert(SnapshotJournal<T> journal, Object snapshot) {
        journal.revertToSnapshot((T) snapshot);
    }

    @SuppressWarnings("unchecked")
    private static <T> void pumpkinRootCommit(SnapshotJournal<T> journal, Object snapshot) {
        journal.onRootCommit((T) snapshot);
    }

    @Override
    public int depth() {
        return pumpkinDepth;
    }

    @Override
    public String toString() {
        return "Transaction(depth=" + pumpkinDepth + ", committed=" + committed + ")";
    }

    boolean open;

    Transaction(TransactionManager manager, int depth, Class<?> callerClass) {
        this.pumpkinDepth = depth;
    }

    public enum Lifecycle {

        NONE, OPEN, CLOSING, ROOT_CLOSING
    }

    public Transaction() {
        this(null, 0, null);
    }
}
