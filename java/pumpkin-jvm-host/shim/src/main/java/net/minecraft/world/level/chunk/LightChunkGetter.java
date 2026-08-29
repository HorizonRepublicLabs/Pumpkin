package net.minecraft.world.level.chunk;

import net.minecraft.world.level.BlockGetter;

public interface LightChunkGetter {

    LightChunk getChunkForLighting(final int x, final int z);

    BlockGetter getLevel();
}
