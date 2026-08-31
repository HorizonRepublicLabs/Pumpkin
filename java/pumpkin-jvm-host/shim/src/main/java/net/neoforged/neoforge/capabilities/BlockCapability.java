package net.neoforged.neoforge.capabilities;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public final class BlockCapability<T, C extends Object> extends BaseCapability<T, C> {

    // Pumpkin divergence: real bodies -- interned by name, as NeoForge's registry does,
    // so creating the same capability twice hands back the same token and identity
    // comparisons hold. Queries against these tokens are a later subsystem; creating
    // and carrying one is pure identity.
    private static final java.util.concurrent.ConcurrentHashMap<Identifier, BlockCapability<?, ?>> PUMPKIN_INTERNED =
            new java.util.concurrent.ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T, C extends Object> BlockCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        return (BlockCapability<T, C>) PUMPKIN_INTERNED.computeIfAbsent(name,
                key -> new BlockCapability<>(key, typeClass, contextClass));
    }

    public static <T> BlockCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {
        return create(name, typeClass, Void.class);
    }

    public static <T> BlockCapability<T, Direction> createSided(Identifier name, Class<T> typeClass) {
        return create(name, typeClass, Direction.class);
    }

    public static synchronized List<BlockCapability<?, ?>> getAll() {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.getAll:()Ljava/util/List;");
    }

    private BlockCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {
    }

    public T getCapability(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.getCapability:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public BlockCapability() {
    }
}
