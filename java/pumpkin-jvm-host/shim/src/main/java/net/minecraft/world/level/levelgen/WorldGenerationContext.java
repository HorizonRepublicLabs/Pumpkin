package net.minecraft.world.level.levelgen;

import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import dev.pumpkin.shim.Unimplemented;

public class WorldGenerationContext {

    public WorldGenerationContext(ChunkGenerator generator, LevelHeightAccessor heightAccessor) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldGenerationContext.<init>:(Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/world/level/LevelHeightAccessor;)V");
    }

    public int getGenDepth() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldGenerationContext.getGenDepth:()I");
    }

    protected WorldGenerationContext() {
    }
}
