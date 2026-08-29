package net.minecraft.world.level;

import net.minecraft.world.flag.FeatureFlagSet;

public record WorldDataConfiguration(DataPackConfig dataPacks, FeatureFlagSet enabledFeatures) {
}
