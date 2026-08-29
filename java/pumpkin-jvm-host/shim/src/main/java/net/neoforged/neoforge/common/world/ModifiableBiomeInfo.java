package net.neoforged.neoforge.common.world;

import net.minecraft.world.level.biome.Biome.ClimateSettings;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import dev.pumpkin.shim.Unimplemented;

public class ModifiableBiomeInfo {

    public ModifiableBiomeInfo(final BiomeInfo originalBiomeInfo) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableBiomeInfo.<init>:(Lnet/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo;)V");
    }

    public BiomeInfo get() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableBiomeInfo.get:()Lnet/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo;");
    }

    public record BiomeInfo(ClimateSettings climateSettings, BiomeSpecialEffects effects, BiomeGenerationSettings generationSettings, MobSpawnSettings mobSpawnSettings) {

        public static class Builder {

            public static Builder copyOf(final BiomeInfo original) {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo$Builder.copyOf:(Lnet/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo;)Lnet/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo$Builder;");
            }

            private Builder(final ClimateSettingsBuilder climateSettings, final BiomeSpecialEffectsBuilder effects, final BiomeGenerationSettingsBuilder generationSettings, final MobSpawnSettingsBuilder mobSpawnSettings) {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo$Builder.<init>:(Lnet/neoforged/neoforge/common/world/ClimateSettingsBuilder;Lnet/neoforged/neoforge/common/world/BiomeSpecialEffectsBuilder;Lnet/neoforged/neoforge/common/world/BiomeGenerationSettingsBuilder;Lnet/neoforged/neoforge/common/world/MobSpawnSettingsBuilder;)V");
            }

            public BiomeInfo build() {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo$Builder.build:()Lnet/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo;");
            }

            public BiomeGenerationSettingsBuilder getGenerationSettings() {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableBiomeInfo$BiomeInfo$Builder.getGenerationSettings:()Lnet/neoforged/neoforge/common/world/BiomeGenerationSettingsBuilder;");
            }

            protected Builder() {
            }
        }
    }

    protected ModifiableBiomeInfo() {
    }
}
