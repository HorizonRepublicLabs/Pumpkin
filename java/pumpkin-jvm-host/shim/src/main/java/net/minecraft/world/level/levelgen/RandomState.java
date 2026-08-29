package net.minecraft.world.level.levelgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import dev.pumpkin.shim.Unimplemented;

public final class RandomState {

    public static RandomState create(HolderGetter.Provider holders, ResourceKey<NoiseGeneratorSettings> noiseSettings, long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/RandomState.create:(Lnet/minecraft/core/HolderGetter$Provider;Lnet/minecraft/resources/ResourceKey;J)Lnet/minecraft/world/level/levelgen/RandomState;");
    }

    public static RandomState create(NoiseGeneratorSettings settings, HolderGetter<NormalNoise.NoiseParameters> noises, long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/RandomState.create:(Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;Lnet/minecraft/core/HolderGetter;J)Lnet/minecraft/world/level/levelgen/RandomState;");
    }

    private RandomState(NoiseGeneratorSettings settings, HolderGetter<NormalNoise.NoiseParameters> noises, long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/RandomState.<init>:(Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;Lnet/minecraft/core/HolderGetter;J)V");
    }

    public RandomState() {
    }
}
