package net.neoforged.neoforge.capabilities;

import java.util.function.BooleanSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import dev.pumpkin.shim.Unimplemented;

public final class BlockCapabilityCache<T, C extends Object> {

    public static <T, C extends Object> BlockCapabilityCache<T, C> create(BlockCapability<T, C> capability, ServerLevel level, BlockPos pos, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapabilityCache.create:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Ljava/lang/Object;)Lnet/neoforged/neoforge/capabilities/BlockCapabilityCache;");
    }

    public static <T, C extends Object> BlockCapabilityCache<T, C> create(BlockCapability<T, C> capability, ServerLevel level, BlockPos pos, C context, BooleanSupplier isValid, Runnable invalidationListener) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapabilityCache.create:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Ljava/lang/Object;Ljava/util/function/BooleanSupplier;Ljava/lang/Runnable;)Lnet/neoforged/neoforge/capabilities/BlockCapabilityCache;");
    }

    private BlockCapabilityCache(BlockCapability<T, C> capability, ServerLevel level, BlockPos pos, C context, BooleanSupplier isValid, Runnable invalidationListener) {
    }

    public ServerLevel level() {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapabilityCache.level:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public T getCapability() {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapabilityCache.getCapability:()Ljava/lang/Object;");
    }

    public BlockCapabilityCache() {
    }
}
