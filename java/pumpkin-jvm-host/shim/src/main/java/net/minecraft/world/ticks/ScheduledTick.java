package net.minecraft.world.ticks;

import net.minecraft.core.BlockPos;

public record ScheduledTick<T>(T type, BlockPos pos, long triggerTick, TickPriority priority, long subTickOrder) {

    public ScheduledTick(T type, BlockPos pos, long triggerTick, long subTickOrder) {
        this((T) null, (BlockPos) null, (long) 0L, (TickPriority) null, (long) 0L);
    }
}
