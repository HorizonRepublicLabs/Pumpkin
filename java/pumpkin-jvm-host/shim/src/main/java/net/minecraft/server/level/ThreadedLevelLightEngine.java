package net.minecraft.server.level;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.thread.ConsecutiveExecutor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.chunk.DataLayer;
import net.minecraft.world.level.chunk.LightChunkGetter;
import net.minecraft.world.level.lighting.LevelLightEngine;
import dev.pumpkin.shim.Unimplemented;

public class ThreadedLevelLightEngine extends LevelLightEngine implements AutoCloseable {

    public ThreadedLevelLightEngine(LightChunkGetter lightChunkGetter, ChunkMap chunkMap, boolean hasSkyLight, ConsecutiveExecutor consecutiveExecutor, ChunkTaskDispatcher taskDispatcher) {
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/server/level/ThreadedLevelLightEngine.close:()V");
    }

    public int runLightUpdates() {
        throw Unimplemented.forMember("net/minecraft/server/level/ThreadedLevelLightEngine.runLightUpdates:()I");
    }

    public void checkBlock(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ThreadedLevelLightEngine.checkBlock:(Lnet/minecraft/core/BlockPos;)V");
    }

    public void updateSectionStatus(SectionPos pos, boolean sectionEmpty) {
        throw Unimplemented.forMember("net/minecraft/server/level/ThreadedLevelLightEngine.updateSectionStatus:(Lnet/minecraft/core/SectionPos;Z)V");
    }

    public void propagateLightSources(ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ThreadedLevelLightEngine.propagateLightSources:(Lnet/minecraft/world/level/ChunkPos;)V");
    }

    public void setLightEnabled(ChunkPos pos, boolean enable) {
        throw Unimplemented.forMember("net/minecraft/server/level/ThreadedLevelLightEngine.setLightEnabled:(Lnet/minecraft/world/level/ChunkPos;Z)V");
    }

    public void queueSectionData(LightLayer layer, SectionPos pos, DataLayer data) {
        throw Unimplemented.forMember("net/minecraft/server/level/ThreadedLevelLightEngine.queueSectionData:(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/SectionPos;Lnet/minecraft/world/level/chunk/DataLayer;)V");
    }

    public void retainData(ChunkPos pos, boolean retain) {
        throw Unimplemented.forMember("net/minecraft/server/level/ThreadedLevelLightEngine.retainData:(Lnet/minecraft/world/level/ChunkPos;Z)V");
    }

    private enum TaskType {

        PRE_UPDATE, POST_UPDATE
    }

    public ThreadedLevelLightEngine() {
    }
}
