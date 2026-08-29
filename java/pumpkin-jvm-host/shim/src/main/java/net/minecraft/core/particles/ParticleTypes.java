package net.minecraft.core.particles;

import dev.pumpkin.shim.Unimplemented;

public class ParticleTypes {

    public static final SimpleParticleType FLAME = null;

    public static final SimpleParticleType HAPPY_VILLAGER = null;

    public static final ParticleType<ItemParticleOption> ITEM = null;

    public static final SimpleParticleType RAIN = null;

    public static final SimpleParticleType SMOKE = null;

    private static SimpleParticleType register(String name, boolean overrideLimiter) {
        throw Unimplemented.forMember("net/minecraft/core/particles/ParticleTypes.register:(Ljava/lang/String;Z)Lnet/minecraft/core/particles/SimpleParticleType;");
    }

    protected ParticleTypes() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/core/particles/ParticleTypes");
        }
    }
}
