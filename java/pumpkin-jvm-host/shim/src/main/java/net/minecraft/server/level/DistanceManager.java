package net.minecraft.server.level;

import java.util.concurrent.Executor;
import net.minecraft.world.level.TicketStorage;
import dev.pumpkin.shim.Unimplemented;

public abstract class DistanceManager {

    protected DistanceManager(TicketStorage ticketStorage, Executor executor, Executor mainThreadExecutor) {
    }

    protected abstract boolean isChunkToRemove(final long node);

    protected abstract ChunkHolder getChunk(final long node);

    protected abstract ChunkHolder updateChunkScheduling(final long node, final int level, final ChunkHolder chunk, final int oldLevel);

    private class FixedPlayerDistanceChunkTracker extends ChunkTracker {

        protected FixedPlayerDistanceChunkTracker(int maxDistance) {
        }

        protected int getLevel(long node) {
            throw Unimplemented.forMember("net/minecraft/server/level/DistanceManager$FixedPlayerDistanceChunkTracker.getLevel:(J)I");
        }

        protected void setLevel(long node, int level) {
            throw Unimplemented.forMember("net/minecraft/server/level/DistanceManager$FixedPlayerDistanceChunkTracker.setLevel:(JI)V");
        }

        protected int getLevelFromSource(long to) {
            throw Unimplemented.forMember("net/minecraft/server/level/DistanceManager$FixedPlayerDistanceChunkTracker.getLevelFromSource:(J)I");
        }

        protected FixedPlayerDistanceChunkTracker() {
        }
    }

    private class PlayerTicketTracker extends DistanceManager.FixedPlayerDistanceChunkTracker {

        protected PlayerTicketTracker(int maxDistance) {
        }

        protected void onLevelChange(long node, int oldLevel, int level) {
            throw Unimplemented.forMember("net/minecraft/server/level/DistanceManager$PlayerTicketTracker.onLevelChange:(JII)V");
        }

        private void onLevelChange(long key, int level, boolean saw, boolean sees) {
            throw Unimplemented.forMember("net/minecraft/server/level/DistanceManager$PlayerTicketTracker.onLevelChange:(JIZZ)V");
        }

        public void runAllUpdates() {
            throw Unimplemented.forMember("net/minecraft/server/level/DistanceManager$PlayerTicketTracker.runAllUpdates:()V");
        }

        protected PlayerTicketTracker() {
        }
    }

    public DistanceManager() {
    }
}
