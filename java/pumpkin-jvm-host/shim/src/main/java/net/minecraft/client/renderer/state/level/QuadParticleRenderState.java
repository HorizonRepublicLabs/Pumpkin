package net.minecraft.client.renderer.state.level;

import net.minecraft.client.renderer.SubmitNodeCollector;
import dev.pumpkin.shim.Unimplemented;

public class QuadParticleRenderState implements ParticleGroupRenderState {

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/QuadParticleRenderState.clear:()V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/QuadParticleRenderState.isEmpty:()Z");
    }

    public void submit(SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/QuadParticleRenderState.submit:(Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public interface ParticleConsumer {

        void consume(final float x, final float y, final float z, final float xRot, final float yRot, final float zRot, final float wRot, final float scale, final float u0, final float u1, final float v0, final float v1, final int color, final int lightCoords);
    }

    private static class Storage {

        public void clear() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/QuadParticleRenderState$Storage.clear:()V");
        }

        public int count() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/QuadParticleRenderState$Storage.count:()I");
        }

        protected Storage() {
        }
    }

    protected QuadParticleRenderState() {
    }
}
