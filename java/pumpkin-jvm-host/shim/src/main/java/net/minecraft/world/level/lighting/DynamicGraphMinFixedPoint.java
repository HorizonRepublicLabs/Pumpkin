package net.minecraft.world.level.lighting;

import dev.pumpkin.shim.Unimplemented;

public abstract class DynamicGraphMinFixedPoint {

    protected DynamicGraphMinFixedPoint(int levelCount, int minQueueSize, int minMapSize) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/DynamicGraphMinFixedPoint.<init>:(III)V");
    }

    protected boolean isSource(long node) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/DynamicGraphMinFixedPoint.isSource:(J)Z");
    }

    protected abstract int getComputedLevel(final long node, final long knownParent, final int knownLevelFromParent);

    protected abstract void checkNeighborsAfterUpdate(final long node, final int level, final boolean onlyDecrease);

    protected abstract int getLevel(long node);

    protected abstract void setLevel(long node, int level);

    protected abstract int computeLevelFromNeighbor(long from, long to, final int fromLevel);

    public DynamicGraphMinFixedPoint() {
    }
}
