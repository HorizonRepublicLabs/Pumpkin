package net.minecraft.client.renderer.rendertype;

import java.util.function.Supplier;
import org.joml.Matrix4f;
import dev.pumpkin.shim.Unimplemented;

public class TextureTransform {

    public static final TextureTransform ARMOR_ENTITY_GLINT_TEXTURING = null;

    public TextureTransform(String name, Supplier<Matrix4f> matrix) {
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/TextureTransform.toString:()Ljava/lang/String;");
    }

    public static final class OffsetTextureTransform extends TextureTransform {

        public OffsetTextureTransform(float uOffset, float vOffset) {
        }

        public OffsetTextureTransform() {
        }
    }

    public TextureTransform() {
    }
}
