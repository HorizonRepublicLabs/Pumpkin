package net.minecraft.world.level.biome;

import com.mojang.serialization.MapCodec;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public abstract class BiomeSource implements BiomeResolver {

    protected BiomeSource() {
        throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeSource.<init>:()V");
    }

    protected abstract MapCodec<? extends BiomeSource> codec();

    protected abstract Stream<Holder<Biome>> collectPossibleBiomes();

    public abstract Holder<Biome> getNoiseBiome(final int quartX, final int quartY, final int quartZ, final Climate.Sampler sampler);
}
