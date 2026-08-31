package net.minecraft.world.level.levelgen;

import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import dev.pumpkin.shim.Unimplemented;

public class WorldGenerationContext {

    public WorldGenerationContext(ChunkGenerator generator, LevelHeightAccessor heightAccessor) {
    }

    public int getMinGenY() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldGenerationContext.getMinGenY:()I");
    }

    public int getGenDepth() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldGenerationContext.getGenDepth:()I");
    }

    public WorldGenerationContext() {
    }
}
