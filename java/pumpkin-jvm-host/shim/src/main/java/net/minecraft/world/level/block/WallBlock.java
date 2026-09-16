package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class WallBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;

    public static final EnumProperty<WallSide> NORTH_WALL = BlockStateProperties.NORTH_WALL;

    public static final EnumProperty<WallSide> EAST_WALL = BlockStateProperties.EAST_WALL;

    public static final EnumProperty<WallSide> SOUTH_WALL = BlockStateProperties.SOUTH_WALL;

    public static final EnumProperty<WallSide> WEST_WALL = BlockStateProperties.WEST_WALL;

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public MapCodec<WallBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public WallBlock(BlockBehaviour.Properties properties) {
        // Pumpkin divergence: chains the properties up. Without this the block's
        // template (and everything else recorded on Properties) silently resets
        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.
        super(properties);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.getShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.getCollisionShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.isPathfindable:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/pathfinder/PathComputationType;)Z");
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.getStateForPlacement:(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.updateShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/world/level/ScheduledTickAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected FluidState getFluidState(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.getFluidState:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/material/FluidState;");
    }

    protected boolean propagatesSkylightDown(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.propagatesSkylightDown:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    // Pumpkin divergence: vanilla's declaration, in vanilla's order -- north, east, west,
    // south, which is not the order the names suggest and is what numbers the states. A
    // mod wall inherits its 324 states from here; declaring one instead shifted the id of
    // every block registered after it.
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, NORTH_WALL, EAST_WALL, WEST_WALL, SOUTH_WALL, WATERLOGGED);
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.rotate:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.mirror:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Mirror;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public WallBlock() {
    }
}
