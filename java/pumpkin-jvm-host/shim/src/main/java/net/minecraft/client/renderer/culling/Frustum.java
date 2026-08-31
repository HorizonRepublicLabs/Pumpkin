package net.minecraft.client.renderer.culling;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public class Frustum {

    public Frustum(Matrix4fc modelView, Matrix4f projection) {
    }

    public Frustum(Frustum frustum) {
    }

    public void set(Frustum frustum) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/culling/Frustum.set:(Lnet/minecraft/client/renderer/culling/Frustum;)V");
    }

    public Frustum() {
    }
}
