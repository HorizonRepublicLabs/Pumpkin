package net.minecraft.client.particle;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.state.level.ParticlesRenderState;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class ParticleEngine {

    public ParticleEngine(ClientLevel level, ParticleResources resourceManager) {
    }

    public void add(Particle p) {
        throw Unimplemented.forMember("net/minecraft/client/particle/ParticleEngine.add:(Lnet/minecraft/client/particle/Particle;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/particle/ParticleEngine.tick:()V");
    }

    public void extract(ParticlesRenderState particlesRenderState, Frustum frustum, Camera camera, float partialTickTime) {
        throw Unimplemented.forMember("net/minecraft/client/particle/ParticleEngine.extract:(Lnet/minecraft/client/renderer/state/level/ParticlesRenderState;Lnet/minecraft/client/renderer/culling/Frustum;Lnet/minecraft/client/Camera;F)V");
    }

    public RandomSource getRandom() {
        throw Unimplemented.forMember("net/minecraft/client/particle/ParticleEngine.getRandom:()Lnet/minecraft/util/RandomSource;");
    }

    public ParticleEngine() {
    }
}
