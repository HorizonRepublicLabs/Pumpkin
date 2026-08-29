package net.minecraft.server.level;

import net.minecraft.util.StaticCache2D;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import dev.pumpkin.shim.Unimplemented;

public class ChunkGenerationTask {

    private ChunkGenerationTask(GeneratingChunkMap chunkMap, ChunkStatus targetStatus, ChunkPos pos, StaticCache2D<GenerationChunkHolder> cache) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkGenerationTask.<init>:(Lnet/minecraft/server/level/GeneratingChunkMap;Lnet/minecraft/world/level/chunk/status/ChunkStatus;Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/util/StaticCache2D;)V");
    }

    public static ChunkGenerationTask create(GeneratingChunkMap chunkMap, ChunkStatus targetStatus, ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkGenerationTask.create:(Lnet/minecraft/server/level/GeneratingChunkMap;Lnet/minecraft/world/level/chunk/status/ChunkStatus;Lnet/minecraft/world/level/ChunkPos;)Lnet/minecraft/server/level/ChunkGenerationTask;");
    }

    protected ChunkGenerationTask() {
    }
}
