package net.minecraft.world.level;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.List;
import net.minecraft.server.level.Ticket;
import net.minecraft.world.level.saveddata.SavedData;

public class TicketStorage extends SavedData {

    private TicketStorage(Long2ObjectOpenHashMap<List<Ticket>> tickets, Long2ObjectOpenHashMap<List<Ticket>> deactivatedTickets) {
    }

    public TicketStorage() {
    }

    public interface ChunkUpdated {

        void update(final long node, final int newLevelFrom, final boolean onlyDecreased);
    }

    public interface TicketPredicate {

        boolean test(Ticket ticket, long chunkPos);
    }
}
