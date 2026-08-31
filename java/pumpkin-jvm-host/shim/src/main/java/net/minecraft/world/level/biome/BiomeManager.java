package net.minecraft.world.level.biome;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class BiomeManager {

    public BiomeManager(BiomeManager.NoiseBiomeSource noiseBiomeSource, long seed) {
    }

    public Holder<Biome> getBiome(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeManager.getBiome:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/Holder;");
    }

    public interface NoiseBiomeSource {

        Holder<Biome> getNoiseBiome(final int quartX, final int quartY, final int quartZ);
    }

    public BiomeManager() {
    }
}
