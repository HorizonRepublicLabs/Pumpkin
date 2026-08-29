package net.minecraft.client.renderer.block;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Unimplemented;

public class FluidRenderer {

    public FluidRenderer(FluidStateModelSet fluidModels) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidRenderer.<init>:(Lnet/minecraft/client/renderer/block/FluidStateModelSet;)V");
    }

    private float getHeight(BlockAndTintGetter level, Fluid fluidType, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidRenderer.getHeight:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/world/level/material/Fluid;Lnet/minecraft/core/BlockPos;)F");
    }

    public interface Output {

        VertexConsumer getBuilder(ChunkSectionLayer layer);
    }

    public FluidRenderer() {
    }
}
