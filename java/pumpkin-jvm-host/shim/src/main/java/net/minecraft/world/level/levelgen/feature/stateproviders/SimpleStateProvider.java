package net.minecraft.world.level.levelgen.feature.stateproviders;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class SimpleStateProvider extends BlockStateProvider {

    public static final MapCodec<SimpleStateProvider> CODEC = null;

    protected SimpleStateProvider(BlockState state) {
    }

    protected BlockStateProviderType<?> type() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/stateproviders/SimpleStateProvider.type:()Lnet/minecraft/world/level/levelgen/feature/stateproviders/BlockStateProviderType;");
    }

    public BlockState getState(WorldGenLevel level, RandomSource random, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/stateproviders/SimpleStateProvider.getState:(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public SimpleStateProvider() {
    }
}
