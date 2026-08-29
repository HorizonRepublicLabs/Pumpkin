package net.minecraft.world.level.levelgen;

import dev.pumpkin.shim.Unimplemented;

public record NoiseSettings(int minY, int height, int noiseSizeHorizontal, int noiseSizeVertical) {

    public static NoiseSettings create(int minY, int height, int noiseSizeHorizontal, int noiseSizeVertical) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseSettings.create:(IIII)Lnet/minecraft/world/level/levelgen/NoiseSettings;");
    }
}
