package net.minecraft.client.renderer;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.pumpkin.shim.Unimplemented;

public class RenderPipelines {

    public static final RenderPipeline.Snippet MATRICES_FOG_SNIPPET = null;

    public static final RenderPipeline.Snippet MATRICES_FOG_LIGHT_DIR_SNIPPET = null;

    public static final RenderPipeline.Snippet BLOCK_SNIPPET = null;

    public static final RenderPipeline.Snippet GUI_SNIPPET = null;

    public static final RenderPipeline.Snippet GUI_TEXTURED_SNIPPET = null;

    public static final RenderPipeline GLINT = null;

    public static final RenderPipeline LIGHTNING = null;

    public static final RenderPipeline GUI = null;

    public static final RenderPipeline GUI_TEXTURED = null;

    private static RenderPipeline register(RenderPipeline pipeline) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/RenderPipelines.register:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;");
    }

    public RenderPipelines() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/RenderPipelines");
        }
    }
}
