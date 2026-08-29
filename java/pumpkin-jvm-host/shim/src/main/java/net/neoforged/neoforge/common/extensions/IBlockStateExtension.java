package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.ItemAbility;
import dev.pumpkin.shim.Unimplemented;

public interface IBlockStateExtension {

    default boolean canHarvestBlock(BlockGetter level, BlockPos pos, Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.canHarvestBlock:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;)Z");
    }

    default boolean onDestroyedByPlayer(Level level, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.onDestroyedByPlayer:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;ZLnet/minecraft/world/level/material/FluidState;)Z");
    }

    default int getExpDrop(LevelAccessor level, BlockPos pos, BlockEntity blockEntity, Entity breaker, ItemStack tool) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getExpDrop:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)I");
    }

    default BlockState rotate(LevelAccessor level, BlockPos pos, Rotation direction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.rotate:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    default BlockState getToolModifiedState(UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getToolModifiedState:(Lnet/minecraft/world/item/context/UseOnContext;Lnet/neoforged/neoforge/common/ItemAbility;Z)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    default boolean isEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.isEmpty:()Z");
    }
}
