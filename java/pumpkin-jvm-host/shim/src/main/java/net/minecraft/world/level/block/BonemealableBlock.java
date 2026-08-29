package net.minecraft.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public interface BonemealableBlock {

    boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state);

    boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state);

    void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state);

    default BonemealableBlock.Type getType() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/BonemealableBlock.getType:()Lnet/minecraft/world/level/block/BonemealableBlock$Type;");
    }

    enum Type {

        NEIGHBOR_SPREADER, GROWER
    }
}
