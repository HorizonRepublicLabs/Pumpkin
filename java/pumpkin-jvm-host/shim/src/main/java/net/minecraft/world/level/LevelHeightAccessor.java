package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public interface LevelHeightAccessor {

    int getHeight();

    int getMinY();

    default int getMaxY() {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelHeightAccessor.getMaxY:()I");
    }

    default int getMinSectionY() {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelHeightAccessor.getMinSectionY:()I");
    }

    default boolean isOutsideBuildHeight(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelHeightAccessor.isOutsideBuildHeight:(Lnet/minecraft/core/BlockPos;)Z");
    }

    default boolean isOutsideBuildHeight(int blockY) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelHeightAccessor.isOutsideBuildHeight:(I)Z");
    }

    static LevelHeightAccessor create(int minY, int height) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelHeightAccessor.create:(II)Lnet/minecraft/world/level/LevelHeightAccessor;");
    }
}
