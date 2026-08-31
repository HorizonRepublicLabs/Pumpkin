package net.minecraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class BubbleParticle extends SingleQuadParticle {

    public BubbleParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, TextureAtlasSprite sprite) {
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/particle/BubbleParticle.tick:()V");
    }

    public SingleQuadParticle.Layer getLayer() {
        throw Unimplemented.forMember("net/minecraft/client/particle/BubbleParticle.getLayer:()Lnet/minecraft/client/particle/SingleQuadParticle$Layer;");
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        public Provider(SpriteSet sprite) {
        }

        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/client/particle/BubbleParticle$Provider.createParticle:(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;");
        }

        public Provider() {
        }
    }

    public BubbleParticle() {
    }
}
