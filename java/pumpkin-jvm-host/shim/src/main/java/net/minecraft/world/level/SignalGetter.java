package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public interface SignalGetter extends BlockGetter {

    default boolean hasNeighborSignal(BlockPos blockPos) {
        throw Unimplemented.forMember("net/minecraft/world/level/SignalGetter.hasNeighborSignal:(Lnet/minecraft/core/BlockPos;)Z");
    }
}
