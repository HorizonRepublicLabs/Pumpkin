package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.ItemAbility;
import dev.pumpkin.shim.Unimplemented;

public interface IBlockStateExtension {

    default float getFriction(LevelReader level, BlockPos pos, Entity entity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getFriction:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)F");
    }

    // Pumpkin divergence: vanilla's derivation -- the position-aware overload falls
    // back to the state's own emission.
    default int getLightEmission(BlockGetter level, BlockPos pos) {
        return ((net.minecraft.world.level.block.state.BlockState) this).getLightEmission();
    }

    default boolean canHarvestBlock(BlockGetter level, BlockPos pos, Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.canHarvestBlock:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;)Z");
    }

    default boolean onDestroyedByPlayer(Level level, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.onDestroyedByPlayer:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;ZLnet/minecraft/world/level/material/FluidState;)Z");
    }

    default float getExplosionResistance(BlockGetter level, BlockPos pos, Explosion explosion) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getExplosionResistance:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/Explosion;)F");
    }

    default ItemStack getCloneItemStack(BlockPos pos, LevelReader level, boolean includeData, Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getCloneItemStack:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/LevelReader;ZLnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/item/ItemStack;");
    }

    default int getExpDrop(LevelAccessor level, BlockPos pos, BlockEntity blockEntity, Entity breaker, ItemStack tool) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getExpDrop:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)I");
    }

    default BlockState rotate(LevelAccessor level, BlockPos pos, Rotation direction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.rotate:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    default void onNeighborChange(LevelReader level, BlockPos pos, BlockPos neighbor) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.onNeighborChange:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)V");
    }

    default boolean getWeakChanges(LevelReader level, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getWeakChanges:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
    }

    default SoundType getSoundType(LevelReader level, BlockPos pos, Entity entity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getSoundType:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/level/block/SoundType;");
    }

    default boolean isFlammable(BlockGetter level, BlockPos pos, Direction face) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.isFlammable:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z");
    }

    default boolean onCaughtFire(Level level, BlockPos pos, Direction face, LivingEntity igniter) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.onCaughtFire:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    default BlockState getToolModifiedState(UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.getToolModifiedState:(Lnet/minecraft/world/item/context/UseOnContext;Lnet/neoforged/neoforge/common/ItemAbility;Z)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    default boolean isEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.isEmpty:()Z");
    }
}
