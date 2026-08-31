package net.neoforged.neoforge.common.world.chunk;

import java.util.Map;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.TicketStorage;
import dev.pumpkin.shim.Unimplemented;

public class TicketHelper {

    TicketHelper(TicketStorage saveData, Identifier controllerId, Map<BlockPos, TicketSet> blockTickets, Map<UUID, TicketSet> entityTickets) {
    }

    public Map<BlockPos, TicketSet> getBlockTickets() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketHelper.getBlockTickets:()Ljava/util/Map;");
    }

    public Map<UUID, TicketSet> getEntityTickets() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketHelper.getEntityTickets:()Ljava/util/Map;");
    }

    public void removeAllTickets(BlockPos owner) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketHelper.removeAllTickets:(Lnet/minecraft/core/BlockPos;)V");
    }

    public void removeAllTickets(UUID owner) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketHelper.removeAllTickets:(Ljava/util/UUID;)V");
    }

    private <T extends Comparable<? super T>> void removeAllTickets(ForcedChunkManager.TicketTracker<T> tickets, T owner, TicketSet existingTickets) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketHelper.removeAllTickets:(Lnet/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketTracker;Ljava/lang/Comparable;Lnet/neoforged/neoforge/common/world/chunk/TicketSet;)V");
    }

    public void removeTicket(BlockPos owner, long chunk, boolean forceNaturalSpawning) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketHelper.removeTicket:(Lnet/minecraft/core/BlockPos;JZ)V");
    }

    public void removeTicket(UUID owner, long chunk, boolean forceNaturalSpawning) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketHelper.removeTicket:(Ljava/util/UUID;JZ)V");
    }

    private <T extends Comparable<? super T>> void removeTicket(ForcedChunkManager.TicketTracker<T> tickets, T owner, long chunk, boolean forceNaturalSpawning) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketHelper.removeTicket:(Lnet/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketTracker;Ljava/lang/Comparable;JZ)V");
    }

    public TicketHelper() {
    }
}
