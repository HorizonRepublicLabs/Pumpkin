package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathType;
import dev.pumpkin.shim.Unimplemented;

public interface IBlockExtension {

    default int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.getLightEmission:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)I");
    }

    default boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.onDestroyedByPlayer:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;ZLnet/minecraft/world/level/material/FluidState;)Z");
    }

    default float getExplosionResistance(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.getExplosionResistance:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/Explosion;)F");
    }

    default ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.getCloneItemStack:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;ZLnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/item/ItemStack;");
    }

    default boolean shouldCheckWeakPower(BlockState state, SignalGetter level, BlockPos pos, Direction side) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.shouldCheckWeakPower:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/SignalGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z");
    }

    default PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, Mob mob) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.getBlockPathType:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Mob;)Lnet/minecraft/world/level/pathfinder/PathType;");
    }

    default void onBlockExploded(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.onBlockExploded:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/Explosion;)V");
    }

    default boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.canConnectRedstone:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z");
    }

    default PushReaction getPistonPushReaction(BlockState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockExtension.getPistonPushReaction:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/material/PushReaction;");
    }
}
