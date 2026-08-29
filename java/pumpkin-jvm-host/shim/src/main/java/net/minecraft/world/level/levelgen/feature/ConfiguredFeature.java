package net.minecraft.world.level.levelgen.feature;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import dev.pumpkin.shim.Unimplemented;

public record ConfiguredFeature<FC extends FeatureConfiguration, F extends Feature<FC>>(F feature, FC config) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/ConfiguredFeature.toString:()Ljava/lang/String;");
    }
}
