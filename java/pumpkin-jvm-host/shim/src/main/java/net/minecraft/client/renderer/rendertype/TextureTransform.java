package net.minecraft.client.renderer.rendertype;

import java.util.function.Supplier;
import org.joml.Matrix4f;
import dev.pumpkin.shim.Unimplemented;

public class TextureTransform {

    public TextureTransform(String name, Supplier<Matrix4f> matrix) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/TextureTransform.<init>:(Ljava/lang/String;Ljava/util/function/Supplier;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/TextureTransform.toString:()Ljava/lang/String;");
    }

    public static final class OffsetTextureTransform extends TextureTransform {

        public OffsetTextureTransform(float uOffset, float vOffset) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/TextureTransform$OffsetTextureTransform.<init>:(FF)V");
        }

        protected OffsetTextureTransform() {
        }
    }

    protected TextureTransform() {
    }
}
