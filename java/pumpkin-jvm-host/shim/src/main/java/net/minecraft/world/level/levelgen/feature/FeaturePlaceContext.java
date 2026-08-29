package net.minecraft.world.level.levelgen.feature;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class FeaturePlaceContext<FC extends FeatureConfiguration> {

    private final WorldGenLevel level = Stubs.of(WorldGenLevel.class, "net/minecraft/world/level/WorldGenLevel");

    private final RandomSource random = Stubs.of(RandomSource.class, "net/minecraft/util/RandomSource");

    private final BlockPos origin = null;

    private final FC config = null;

    public FeaturePlaceContext(Optional<ConfiguredFeature<?, ?>> topFeature, WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin, FC config) {
    }

    public WorldGenLevel level() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/FeaturePlaceContext.level:()Lnet/minecraft/world/level/WorldGenLevel;");
    }

    public RandomSource random() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/FeaturePlaceContext.random:()Lnet/minecraft/util/RandomSource;");
    }

    public BlockPos origin() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/FeaturePlaceContext.origin:()Lnet/minecraft/core/BlockPos;");
    }

    public FC config() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/FeaturePlaceContext.config:()Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;");
    }

    public FeaturePlaceContext() {
    }
}
