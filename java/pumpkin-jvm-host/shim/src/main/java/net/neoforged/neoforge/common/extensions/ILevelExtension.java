package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.IBlockCapabilityProvider;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import dev.pumpkin.shim.Unimplemented;

public interface ILevelExtension {

    public double getMaxEntityRadius();

    public double increaseMaxEntityRadius(double value);

    // Pumpkin divergence: consult what RegisterCapabilitiesEvent collected; null --
    // the NeoForge contract for "no provider" -- when nothing registered.
    default <T, C extends Object> T getCapability(BlockCapability<T, C> cap, BlockPos pos, C context) {
        BlockEntity blockEntity =
                dev.pumpkin.bridge.PumpkinBlockEntities.get(pos.getX(), pos.getY(), pos.getZ());        return getCapability(cap, pos, null, blockEntity, context);
    }

    @SuppressWarnings("unchecked")
    default <T, C extends Object> T getCapability(BlockCapability<T, C> cap, BlockPos pos, BlockState state, BlockEntity blockEntity, C context) {
        if (blockEntity != null) {
            ICapabilityProvider<BlockEntity, C, T> provider =
                    (ICapabilityProvider<BlockEntity, C, T>) dev.pumpkin.bridge.PumpkinCapabilities.get(
                            dev.pumpkin.bridge.PumpkinCapabilities.BLOCK_ENTITY, cap,
                            blockEntity.getType());
            if (provider != null) {
                return provider.getCapability(blockEntity, context);
            }
        }        BlockState resolved = state;
        if (resolved == null && blockEntity != null) {
            resolved = blockEntity.getBlockState();
        }
        if (resolved != null) {
            IBlockCapabilityProvider<T, C> provider =
                    (IBlockCapabilityProvider<T, C>) dev.pumpkin.bridge.PumpkinCapabilities.get(
                            dev.pumpkin.bridge.PumpkinCapabilities.BLOCK, cap, resolved.getBlock());
            if (provider != null) {
                return provider.getCapability((net.minecraft.world.level.Level) this, pos, resolved,
                        blockEntity, context);
            }
        }
        return null;
    }

    default <T> T getCapability(BlockCapability<T, Void> cap, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;)Ljava/lang/Object;");
    }

    default <T> T getCapability(BlockCapability<T, Void> cap, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;)Ljava/lang/Object;");
    }

    default void invalidateCapabilities(BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.invalidateCapabilities:(Lnet/minecraft/core/BlockPos;)V");
    }

    default void invalidateCapabilities(ChunkPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.invalidateCapabilities:(Lnet/minecraft/world/level/ChunkPos;)V");
    }

    default Component getDescription() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getDescription:()Lnet/minecraft/network/chat/Component;");
    }
}
