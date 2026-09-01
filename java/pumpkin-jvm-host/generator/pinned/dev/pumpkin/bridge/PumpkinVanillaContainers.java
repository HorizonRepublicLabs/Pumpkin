package dev.pumpkin.bridge;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

/**
 * The item handler a mod sees when it pushes into an adjacent vanilla container.
 *
 * <p>The Rust side tells the bridge which neighbors really hold an inventory; for those
 * positions this handler accepts items into a per-tick buffer (transactionally -- an
 * aborted simulation leaves nothing behind), and the tick reply drains the buffer so the
 * Rust side lands the items in the actual chest, dropping only what genuinely has no
 * room. One divergence, deliberate: the buffer accepts a full stack regardless of the
 * chest's current fill, so an overfull push becomes a drop at the chest rather than a
 * refusal -- the bridge cannot ask the chest synchronously.
 */
public final class PumpkinVanillaContainers {
    private PumpkinVanillaContainers() {
    }

    /** {@code x/y/z|namespace:path:count} entries pushed this tick. */
    private static final List<String> PUSHED = new ArrayList<>();

    /** Drains what mods pushed since the last drain, for the tick reply. */
    public static List<String> pumpkinDrainPushed() {
        List<String> drained = List.copyOf(PUSHED);
        PUSHED.clear();
        return drained;
    }

    /** A transaction-aware accepting handler for the vanilla container at {@code pos}. */
    public static ResourceHandler<ItemResource> handlerFor(BlockPos pos) {
        return new BufferHandler(pos);
    }

    private static final class BufferHandler extends SnapshotJournal<List<String>>
            implements ResourceHandler<ItemResource> {
        private final BlockPos pos;
        private final List<String> accepted = new ArrayList<>();

        private BufferHandler(BlockPos pos) {
            this.pos = pos;
        }

        @Override
        protected List<String> createSnapshot() {
            return List.copyOf(accepted);
        }

        @Override
        protected void revertToSnapshot(List<String> snapshot) {
            accepted.clear();
            accepted.addAll(snapshot);
        }

        @Override
        protected void onRootCommit(List<String> originalState) {
            PUSHED.addAll(accepted);
            accepted.clear();
        }

        @Override
        public int size() {
            return 1;
        }

        @Override
        public ItemResource getResource(int index) {
            return ItemResource.EMPTY;
        }

        @Override
        public long getAmountAsLong(int index) {
            return 0;
        }

        @Override
        public long getCapacityAsLong(int index, ItemResource resource) {
            return 64;
        }

        @Override
        public boolean isValid(int index, ItemResource resource) {
            return true;
        }

        @Override
        public int insert(int index, ItemResource resource, int amount, TransactionContext transaction) {
            if (resource == null || resource.isEmpty() || amount <= 0) {
                return 0;
            }
            updateSnapshots(transaction);
            accepted.add(pos.getX() + "/" + pos.getY() + "/" + pos.getZ() + "|"
                    + PumpkinInteractions.pumpkinItemId(resource.toStack(1)) + ":" + amount);
            return amount;
        }

        @Override
        public int insert(ItemResource resource, int amount, TransactionContext transaction) {
            return insert(0, resource, amount, transaction);
        }

        @Override
        public int extract(int index, ItemResource resource, int amount, TransactionContext transaction) {
            return 0;
        }

        @Override
        public int extract(ItemResource resource, int amount, TransactionContext transaction) {
            return 0;
        }
    }
}
