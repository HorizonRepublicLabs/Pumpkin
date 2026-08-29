package net.minecraft.world.level.levelgen.carver;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import dev.pumpkin.shim.Unimplemented;

public class CarvingContext extends WorldGenerationContext {

    public CarvingContext(NoiseBasedChunkGenerator generator, RegistryAccess registryAccess, LevelHeightAccessor heightAccessor, NoiseChunk noiseChunk, RandomState randomState, SurfaceRules.RuleSource surfaceRule) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/carver/CarvingContext.<init>:(Lnet/minecraft/world/level/levelgen/NoiseBasedChunkGenerator;Lnet/minecraft/core/RegistryAccess;Lnet/minecraft/world/level/LevelHeightAccessor;Lnet/minecraft/world/level/levelgen/NoiseChunk;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/levelgen/SurfaceRules$RuleSource;)V");
    }

    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/carver/CarvingContext.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
    }

    protected CarvingContext() {
    }
}
