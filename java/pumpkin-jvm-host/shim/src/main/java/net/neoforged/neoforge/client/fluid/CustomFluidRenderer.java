package net.neoforged.neoforge.client.fluid;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.FluidRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public interface CustomFluidRenderer {

    boolean renderFluid(FluidRenderer fluidRenderer, FluidState fluidState, BlockAndTintGetter getter, BlockPos pos, FluidRenderer.Output output, BlockState blockState);
}
