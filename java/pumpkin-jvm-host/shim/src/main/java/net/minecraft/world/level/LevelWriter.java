package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;

public interface LevelWriter {

    boolean setBlock(BlockPos pos, BlockState blockState, int updateFlags, int updateLimit);

    boolean removeBlock(BlockPos pos, boolean movedByPiston);

    boolean destroyBlock(BlockPos pos, boolean dropResources, Entity breaker, int updateLimit);
}
