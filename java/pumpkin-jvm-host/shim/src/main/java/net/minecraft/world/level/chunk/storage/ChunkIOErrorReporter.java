package net.minecraft.world.level.chunk.storage;

import net.minecraft.world.level.ChunkPos;

public interface ChunkIOErrorReporter {

    void reportChunkLoadFailure(Throwable throwable, RegionStorageInfo storageInfo, ChunkPos pos);

    void reportChunkSaveFailure(Throwable throwable, RegionStorageInfo storageInfo, ChunkPos pos);
}
