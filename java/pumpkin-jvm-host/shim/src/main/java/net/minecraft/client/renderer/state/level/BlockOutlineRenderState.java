package net.minecraft.client.renderer.state.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;

public record BlockOutlineRenderState(BlockPos pos, boolean isTranslucent, boolean highContrast, VoxelShape shape, VoxelShape collisionShape, VoxelShape occlusionShape, VoxelShape interactionShape, java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer> customRenderers) {

    public BlockOutlineRenderState(BlockPos pos, boolean isTranslucent, boolean highContrast, VoxelShape shape, VoxelShape collisionShape, VoxelShape occlusionShape, VoxelShape interactionShape) {
        this((BlockPos) null, (boolean) false, (boolean) false, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer>) null);
    }

    public BlockOutlineRenderState(BlockPos pos, boolean isTranslucent, boolean highContrast, VoxelShape shape) {
        this((BlockPos) null, (boolean) false, (boolean) false, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer>) null);
    }

    public BlockOutlineRenderState(BlockPos pos, boolean isTranslucent, boolean highContrast, VoxelShape shape, java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer> customRenderers) {
        this((BlockPos) null, (boolean) false, (boolean) false, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer>) null);
    }
}
