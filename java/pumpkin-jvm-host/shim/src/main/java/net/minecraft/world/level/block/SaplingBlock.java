package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class SaplingBlock extends VegetationBlock implements BonemealableBlock {

    protected final TreeGrower treeGrower = null;

    public MapCodec<? extends SaplingBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public SaplingBlock(TreeGrower treeGrower, BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.<init>:(Lnet/minecraft/world/level/block/grower/TreeGrower;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V");
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.getShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.randomTick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.isValidBonemealTarget:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.isBonemealSuccess:(Lnet/minecraft/world/level/Level;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.performBonemeal:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");
    }

    protected SaplingBlock() {
    }
}
