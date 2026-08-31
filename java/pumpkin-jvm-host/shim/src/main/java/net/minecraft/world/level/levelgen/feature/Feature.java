package net.minecraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import dev.pumpkin.shim.Unimplemented;

public abstract class Feature<FC extends FeatureConfiguration> {

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/Feature.register:(Ljava/lang/String;Lnet/minecraft/world/level/levelgen/feature/Feature;)Lnet/minecraft/world/level/levelgen/feature/Feature;");
    }

    public Feature(Codec<FC> codec) {
    }

    public abstract boolean place(final FeaturePlaceContext<FC> context);

    public boolean place(FC config, WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/Feature.place:(Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Z");
    }

    public static boolean isAdjacentToAir(Function<BlockPos, BlockState> blockGetter, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/Feature.isAdjacentToAir:(Ljava/util/function/Function;Lnet/minecraft/core/BlockPos;)Z");
    }

    public Feature() {
    }
}
