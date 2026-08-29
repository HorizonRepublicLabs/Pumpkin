package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import dev.pumpkin.shim.Unimplemented;

public interface ILevelExtension {

    public double getMaxEntityRadius();

    public double increaseMaxEntityRadius(double value);

    default <T, C extends Object> T getCapability(BlockCapability<T, C> cap, BlockPos pos, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    default <T, C extends Object> T getCapability(BlockCapability<T, C> cap, BlockPos pos, BlockState state, BlockEntity blockEntity, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    default <T> T getCapability(BlockCapability<T, Void> cap, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;)Ljava/lang/Object;");
    }

    default <T> T getCapability(BlockCapability<T, Void> cap, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;)Ljava/lang/Object;");
    }

    default Component getDescription() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getDescription:()Lnet/minecraft/network/chat/Component;");
    }
}
