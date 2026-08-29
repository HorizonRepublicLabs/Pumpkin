package net.minecraft.client.renderer.state.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public record BlockOutlineRenderState(BlockPos pos, boolean isTranslucent, boolean highContrast, VoxelShape shape, VoxelShape collisionShape, VoxelShape occlusionShape, VoxelShape interactionShape, java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer> customRenderers) {

    public BlockOutlineRenderState(BlockPos pos, boolean isTranslucent, boolean highContrast, VoxelShape shape, VoxelShape collisionShape, VoxelShape occlusionShape, VoxelShape interactionShape) {
        this((BlockPos) null, (boolean) false, (boolean) false, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer>) null);
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/BlockOutlineRenderState.<init>:(Lnet/minecraft/core/BlockPos;ZZLnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/VoxelShape;)V");
    }

    public BlockOutlineRenderState(BlockPos pos, boolean isTranslucent, boolean highContrast, VoxelShape shape) {
        this((BlockPos) null, (boolean) false, (boolean) false, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer>) null);
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/BlockOutlineRenderState.<init>:(Lnet/minecraft/core/BlockPos;ZZLnet/minecraft/world/phys/shapes/VoxelShape;)V");
    }

    public BlockOutlineRenderState(BlockPos pos, boolean isTranslucent, boolean highContrast, VoxelShape shape, java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer> customRenderers) {
        this((BlockPos) null, (boolean) false, (boolean) false, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (VoxelShape) null, (java.util.List<net.neoforged.neoforge.client.CustomBlockOutlineRenderer>) null);
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/BlockOutlineRenderState.<init>:(Lnet/minecraft/core/BlockPos;ZZLnet/minecraft/world/phys/shapes/VoxelShape;Ljava/util/List;)V");
    }
}
