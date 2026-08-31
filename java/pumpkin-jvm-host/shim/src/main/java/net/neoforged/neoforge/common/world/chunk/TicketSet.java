package net.neoforged.neoforge.common.world.chunk;

import it.unimi.dsi.fastutil.longs.LongSet;
import dev.pumpkin.shim.Unimplemented;

public record TicketSet(LongSet normal, LongSet naturalSpawning) {

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketSet.isEmpty:()Z");
    }
}
