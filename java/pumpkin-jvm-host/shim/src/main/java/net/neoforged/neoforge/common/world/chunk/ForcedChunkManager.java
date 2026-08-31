package net.neoforged.neoforge.common.world.chunk;

import java.util.Map;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.TicketStorage;
import dev.pumpkin.shim.Unimplemented;

public class ForcedChunkManager {

    public record OwnedChunks(Identifier controller, Map<BlockPos, TicketSet> blockChunks, Map<UUID, TicketSet> entityChunks) {
    }

    static class TicketOwner<T extends Comparable<? super T>> implements Comparable<TicketOwner<T>> {

        TicketOwner(Identifier id, T owner) {
        }

        public int compareTo(TicketOwner<T> other) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketOwner.compareTo:(Lnet/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketOwner;)I");
        }

        public boolean equals(Object o) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketOwner.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketOwner.hashCode:()I");
        }

        protected TicketOwner() {
        }
    }

    public static class TicketTracker<T extends Comparable<? super T>> {

        public TicketTracker(TicketStorage ticketStorage, Holder<TicketType> ticketType, Holder<TicketType> naturalSpawningTicketType) {
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketTracker.isEmpty:()Z");
        }

        public boolean remove(TicketOwner<T> owner, long chunk, boolean forceNaturalSpawning, boolean targetDeactivated) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketTracker.remove:(Lnet/neoforged/neoforge/common/world/chunk/ForcedChunkManager$TicketOwner;JZZ)Z");
        }

        public TicketTracker() {
        }
    }

    public ForcedChunkManager() {
    }
}
