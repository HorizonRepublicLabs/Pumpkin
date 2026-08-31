package net.minecraft.client.renderer.rendertype;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public class RenderType {

    private final Optional<RenderType> outline = null;

    private RenderType(String name, RenderSetup state) {
    }

    public static RenderType create(String name, RenderSetup state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.create:(Ljava/lang/String;Lnet/minecraft/client/renderer/rendertype/RenderSetup;)Lnet/minecraft/client/renderer/rendertype/RenderType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.toString:()Ljava/lang/String;");
    }

    public boolean hasBlending() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.hasBlending:()Z");
    }

    public VertexFormat format() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.format:()Lcom/mojang/blaze3d/vertex/VertexFormat;");
    }

    public Optional<RenderType> outline() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.outline:()Ljava/util/Optional;");
    }

    public boolean isOutline() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.isOutline:()Z");
    }

    public RenderPipeline pipeline() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderType.pipeline:()Lcom/mojang/blaze3d/pipeline/RenderPipeline;");
    }

    public RenderType() {
    }
}
