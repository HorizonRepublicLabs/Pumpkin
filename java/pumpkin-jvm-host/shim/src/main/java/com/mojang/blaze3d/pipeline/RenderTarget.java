package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.GpuFormat;
import dev.pumpkin.shim.Unimplemented;

public abstract class RenderTarget {

    public RenderTarget(String label, boolean useDepth, GpuFormat format) {
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderTarget.<init>:(Ljava/lang/String;ZLcom/mojang/blaze3d/GpuFormat;)V");
    }

    public RenderTarget(String label, boolean useDepth, boolean useStencil, GpuFormat format) {
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderTarget.<init>:(Ljava/lang/String;ZZLcom/mojang/blaze3d/GpuFormat;)V");
    }

    protected RenderTarget() {
    }
}
