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
         * Hands over what the real container holds, and records the take.
         *
         * <p>The amounts come from this tick's snapshot of the real container, so a take
         * can only remove what the Rust side reported a moment earlier. The journal
         * entry names the slot, the item and the count; the tick reply removes exactly
         * that, and refuses with a warning if the slot changed underneath in the
         * meantime. A take inside a transaction that rolls back never reaches the
         * committed list, so the real container is untouched.
         */
        @Override
        public int extract(int index, ItemResource resource, int amount, TransactionContext transaction) {
            if (resource == null || resource.isEmpty() || amount <= 0) {
                return 0;
            }
            if (index < 0 || index >= slots.size() || remaining[index] <= 0) {
                return 0;
            }
            Slot slot = slots.get(index);
            if (!pumpkinSameItem(slot.itemId(),
                    PumpkinInteractions.pumpkinItemId(resource.toStack(1)))) {
                return 0;
            }
            int take = Math.min(amount, remaining[index]);
            updateSnapshots(transaction);
            remaining[index] -= take;
            taken.add(pos.getX() + "/" + pos.getY() + "/" + pos.getZ() + "|"
                    + slot.index() + "*" + slot.itemId() + "*" + take);
            return take;
        }

        /** Takes from every reported slot holding the asked-for item, up to {@code amount}. */
        @Override
        public int extract(ItemResource resource, int amount, TransactionContext transaction) {
            int total = 0;
            for (int i = 0; i < slots.size() && total < amount; i++) {
                total += extract(i, resource, amount - total, transaction);
            }
            return total;
        }
    }

    /** True when two item ids name the same item, one of them missing its namespace. */
    private static boolean pumpkinSameItem(String reported, String asked) {
        return reported.equals(asked) || pumpkinQualify(reported).equals(pumpkinQualify(asked));
    }

    private static String pumpkinQualify(String id) {
        return id.indexOf(':') < 0 ? "minecraft:" + id : id;
    }
}
