package net.minecraft.world.level.entity;

import net.minecraft.server.level.FullChunkStatus;
import net.minecraft.world.level.ChunkPos;

public interface ChunkStatusUpdateListener {

    void onChunkStatusChange(ChunkPos pos, FullChunkStatus chunkStatus);
}
