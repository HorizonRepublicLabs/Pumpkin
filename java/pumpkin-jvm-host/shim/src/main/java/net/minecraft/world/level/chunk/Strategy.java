package net.minecraft.world.level.chunk;

import net.minecraft.core.IdMap;
import dev.pumpkin.shim.Unimplemented;

public abstract class Strategy<T> {

    private Strategy(IdMap<T> globalMap, int bitsPerAxis) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/Strategy.<init>:(Lnet/minecraft/core/IdMap;I)V");
    }

    protected abstract Configuration getConfigurationForBitCount(int entryBits);

    protected Strategy() {
    }
}
