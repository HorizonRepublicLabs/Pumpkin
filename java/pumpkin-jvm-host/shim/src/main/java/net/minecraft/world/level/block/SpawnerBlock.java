package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class SpawnerBlock extends BaseEntityBlock {

    public MapCodec<SpawnerBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SpawnerBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public SpawnerBlock(BlockBehaviour.Properties properties) {
    }

    public BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SpawnerBlock.newBlockEntity:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SpawnerBlock.getTicker:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntityType;)Lnet/minecraft/world/level/block/entity/BlockEntityTicker;");
    }

    protected void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SpawnerBlock.spawnAfterBreak:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;Z)V");
    }

    public int getExpDrop(BlockState state, net.minecraft.world.level.LevelAccessor level, BlockPos pos, net.minecraft.world.level.block.entity.BlockEntity blockEntity, net.minecraft.world.entity.Entity breaker, ItemStack tool) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SpawnerBlock.getExpDrop:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)I");
    }

    public SpawnerBlock() {
    }
}
