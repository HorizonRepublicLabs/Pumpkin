package net.minecraft.world.level.biome;

import net.minecraft.core.Holder;

public class BiomeManager {

    public BiomeManager(BiomeManager.NoiseBiomeSource noiseBiomeSource, long seed) {
    }

    public interface NoiseBiomeSource {

        Holder<Biome> getNoiseBiome(final int quartX, final int quartY, final int quartZ);
    }

    public BiomeManager() {
    }
}
