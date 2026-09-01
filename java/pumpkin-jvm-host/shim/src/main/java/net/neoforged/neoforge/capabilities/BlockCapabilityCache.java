package net.neoforged.neoforge.capabilities;

import java.util.function.BooleanSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import dev.pumpkin.shim.Unimplemented;

public final class BlockCapabilityCache<T, C extends Object> {

    // Pumpkin divergence: NeoForge's cache is a lookup optimisation over chunk
    // invalidation Pumpkin does not model; the honest minimal form re-asks the level
    // on every query, which is always correct and merely slower.
    private BlockCapability<T, C> pumpkinCapability;
    private ServerLevel pumpkinLevel;
    private BlockPos pumpkinPos;
    private C pumpkinContext;

    public static <T, C extends Object> BlockCapabilityCache<T, C> create(BlockCapability<T, C> capability, ServerLevel level, BlockPos pos, C context) {
        return create(capability, level, pos, context, () -> true, () -> { });
    }

    public static <T, C extends Object> BlockCapabilityCache<T, C> create(BlockCapability<T, C> capability, ServerLevel level, BlockPos pos, C context, BooleanSupplier isValid, Runnable invalidationListener) {
        BlockCapabilityCache<T, C> cache =
                new BlockCapabilityCache<>(capability, level, pos, context, isValid, invalidationListener);
        cache.pumpkinCapability = capability;
        cache.pumpkinLevel = level;
        cache.pumpkinPos = pos;
        cache.pumpkinContext = context;
        return cache;
    }

    private BlockCapabilityCache(BlockCapability<T, C> capability, ServerLevel level, BlockPos pos, C context, BooleanSupplier isValid, Runnable invalidationListener) {
    }

    public ServerLevel level() {
        return pumpkinLevel;
    }

    public T getCapability() {
        return pumpkinLevel.getCapability(pumpkinCapability, pumpkinPos, pumpkinContext);
    }

    public BlockCapabilityCache() {
    }
}
