package net.minecraft.client.renderer.chunk;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.pumpkin.shim.Unimplemented;

public enum ChunkSectionLayer {

    SOLID, CUTOUT, TRANSLUCENT;

    public RenderPipeline pipeline() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/chunk/ChunkSectionLayer.pipeline:()Lcom/mojang/blaze3d/pipeline/RenderPipeline;");
    }
}
