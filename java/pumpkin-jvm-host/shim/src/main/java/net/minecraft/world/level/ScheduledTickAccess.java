package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.ticks.LevelTickAccess;
import net.minecraft.world.ticks.ScheduledTick;
import net.minecraft.world.ticks.TickPriority;
import dev.pumpkin.shim.Unimplemented;

public interface ScheduledTickAccess {

    <T> ScheduledTick<T> createTick(BlockPos pos, T type, int tickDelay, TickPriority priority);

    <T> ScheduledTick<T> createTick(BlockPos pos, T type, int tickDelay);

    LevelTickAccess<Block> getBlockTicks();

    default void scheduleTick(BlockPos pos, Block type, int tickDelay, TickPriority priority) {
        throw Unimplemented.forMember("net/minecraft/world/level/ScheduledTickAccess.scheduleTick:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;ILnet/minecraft/world/ticks/TickPriority;)V");
    }

    default void scheduleTick(BlockPos pos, Block type, int tickDelay) {
        throw Unimplemented.forMember("net/minecraft/world/level/ScheduledTickAccess.scheduleTick:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V");
    }

    LevelTickAccess<Fluid> getFluidTicks();

    default void scheduleTick(BlockPos pos, Fluid type, int tickDelay, TickPriority priority) {
        throw Unimplemented.forMember("net/minecraft/world/level/ScheduledTickAccess.scheduleTick:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/Fluid;ILnet/minecraft/world/ticks/TickPriority;)V");
    }

    default void scheduleTick(BlockPos pos, Fluid type, int tickDelay) {
        throw Unimplemented.forMember("net/minecraft/world/level/ScheduledTickAccess.scheduleTick:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/Fluid;I)V");
    }
}
