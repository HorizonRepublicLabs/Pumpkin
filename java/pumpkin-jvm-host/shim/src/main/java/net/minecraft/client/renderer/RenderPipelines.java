package net.minecraft.client.renderer;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.pumpkin.shim.Unimplemented;

public class RenderPipelines {

    public static final RenderPipeline.Snippet BLOCK_SNIPPET = null;

    public static final RenderPipeline GUI_TEXTURED = null;

    private static RenderPipeline register(RenderPipeline pipeline) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/RenderPipelines.register:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;");
    }

    protected RenderPipelines() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/RenderPipelines");
        }
    }
}
