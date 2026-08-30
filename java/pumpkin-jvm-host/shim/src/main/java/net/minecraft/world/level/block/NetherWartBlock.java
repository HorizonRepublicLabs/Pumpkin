package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class NetherWartBlock extends VegetationBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    public MapCodec<NetherWartBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/NetherWartBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public NetherWartBlock(BlockBehaviour.Properties properties) {
        // Pumpkin divergence: chains the properties up. Without this the block's
        // template (and everything else recorded on Properties) silently resets
        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.
        super(properties);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/NetherWartBlock.getShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/NetherWartBlock.mayPlaceOn:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z");
    }

    protected boolean isRandomlyTicking(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/NetherWartBlock.isRandomlyTicking:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/NetherWartBlock.randomTick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/NetherWartBlock.getCloneItemStack:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/item/ItemStack;");
    }

    // Pumpkin divergence: vanilla body -- the properties this block declares.
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public NetherWartBlock() {
    }
}
