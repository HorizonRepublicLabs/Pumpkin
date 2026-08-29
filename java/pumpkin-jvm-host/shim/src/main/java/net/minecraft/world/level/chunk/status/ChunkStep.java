package net.minecraft.world.level.chunk.status;

import java.util.concurrent.CompletableFuture;
import net.minecraft.server.level.GenerationChunkHolder;
import net.minecraft.util.StaticCache2D;
import net.minecraft.world.level.chunk.ChunkAccess;
import dev.pumpkin.shim.Unimplemented;

public record ChunkStep(ChunkStatus targetStatus, ChunkDependencies directDependencies, ChunkDependencies accumulatedDependencies, int blockStateWriteRadius, ChunkStatusTask task) {

    public CompletableFuture<ChunkAccess> apply(WorldGenContext context, StaticCache2D<GenerationChunkHolder> cache, ChunkAccess chunk) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkStep.apply:(Lnet/minecraft/world/level/chunk/status/WorldGenContext;Lnet/minecraft/util/StaticCache2D;Lnet/minecraft/world/level/chunk/ChunkAccess;)Ljava/util/concurrent/CompletableFuture;");
    }

    public static class Builder {

        protected Builder(ChunkStatus status) {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkStep$Builder.<init>:(Lnet/minecraft/world/level/chunk/status/ChunkStatus;)V");
        }

        protected Builder(ChunkStatus status, ChunkStep parent) {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkStep$Builder.<init>:(Lnet/minecraft/world/level/chunk/status/ChunkStatus;Lnet/minecraft/world/level/chunk/status/ChunkStep;)V");
        }

        public ChunkStep build() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkStep$Builder.build:()Lnet/minecraft/world/level/chunk/status/ChunkStep;");
        }

        protected Builder() {
        }
    }
}
