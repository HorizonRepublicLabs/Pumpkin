package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.ticks.LevelTickAccess;
import net.minecraft.world.ticks.ScheduledTick;
import net.minecraft.world.ticks.TickPriority;

public interface ScheduledTickAccess {

    <T> ScheduledTick<T> createTick(BlockPos pos, T type, int tickDelay, TickPriority priority);

    <T> ScheduledTick<T> createTick(BlockPos pos, T type, int tickDelay);

    LevelTickAccess<Block> getBlockTicks();

    LevelTickAccess<Fluid> getFluidTicks();
}
