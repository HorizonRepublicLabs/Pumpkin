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

    /** {@code x/y/z|slot*id*count} entries taken out of a real container this tick. */
    private static final List<String> PULLED = new ArrayList<>();

    /** Said once: mods asking to take from a vanilla container get nothing, and why. */
    private static final java.util.concurrent.atomic.AtomicBoolean PUMPKIN_REFUSAL_SAID =
            new java.util.concurrent.atomic.AtomicBoolean();

    /** One occupied slot of a real container, as the Rust side reported it. */
    record Slot(int index, String itemId, int count) {
    }

    /** Contents by packed position, refreshed every tick before the mod runs. */
    private static final java.util.Map<Long, List<Slot>> CONTENTS = new java.util.HashMap<>();

    /**
     * One handler per container position, kept for the life of the run.
     *
     * <p>Vanilla hands out a capability instance that stays the same object for a given
     * position, and mods rely on that: a transporter inserts through one lookup and
     * consults its own bookkeeping through another, matching them by identity. Minting a
     * fresh handler per query made those two look like different inventories, and the
     * item in flight belonged to neither.
     */
    private static final java.util.Map<Long, BufferHandler> HANDLERS = new java.util.HashMap<>();

    /**
     * Takes the tick's snapshot of what the neighbouring vanilla containers hold.
     *
     * <p>Format is the bridge's own: {@code x/y/z|slot*id*count,...} per container,
     * containers separated by {@code ;}. Empty means no neighbour holds anything.
     */
    static void pumpkinSetContents(String contents) {
        CONTENTS.clear();
        if (contents == null || contents.isEmpty()) {
            pumpkinRefreshHandlers();
            return;
        }
        for (String entry : contents.split(";")) {
            int bar = entry.indexOf('|');
            if (bar < 0) {
                continue;
            }
            String[] coords = entry.substring(0, bar).split("/");
            if (coords.length != 3) {
                continue;
            }
            long key = net.minecraft.core.BlockPos.asLong(Integer.parseInt(coords[0]),
                    Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
            List<Slot> slots = new ArrayList<>();
            for (String slot : entry.substring(bar + 1).split(",")) {
                String[] fields = slot.split("\\*");
                if (fields.length == 3) {
                    slots.add(new Slot(Integer.parseInt(fields[0]), fields[1],
                            Integer.parseInt(fields[2])));
                }
            }
            CONTENTS.put(key, slots);
        }
        pumpkinRefreshHandlers();
    }

    /** Points the live handlers at this tick's contents, keeping their identity. */
    private static void pumpkinRefreshHandlers() {
        for (java.util.Map.Entry<Long, BufferHandler> entry : HANDLERS.entrySet()) {
            List<Slot> slots = CONTENTS.get(entry.getKey());
            if (slots != null) {
                entry.getValue().pumpkinRefresh(slots);
            }
        }
    }

    /** Drains what mods pushed since the last drain, for the tick reply. */
    public static List<String> pumpkinDrainPushed() {
        List<String> drained = List.copyOf(PUSHED);
        PUSHED.clear();
        return drained;
    }

    /** Drains what mods took out of real containers, for the tick reply. */
    public static List<String> pumpkinDrainPulled() {
        List<String> drained = List.copyOf(PULLED);
        PULLED.clear();
        return drained;
    }

    /** The transaction-aware handler over the vanilla container at {@code pos}. */
    public static ResourceHandler<ItemResource> handlerFor(BlockPos pos) {
        return HANDLERS.computeIfAbsent(pos.asLong(),
                key -> new BufferHandler(pos.immutable(), CONTENTS.getOrDefault(key, List.of())));
    }

    /** The two buffers one handler journals: what it accepted, and what it took. */
    private record Buffers(List<String> accepted, List<String> taken, int[] remaining) {
    }

    private static final class BufferHandler extends SnapshotJournal<Buffers>
            implements ResourceHandler<ItemResource> {
        private final BlockPos pos;
        /**
         * The occupied slots the Rust side reported this tick, in order. Index 0 is the
         * one slot a push targets (the Rust side lands pushes first-fit, so which slot a
         * mod names does not matter); the rest are read and extract views.
         */
        private List<Slot> slots;
        /** How much of each reported slot is still available, after this tick's takes. */
        private int[] remaining;
        private final List<String> accepted = new ArrayList<>();
        private final List<String> taken = new ArrayList<>();

        private BufferHandler(BlockPos pos, List<Slot> slots) {
            this.pos = pos;
            this.slots = slots;
            this.remaining = new int[slots.size()];
            for (int i = 0; i < slots.size(); i++) {
                this.remaining[i] = slots.get(i).count();
            }
        }

        /** Re-points this handler at the tick's fresh view of the real container. */
        private void pumpkinRefresh(List<Slot> current) {
            slots = current;
            remaining = new int[current.size()];
            for (int i = 0; i < current.size(); i++) {
                remaining[i] = current.get(i).count();
            }
        }

        @Override
        protected Buffers createSnapshot() {
            return new Buffers(List.copyOf(accepted), List.copyOf(taken), remaining.clone());
        }

        @Override
        protected void revertToSnapshot(Buffers snapshot) {
            accepted.clear();
            accepted.addAll(snapshot.accepted());
            taken.clear();
            taken.addAll(snapshot.taken());
            // Length can differ from the snapshot's when a tick refreshed the view
            // mid-transaction; the fresh view is the newer truth, so keep it.
            if (snapshot.remaining().length == remaining.length) {
                System.arraycopy(snapshot.remaining(), 0, remaining, 0, remaining.length);
            }
        }

        @Override
        protected void onRootCommit(Buffers originalState) {
            PUSHED.addAll(accepted);
            PULLED.addAll(taken);
            accepted.clear();
            taken.clear();
        }

        @Override
        public int size() {
            // At least one slot, so a push always has somewhere to name.
            return Math.max(1, slots.size());
        }

        @Override
        public ItemResource getResource(int index) {
            if (index < 0 || index >= slots.size() || remaining[index] <= 0) {
                return ItemResource.EMPTY;
            }
            return ItemResource.of(
                    PumpkinInteractions.pumpkinBuildStack(slots.get(index).itemId(), 1));
        }

        @Override
        public long getAmountAsLong(int index) {
            return index < 0 || index >= remaining.length ? 0 : remaining[index];
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

        /**
         * Refuses, for now: a vanilla container reads as full but gives nothing up.
         *
         * <p>The read side above is real -- a mod sees what a chest holds and can route
         * on it -- and the take side is written and works up to the last step: Mekanism
         * simulates the take in a nested transaction, aborts it, then re-takes for real
         * through {@code HandlerItemData.use}, and that second take is not completing
         * against this handler. Until it does, an accepted take would leave the item in
         * the mod's hands *and* in the chest, which duplicates it. Refusing is the
         * honest answer while the handshake is unfinished; a mod reads it as a container
         * that will not give, which is a state vanilla has too.
         */
        /**
         * Refuses, for now: a vanilla container reads as full but gives nothing up.
         *
         * <p>The read side above is real -- a mod sees what a chest holds and routes on
         * it. The take side is written on both sides (this journal records what was
         * taken; the tick reply's {@code PULLED=} entries remove it from the real
         * container) and is exercised only by simulation so far: Mekanism's transporter
         * asks in a nested scope it then rolls back, and the real take never follows.
         * Until that handshake completes, accepting a take would leave the stack in the
         * mod's hands *and* in the chest -- duplication -- so the container gives
         * nothing. A mod reads that as a container that will not give, a state vanilla
         * has too.
         */
        @Override
        public int extract(int index, ItemResource resource, int amount, TransactionContext transaction) {
            if (PUMPKIN_REFUSAL_SAID.compareAndSet(false, true)) {
                System.err.println("[pumpkin] a mod asked to take items out of a vanilla"
                        + " container; that path is not finished, so the container gives"
                        + " nothing rather than risk duplicating the stack");
            }
            return 0;
        }

        @Override
        public int extract(ItemResource resource, int amount, TransactionContext transaction) {
            return extract(0, resource, amount, transaction);
        }
    }
}
