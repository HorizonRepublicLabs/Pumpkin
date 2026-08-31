package net.neoforged.neoforge.event.level;

import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public class ChunkTicketLevelUpdatedEvent extends Event {

    public ChunkTicketLevelUpdatedEvent(ServerLevel level, long chunkPos, int oldTicketLevel, int newTicketLevel, ChunkHolder chunkHolder) {
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/ChunkTicketLevelUpdatedEvent.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public long getChunkPos() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/ChunkTicketLevelUpdatedEvent.getChunkPos:()J");
    }

    public int getOldTicketLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/ChunkTicketLevelUpdatedEvent.getOldTicketLevel:()I");
    }

    public int getNewTicketLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/ChunkTicketLevelUpdatedEvent.getNewTicketLevel:()I");
    }

    public ChunkTicketLevelUpdatedEvent() {
    }
}
