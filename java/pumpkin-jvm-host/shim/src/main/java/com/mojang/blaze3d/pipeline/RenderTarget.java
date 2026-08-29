package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.GpuFormat;

public abstract class RenderTarget {

    public RenderTarget(String label, boolean useDepth, GpuFormat format) {
    }

    public RenderTarget(String label, boolean useDepth, boolean useStencil, GpuFormat format) {
    }

    public RenderTarget() {
    }
}
