package net.minecraft.world.level.biome;

import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class BiomeManager {

    public BiomeManager(BiomeManager.NoiseBiomeSource noiseBiomeSource, long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeManager.<init>:(Lnet/minecraft/world/level/biome/BiomeManager$NoiseBiomeSource;J)V");
    }

    public interface NoiseBiomeSource {

        Holder<Biome> getNoiseBiome(final int quartX, final int quartY, final int quartZ);
    }

    public BiomeManager() {
    }
}
