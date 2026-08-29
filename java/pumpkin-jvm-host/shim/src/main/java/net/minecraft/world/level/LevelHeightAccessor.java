package net.minecraft.world.level;

import dev.pumpkin.shim.Unimplemented;

public interface LevelHeightAccessor {

    int getHeight();

    int getMinY();

    static LevelHeightAccessor create(int minY, int height) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelHeightAccessor.create:(II)Lnet/minecraft/world/level/LevelHeightAccessor;");
    }
}
