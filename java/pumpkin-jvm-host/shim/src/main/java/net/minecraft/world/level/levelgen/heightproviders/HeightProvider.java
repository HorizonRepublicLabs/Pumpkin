package net.minecraft.world.level.levelgen.heightproviders;

import com.mojang.serialization.Codec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.WorldGenerationContext;

public abstract class HeightProvider {

    public static final Codec<HeightProvider> CODEC = null;

    public abstract int sample(final RandomSource random, final WorldGenerationContext heightAccessor);

    public abstract HeightProviderType<?> getType();

    public HeightProvider() {
    }
}
