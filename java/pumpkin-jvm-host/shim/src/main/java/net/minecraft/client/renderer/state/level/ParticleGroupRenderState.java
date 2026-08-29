package net.minecraft.client.renderer.state.level;

import net.minecraft.client.renderer.SubmitNodeCollector;
import dev.pumpkin.shim.Unimplemented;

public interface ParticleGroupRenderState {

    void submit(SubmitNodeCollector submitNodeCollector, final CameraRenderState camera);

    default void clear() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/ParticleGroupRenderState.clear:()V");
    }
}
