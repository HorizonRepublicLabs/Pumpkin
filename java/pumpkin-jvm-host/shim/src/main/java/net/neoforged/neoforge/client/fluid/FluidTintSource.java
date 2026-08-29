package net.neoforged.neoforge.client.fluid;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import dev.pumpkin.shim.Unimplemented;

public interface FluidTintSource extends BlockTintSource {

    int color(FluidState state);

    default int color(BlockState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/fluid/FluidTintSource.color:(Lnet/minecraft/world/level/block/state/BlockState;)I");
    }

    default int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/fluid/FluidTintSource.colorInWorld:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;)I");
    }
}
