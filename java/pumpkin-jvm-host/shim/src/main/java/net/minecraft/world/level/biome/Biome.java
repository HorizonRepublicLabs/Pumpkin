package net.minecraft.world.level.biome;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import dev.pumpkin.shim.Unimplemented;

public final class Biome {

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    public static final Codec<HolderSet<Biome>> LIST_CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/biome/Biome.LIST_CODEC");

    private Biome(Biome.ClimateSettings climateSettings, EnvironmentAttributeMap attributes, BiomeSpecialEffects specialEffects, BiomeGenerationSettings generationSettings, MobSpawnSettings mobSettings) {
        throw Unimplemented.forMember("net/minecraft/world/level/biome/Biome.<init>:(Lnet/minecraft/world/level/biome/Biome$ClimateSettings;Lnet/minecraft/world/attribute/EnvironmentAttributeMap;Lnet/minecraft/world/level/biome/BiomeSpecialEffects;Lnet/minecraft/world/level/biome/BiomeGenerationSettings;Lnet/minecraft/world/level/biome/MobSpawnSettings;)V");
    }

    public static class BiomeBuilder {

        public Biome build() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Biome$BiomeBuilder.build:()Lnet/minecraft/world/level/biome/Biome;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Biome$BiomeBuilder.toString:()Ljava/lang/String;");
        }

        public BiomeBuilder() {
        }
    }

    public record ClimateSettings(boolean hasPrecipitation, float temperature, Biome.TemperatureModifier temperatureModifier, float downfall) {
    }

    public enum Precipitation implements StringRepresentable {

        NONE, RAIN, SNOW;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Biome$Precipitation.getSerializedName:()Ljava/lang/String;");
        }
    }

    public enum TemperatureModifier implements StringRepresentable {

        NONE {

            public float modifyTemperature(BlockPos pos, float baseTemperature) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Biome$TemperatureModifier$NONE.modifyTemperature:()");
            }
        }
        , FROZEN {

            public float modifyTemperature(BlockPos pos, float baseTemperature) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Biome$TemperatureModifier$FROZEN.modifyTemperature:()");
            }
        }
        ;

        public abstract float modifyTemperature(final BlockPos pos, final float baseTemperature);

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Biome$TemperatureModifier.getName:()Ljava/lang/String;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Biome$TemperatureModifier.getSerializedName:()Ljava/lang/String;");
        }
    }

    public Biome() {
    }
}
