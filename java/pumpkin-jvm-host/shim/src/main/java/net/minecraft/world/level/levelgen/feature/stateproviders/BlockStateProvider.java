package net.minecraft.world.level.levelgen.feature.stateproviders;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockStateProvider {

    public static final Codec<BlockStateProvider> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.CODEC");

    protected abstract BlockStateProviderType<?> type();

    public abstract BlockState getState(final WorldGenLevel level, final RandomSource random, final BlockPos pos);

    public BlockStateProvider() {
    }
}
