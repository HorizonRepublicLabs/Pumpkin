package net.minecraft.world.level.biome;

import java.util.List;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import dev.pumpkin.shim.Unimplemented;

public class BiomeGenerationSettings {

    public BiomeGenerationSettings(HolderSet<ConfiguredWorldCarver<?>> carvers, List<HolderSet<PlacedFeature>> features) {
        throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeGenerationSettings.<init>:(Lnet/minecraft/core/HolderSet;Ljava/util/List;)V");
    }

    public static class Builder extends BiomeGenerationSettings.PlainBuilder {

        public Builder(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeGenerationSettings$Builder.<init>:(Lnet/minecraft/core/HolderGetter;Lnet/minecraft/core/HolderGetter;)V");
        }

        protected Builder() {
        }
    }

    public static class PlainBuilder {

        public BiomeGenerationSettings build() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeGenerationSettings$PlainBuilder.build:()Lnet/minecraft/world/level/biome/BiomeGenerationSettings;");
        }

        protected PlainBuilder() {
        }
    }

    protected BiomeGenerationSettings() {
    }
}
