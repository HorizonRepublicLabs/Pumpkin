package net.minecraft.world.level.chunk;

import net.minecraft.core.IdMap;

public abstract class Strategy<T> {

    private Strategy(IdMap<T> globalMap, int bitsPerAxis) {
    }

    protected abstract Configuration getConfigurationForBitCount(int entryBits);

    public Strategy() {
    }
}
