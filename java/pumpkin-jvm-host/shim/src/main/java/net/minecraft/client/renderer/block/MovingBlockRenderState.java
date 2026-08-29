package net.minecraft.client.renderer.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.FluidState;
import dev.pumpkin.shim.Unimplemented;

public class MovingBlockRenderState implements BlockAndTintGetter {

    public CardinalLighting cardinalLighting() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.cardinalLighting:()Lnet/minecraft/world/level/CardinalLighting;");
    }

    public LevelLightEngine getLightEngine() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.getLightEngine:()Lnet/minecraft/world/level/lighting/LevelLightEngine;");
    }

    public int getBlockTint(BlockPos pos, ColorResolver color) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.getBlockTint:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/ColorResolver;)I");
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.getBlockEntity:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public FluidState getFluidState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.getFluidState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;");
    }

    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.getHeight:()I");
    }

    public int getMinY() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.getMinY:()I");
    }

    public net.neoforged.neoforge.model.data.ModelData getModelData(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/MovingBlockRenderState.getModelData:(Lnet/minecraft/core/BlockPos;)Lnet/neoforged/neoforge/model/data/ModelData;");
    }

    public MovingBlockRenderState() {
    }
}
