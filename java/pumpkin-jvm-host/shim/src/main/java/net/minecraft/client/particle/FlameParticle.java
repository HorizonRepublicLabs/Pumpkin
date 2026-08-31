package net.minecraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class FlameParticle extends RisingParticle {

    public FlameParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, TextureAtlasSprite sprite) {
    }

    public SingleQuadParticle.Layer getLayer() {
        throw Unimplemented.forMember("net/minecraft/client/particle/FlameParticle.getLayer:()Lnet/minecraft/client/particle/SingleQuadParticle$Layer;");
    }

    public void move(double xa, double ya, double za) {
        throw Unimplemented.forMember("net/minecraft/client/particle/FlameParticle.move:(DDD)V");
    }

    public float getQuadSize(float a) {
        throw Unimplemented.forMember("net/minecraft/client/particle/FlameParticle.getQuadSize:(F)F");
    }

    public int getLightCoords(float a) {
        throw Unimplemented.forMember("net/minecraft/client/particle/FlameParticle.getLightCoords:(F)I");
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        public Provider(SpriteSet sprite) {
        }

        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/client/particle/FlameParticle$Provider.createParticle:(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;");
        }

        public Provider() {
        }
    }

    public static class SmallFlameProvider implements ParticleProvider<SimpleParticleType> {

        public SmallFlameProvider(SpriteSet sprite) {
        }

        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/client/particle/FlameParticle$SmallFlameProvider.createParticle:(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;");
        }

        public SmallFlameProvider() {
        }
    }

    public FlameParticle() {
    }
}
