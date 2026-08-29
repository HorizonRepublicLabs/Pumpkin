package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import dev.pumpkin.shim.Unimplemented;

public abstract class VegetationBlock extends Block {

    public VegetationBlock(BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/VegetationBlock.<init>:(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V");
    }

    protected abstract MapCodec<? extends VegetationBlock> codec();

    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/VegetationBlock.updateShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/world/level/ScheduledTickAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/VegetationBlock.canSurvive:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
    }

    protected boolean propagatesSkylightDown(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/VegetationBlock.propagatesSkylightDown:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/VegetationBlock.isPathfindable:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/pathfinder/PathComputationType;)Z");
    }

    public VegetationBlock() {
    }
}
