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

    public static <T, C extends Object> BlockCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.create:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/BlockCapability;");
    }

    public static <T> BlockCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.createVoid:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/BlockCapability;");
    }

    public static <T> BlockCapability<T, Direction> createSided(Identifier name, Class<T> typeClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.createSided:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/BlockCapability;");
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
