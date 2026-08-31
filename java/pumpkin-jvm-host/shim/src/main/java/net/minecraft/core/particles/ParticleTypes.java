package net.minecraft.core.particles;

import dev.pumpkin.shim.Unimplemented;

public class ParticleTypes {

    public static final SimpleParticleType EXPLOSION_EMITTER = null;

    public static final SimpleParticleType FLAME = null;

    public static final SimpleParticleType HAPPY_VILLAGER = null;

    public static final ParticleType<ItemParticleOption> ITEM = null;

    public static final SimpleParticleType LAVA = null;

    public static final SimpleParticleType PORTAL = null;

    public static final SimpleParticleType RAIN = null;

    public static final SimpleParticleType SMOKE = null;

    private static SimpleParticleType register(String name, boolean overrideLimiter) {
        throw Unimplemented.forMember("net/minecraft/core/particles/ParticleTypes.register:(Ljava/lang/String;Z)Lnet/minecraft/core/particles/SimpleParticleType;");
    }

    public ParticleTypes() {
    }

    // Pumpkin divergence: the throwing clinit is defused. Every field stays null, and the
    // one consumer this host serves -- a machine spawning decoration -- hands the value
    // straight to the bridge level, which accepts and drops particles. A machine must not
    // die over sparkles.
    static {
    }
}
