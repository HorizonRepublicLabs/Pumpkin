package net.minecraft.core.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public class SimpleParticleType extends ParticleType<SimpleParticleType> implements ParticleOptions {

    public SimpleParticleType(boolean overrideLimiter) {
    }

    public SimpleParticleType getType() {
        throw Unimplemented.forMember("net/minecraft/core/particles/SimpleParticleType.getType:()Lnet/minecraft/core/particles/SimpleParticleType;");
    }

    public MapCodec<SimpleParticleType> codec() {
        throw Unimplemented.forMember("net/minecraft/core/particles/SimpleParticleType.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public StreamCodec<RegistryFriendlyByteBuf, SimpleParticleType> streamCodec() {
        throw Unimplemented.forMember("net/minecraft/core/particles/SimpleParticleType.streamCodec:()Lnet/minecraft/network/codec/StreamCodec;");
    }

    public SimpleParticleType() {
    }
}
