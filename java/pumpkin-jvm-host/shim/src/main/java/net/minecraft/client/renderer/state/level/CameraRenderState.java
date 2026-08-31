package net.minecraft.client.renderer.state.level;

import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class CameraRenderState {

    public Vec3 pos;

    public float yRot;

    public Quaternionf orientation;

    public CameraRenderState() {
    }
}
