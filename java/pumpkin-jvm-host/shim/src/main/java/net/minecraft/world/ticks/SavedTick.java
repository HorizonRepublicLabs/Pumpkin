package net.minecraft.world.ticks;

import net.minecraft.core.BlockPos;

public record SavedTick<T>(T type, BlockPos pos, int delay, TickPriority priority) {
}
