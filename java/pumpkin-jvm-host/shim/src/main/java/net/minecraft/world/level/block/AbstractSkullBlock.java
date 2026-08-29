package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractSkullBlock extends BaseEntityBlock {

    public AbstractSkullBlock(SkullBlock.Type type, BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.<init>:(Lnet/minecraft/world/level/block/SkullBlock$Type;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V");
    }

    protected abstract MapCodec<? extends AbstractSkullBlock> codec();

    public BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.newBlockEntity:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.getTicker:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntityType;)Lnet/minecraft/world/level/block/entity/BlockEntityTicker;");
    }

    public SkullBlock.Type getType() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.getType:()Lnet/minecraft/world/level/block/SkullBlock$Type;");
    }

    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.isPathfindable:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/pathfinder/PathComputationType;)Z");
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.getStateForPlacement:(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, Orientation orientation, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.neighborChanged:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;Z)V");
    }

    protected AbstractSkullBlock() {
    }
}
