package net.minecraft.world.level.levelgen;

import java.util.Optional;

public class WorldOptions {

    public WorldOptions(long seed, boolean generateStructures, boolean generateBonusChest) {
    }

    private WorldOptions(long seed, boolean generateStructures, boolean generateBonusChest, Optional<String> legacyCustomOptions) {
    }

    public WorldOptions() {
    }
}
