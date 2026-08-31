package net.minecraft.core.particles;

import org.joml.Vector3f;
import dev.pumpkin.shim.Unimplemented;

public class DustParticleOptions extends ScalableParticleOptionsBase {

    public static final DustParticleOptions REDSTONE = null;

    public DustParticleOptions(int color, float scale) {
    }

    public ParticleType<DustParticleOptions> getType() {
        throw Unimplemented.forMember("net/minecraft/core/particles/DustParticleOptions.getType:()Lnet/minecraft/core/particles/ParticleType;");
    }

    public Vector3f getColor() {
        throw Unimplemented.forMember("net/minecraft/core/particles/DustParticleOptions.getColor:()Lorg/joml/Vector3f;");
    }

    public DustParticleOptions() {
    }
}
