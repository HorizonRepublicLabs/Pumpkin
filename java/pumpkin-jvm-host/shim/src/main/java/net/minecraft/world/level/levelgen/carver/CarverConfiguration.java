package net.minecraft.world.level.levelgen.carver;

import net.minecraft.core.HolderSet;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import dev.pumpkin.shim.Unimplemented;

public class CarverConfiguration extends ProbabilityFeatureConfiguration {

    public CarverConfiguration(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, CarverDebugSettings debugSettings, HolderSet<Block> replaceable) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/carver/CarverConfiguration.<init>:(FLnet/minecraft/world/level/levelgen/heightproviders/HeightProvider;Lnet/minecraft/util/valueproviders/FloatProvider;Lnet/minecraft/world/level/levelgen/VerticalAnchor;Lnet/minecraft/world/level/levelgen/carver/CarverDebugSettings;Lnet/minecraft/core/HolderSet;)V");
    }

    protected CarverConfiguration() {
    }
}
