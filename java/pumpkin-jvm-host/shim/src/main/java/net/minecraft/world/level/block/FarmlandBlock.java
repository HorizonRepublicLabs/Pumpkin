package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class FarmlandBlock extends Block {

    public static final IntegerProperty MOISTURE = null;

    public MapCodec<FarmlandBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public FarmlandBlock(BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.<init>:(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V");
    }

    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.updateShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/world/level/ScheduledTickAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.canSurvive:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.getStateForPlacement:(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected boolean useShapeForLightOcclusion(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.useShapeForLightOcclusion:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.getShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.tick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.randomTick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.fallOn:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;D)V");
    }

    private static boolean isNearWater(LevelReader level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.isNearWater:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");
    }

    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.isPathfindable:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/pathfinder/PathComputationType;)Z");
    }

    protected FarmlandBlock() {
    }
}
