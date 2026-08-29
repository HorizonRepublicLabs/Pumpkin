package net.minecraft.client.renderer.rendertype;

import java.util.function.Consumer;
import org.joml.Matrix4f;
import dev.pumpkin.shim.Unimplemented;

public class LayeringTransform {

    public LayeringTransform(String name, Consumer<Matrix4f> modifier) {
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/LayeringTransform.toString:()Ljava/lang/String;");
    }

    public LayeringTransform() {
    }
}
