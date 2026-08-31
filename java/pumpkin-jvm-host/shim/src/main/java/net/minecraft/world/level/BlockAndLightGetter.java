package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.lighting.LevelLightEngine;
import dev.pumpkin.shim.Unimplemented;

public interface BlockAndLightGetter extends BlockGetter {

    LevelLightEngine getLightEngine();

    default boolean canSeeSky(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/BlockAndLightGetter.canSeeSky:(Lnet/minecraft/core/BlockPos;)Z");
    }
}
