package net.minecraft.server.level;

import java.util.concurrent.CompletableFuture;
import net.minecraft.util.StaticCache2D;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.chunk.status.ChunkStep;
import dev.pumpkin.shim.Unimplemented;

public abstract class GenerationChunkHolder {

    public GenerationChunkHolder(ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/GenerationChunkHolder.<init>:(Lnet/minecraft/world/level/ChunkPos;)V");
    }

    CompletableFuture<ChunkResult<ChunkAccess>> applyStep(ChunkStep step, GeneratingChunkMap chunkMap, StaticCache2D<GenerationChunkHolder> cache) {
        throw Unimplemented.forMember("net/minecraft/server/level/GenerationChunkHolder.applyStep:(Lnet/minecraft/world/level/chunk/status/ChunkStep;Lnet/minecraft/server/level/GeneratingChunkMap;Lnet/minecraft/util/StaticCache2D;)Ljava/util/concurrent/CompletableFuture;");
    }

    protected abstract void addSaveDependency(final CompletableFuture<?> sync);

    public ChunkStatus getPersistedStatus() {
        throw Unimplemented.forMember("net/minecraft/server/level/GenerationChunkHolder.getPersistedStatus:()Lnet/minecraft/world/level/chunk/status/ChunkStatus;");
    }

    public ChunkPos getPos() {
        throw Unimplemented.forMember("net/minecraft/server/level/GenerationChunkHolder.getPos:()Lnet/minecraft/world/level/ChunkPos;");
    }

    public abstract int getTicketLevel();

    public abstract int getQueueLevel();

    protected GenerationChunkHolder() {
    }
}
