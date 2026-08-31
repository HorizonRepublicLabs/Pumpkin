package net.minecraft.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record DiskConfiguration(BlockStateProvider stateProvider, BlockPredicate target, IntProvider radius, int halfHeight) implements FeatureConfiguration {

    public static final Codec<DiskConfiguration> CODEC = null;
}
