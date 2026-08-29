package net.minecraft.world.level.chunk;

import java.io.IOException;
import java.util.function.BooleanSupplier;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.lighting.LevelLightEngine;
import dev.pumpkin.shim.Unimplemented;

public abstract class ChunkSource implements AutoCloseable, LightChunkGetter {

    public LightChunk getChunkForLighting(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkSource.getChunkForLighting:(II)Lnet/minecraft/world/level/chunk/LightChunk;");
    }

    public boolean hasChunk(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkSource.hasChunk:(II)Z");
    }

    public abstract ChunkAccess getChunk(int x, int z, ChunkStatus targetStatus, boolean loadOrGenerate);

    public abstract void tick(BooleanSupplier haveTime, final boolean tickChunks);

    public abstract String gatherStats();

    public abstract int getLoadedChunksCount();

    public void close() throws IOException {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkSource.close:()V");
    }

    public abstract LevelLightEngine getLightEngine();

    protected ChunkSource() {
    }
}
