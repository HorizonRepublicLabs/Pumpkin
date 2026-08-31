package net.minecraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseAshSmokeParticle extends SingleQuadParticle {

    public BaseAshSmokeParticle(ClientLevel level, double x, double y, double z, float dirX, float dirY, float dirZ, double xa, double ya, double za, float scale, SpriteSet sprites, float colorRandom, int maxLifetime, float gravity, boolean hasPhysics) {
    }

    public SingleQuadParticle.Layer getLayer() {
        throw Unimplemented.forMember("net/minecraft/client/particle/BaseAshSmokeParticle.getLayer:()Lnet/minecraft/client/particle/SingleQuadParticle$Layer;");
    }

    public float getQuadSize(float a) {
        throw Unimplemented.forMember("net/minecraft/client/particle/BaseAshSmokeParticle.getQuadSize:(F)F");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/particle/BaseAshSmokeParticle.tick:()V");
    }

    public BaseAshSmokeParticle() {
    }
}
