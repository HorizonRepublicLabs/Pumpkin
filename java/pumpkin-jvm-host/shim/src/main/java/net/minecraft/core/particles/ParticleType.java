package net.minecraft.core.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public abstract class ParticleType<T extends ParticleOptions> {

    protected ParticleType(boolean overrideLimiter) {
        throw Unimplemented.forMember("net/minecraft/core/particles/ParticleType.<init>:(Z)V");
    }

    public abstract MapCodec<T> codec();

    public abstract StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec();

    public ParticleType() {
    }
}
