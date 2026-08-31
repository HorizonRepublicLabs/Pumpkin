package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public interface SignalGetter extends BlockGetter {

    default int getSignal(BlockPos pos, Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/level/SignalGetter.getSignal:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)I");
    }

    default boolean hasNeighborSignal(BlockPos blockPos) {
        throw Unimplemented.forMember("net/minecraft/world/level/SignalGetter.hasNeighborSignal:(Lnet/minecraft/core/BlockPos;)Z");
    }

    default int getBestNeighborSignal(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/SignalGetter.getBestNeighborSignal:(Lnet/minecraft/core/BlockPos;)I");
    }
}
