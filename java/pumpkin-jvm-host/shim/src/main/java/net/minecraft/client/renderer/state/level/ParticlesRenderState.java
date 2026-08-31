package net.minecraft.client.renderer.state.level;

import net.minecraft.client.renderer.SubmitNodeCollector;
import dev.pumpkin.shim.Unimplemented;

public class ParticlesRenderState {

    public void reset() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/ParticlesRenderState.reset:()V");
    }

    public void add(ParticleGroupRenderState state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/ParticlesRenderState.add:(Lnet/minecraft/client/renderer/state/level/ParticleGroupRenderState;)V");
    }

    public void submit(SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/ParticlesRenderState.submit:(Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public ParticlesRenderState() {
    }
}
