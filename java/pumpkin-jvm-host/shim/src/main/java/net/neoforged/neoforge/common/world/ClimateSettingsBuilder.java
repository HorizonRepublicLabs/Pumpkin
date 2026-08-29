package net.neoforged.neoforge.common.world;

import net.minecraft.world.level.biome.Biome.ClimateSettings;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;
import dev.pumpkin.shim.Unimplemented;

public class ClimateSettingsBuilder {

    public static ClimateSettingsBuilder copyOf(ClimateSettings settings) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ClimateSettingsBuilder.copyOf:(Lnet/minecraft/world/level/biome/Biome$ClimateSettings;)Lnet/neoforged/neoforge/common/world/ClimateSettingsBuilder;");
    }

    public static ClimateSettingsBuilder create(boolean hasPrecipitation, float temperature, TemperatureModifier temperatureModifier, float downfall) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ClimateSettingsBuilder.create:(ZFLnet/minecraft/world/level/biome/Biome$TemperatureModifier;F)Lnet/neoforged/neoforge/common/world/ClimateSettingsBuilder;");
    }

    private ClimateSettingsBuilder(boolean hasPrecipitation, float temperature, TemperatureModifier temperatureModifier, float downfall) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ClimateSettingsBuilder.<init>:(ZFLnet/minecraft/world/level/biome/Biome$TemperatureModifier;F)V");
    }

    public ClimateSettings build() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ClimateSettingsBuilder.build:()Lnet/minecraft/world/level/biome/Biome$ClimateSettings;");
    }

    public ClimateSettingsBuilder() {
    }
}
