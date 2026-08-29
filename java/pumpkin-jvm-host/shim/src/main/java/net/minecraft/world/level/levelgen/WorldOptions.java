package net.minecraft.world.level.levelgen;

import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public class WorldOptions {

    public WorldOptions(long seed, boolean generateStructures, boolean generateBonusChest) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldOptions.<init>:(JZZ)V");
    }

    private WorldOptions(long seed, boolean generateStructures, boolean generateBonusChest, Optional<String> legacyCustomOptions) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldOptions.<init>:(JZZLjava/util/Optional;)V");
    }

    protected WorldOptions() {
    }
}
