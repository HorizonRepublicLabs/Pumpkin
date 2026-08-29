package net.minecraft.world.level.biome;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import dev.pumpkin.shim.Unimplemented;

public class BiomeGenerationSettings {

    public BiomeGenerationSettings(HolderSet<ConfiguredWorldCarver<?>> carvers, List<HolderSet<PlacedFeature>> features) {
    }

    public static class Builder extends BiomeGenerationSettings.PlainBuilder {

        public Builder(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        }

        public Builder() {
        }
    }

    public static class PlainBuilder {

        public BiomeGenerationSettings.PlainBuilder addFeature(GenerationStep.Decoration step, Holder<PlacedFeature> feature) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeGenerationSettings$PlainBuilder.addFeature:(Lnet/minecraft/world/level/levelgen/GenerationStep$Decoration;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/level/biome/BiomeGenerationSettings$PlainBuilder;");
        }

        public BiomeGenerationSettings.PlainBuilder addFeature(int index, Holder<PlacedFeature> feature) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeGenerationSettings$PlainBuilder.addFeature:(ILnet/minecraft/core/Holder;)Lnet/minecraft/world/level/biome/BiomeGenerationSettings$PlainBuilder;");
        }

        public BiomeGenerationSettings build() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeGenerationSettings$PlainBuilder.build:()Lnet/minecraft/world/level/biome/BiomeGenerationSettings;");
        }

        public PlainBuilder() {
        }
    }

    public BiomeGenerationSettings() {
    }
}
