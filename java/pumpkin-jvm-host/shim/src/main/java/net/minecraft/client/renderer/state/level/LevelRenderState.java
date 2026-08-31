package net.minecraft.client.renderer.state.level;

import dev.pumpkin.shim.Unimplemented;

public class LevelRenderState extends net.neoforged.neoforge.client.renderstate.BaseRenderState {

    public CameraRenderState cameraRenderState;

    public long gameTime;

    public void reset() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/level/LevelRenderState.reset:()V");
    }

    public LevelRenderState() {
    }
}
