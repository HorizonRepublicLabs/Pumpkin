package net.minecraft.world.level.levelgen.heightproviders;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.WorldGenerationContext;

public abstract class HeightProvider {

    public abstract int sample(final RandomSource random, final WorldGenerationContext heightAccessor);

    public abstract HeightProviderType<?> getType();

    protected HeightProvider() {
    }
}
