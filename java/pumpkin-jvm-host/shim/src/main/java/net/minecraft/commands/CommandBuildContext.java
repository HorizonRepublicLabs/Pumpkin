package net.minecraft.commands;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.flag.FeatureFlagSet;

public interface CommandBuildContext extends HolderLookup.Provider {

    FeatureFlagSet enabledFeatures();
}
