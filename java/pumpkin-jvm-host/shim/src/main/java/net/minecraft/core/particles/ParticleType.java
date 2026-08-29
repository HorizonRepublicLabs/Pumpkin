package net.minecraft.core.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public abstract class ParticleType<T extends ParticleOptions> {

    protected ParticleType(boolean overrideLimiter) {
    }

    public abstract MapCodec<T> codec();

    public abstract StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec();

    public ParticleType() {
    }
}
