package net.minecraft.client.renderer.rendertype;

import com.mojang.blaze3d.vertex.VertexFormat;
import dev.pumpkin.shim.Unimplemented;

public class RenderType {

    private RenderType(String name, RenderSetup state) {
    }

    public static RenderType create(String name, RenderSetup state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.create:(Ljava/lang/String;Lnet/minecraft/client/renderer/rendertype/RenderSetup;)Lnet/minecraft/client/renderer/rendertype/RenderType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.toString:()Ljava/lang/String;");
    }

    public VertexFormat format() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.format:()Lcom/mojang/blaze3d/vertex/VertexFormat;");
    }

    public RenderType() {
    }
}
