package net.neoforged.neoforge.capabilities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface IBlockCapabilityProvider<T, C extends Object> {

    T getCapability(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity, C context);
}
