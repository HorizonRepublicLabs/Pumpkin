package net.minecraft.world.level;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public interface WorldGenLevel extends ServerLevelAccessor {

    long getSeed();

    default boolean ensureCanWrite(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/WorldGenLevel.ensureCanWrite:(Lnet/minecraft/core/BlockPos;)Z");
    }

    default void setCurrentlyGenerating(Supplier<String> currentlyGenerating) {
        throw Unimplemented.forMember("net/minecraft/world/level/WorldGenLevel.setCurrentlyGenerating:(Ljava/util/function/Supplier;)V");
    }
}
