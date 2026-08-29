package net.minecraft.server.level;

import net.minecraft.world.level.lighting.DynamicGraphMinFixedPoint;
import dev.pumpkin.shim.Unimplemented;

public abstract class ChunkTracker extends DynamicGraphMinFixedPoint {

    protected ChunkTracker(int levelCount, int minQueueSize, int minMapSize) {
    }

    protected boolean isSource(long node) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkTracker.isSource:(J)Z");
    }

    protected void checkNeighborsAfterUpdate(long node, int level, boolean onlyDecrease) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkTracker.checkNeighborsAfterUpdate:(JIZ)V");
    }

    protected int getComputedLevel(long node, long knownParent, int knownLevelFromParent) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkTracker.getComputedLevel:(JJI)I");
    }

    protected int computeLevelFromNeighbor(long from, long to, int fromLevel) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkTracker.computeLevelFromNeighbor:(JJI)I");
    }

    protected abstract int getLevelFromSource(long to);

    public void update(long node, int newLevelFrom, boolean onlyDecreased) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkTracker.update:(JIZ)V");
    }

    public ChunkTracker() {
    }
}
