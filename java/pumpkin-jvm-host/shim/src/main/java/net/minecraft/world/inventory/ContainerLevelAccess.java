package net.minecraft.world.inventory;

import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface ContainerLevelAccess {

    ContainerLevelAccess NULL = Stubs.of(ContainerLevelAccess.class, "net/minecraft/world/inventory/ContainerLevelAccess");

    static ContainerLevelAccess create(Level level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ContainerLevelAccess.create:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/inventory/ContainerLevelAccess;");
    }

    <T> Optional<T> evaluate(BiFunction<Level, BlockPos, T> action);

    default <T> T evaluate(BiFunction<Level, BlockPos, T> action, T defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ContainerLevelAccess.evaluate:(Ljava/util/function/BiFunction;Ljava/lang/Object;)Ljava/lang/Object;");
    }
}
