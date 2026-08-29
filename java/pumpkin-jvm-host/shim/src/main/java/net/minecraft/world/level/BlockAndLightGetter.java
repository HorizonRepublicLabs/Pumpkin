package net.minecraft.world.level;

import net.minecraft.world.level.lighting.LevelLightEngine;

public interface BlockAndLightGetter extends BlockGetter {

    LevelLightEngine getLightEngine();
}
