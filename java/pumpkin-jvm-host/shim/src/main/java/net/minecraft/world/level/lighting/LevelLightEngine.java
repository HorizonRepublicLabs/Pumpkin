package net.minecraft.world.level.lighting;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LightChunkGetter;
import dev.pumpkin.shim.Unimplemented;

public class LevelLightEngine implements LightEventListener {

    public LevelLightEngine(LightChunkGetter chunkSource, boolean hasBlockLight, boolean hasSkyLight) {
    }

    protected LevelLightEngine() {
    }

    public void checkBlock(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/LevelLightEngine.checkBlock:(Lnet/minecraft/core/BlockPos;)V");
    }

    public boolean hasLightWork() {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/LevelLightEngine.hasLightWork:()Z");
    }

    public int runLightUpdates() {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/LevelLightEngine.runLightUpdates:()I");
    }

    public void updateSectionStatus(SectionPos pos, boolean sectionEmpty) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/LevelLightEngine.updateSectionStatus:(Lnet/minecraft/core/SectionPos;Z)V");
    }

    public void setLightEnabled(ChunkPos pos, boolean enable) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/LevelLightEngine.setLightEnabled:(Lnet/minecraft/world/level/ChunkPos;Z)V");
    }

    public void propagateLightSources(ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/LevelLightEngine.propagateLightSources:(Lnet/minecraft/world/level/ChunkPos;)V");
    }
}
