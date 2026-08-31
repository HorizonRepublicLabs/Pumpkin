package net.minecraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;

public interface ParticleProvider<T extends ParticleOptions> {

    Particle createParticle(T options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random);

    interface Sprite<T extends ParticleOptions> {

        SingleQuadParticle createParticle(T options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random);
    }
}
