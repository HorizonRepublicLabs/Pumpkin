package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.platform.CompareOp;

public record DepthStencilState(CompareOp depthTest, boolean writeDepth, float depthBiasScaleFactor, float depthBiasConstant) {

    public static final DepthStencilState DEFAULT = null;

    public DepthStencilState(CompareOp depthTest, boolean depthWrite) {
        this((CompareOp) null, (boolean) false, (float) 0.0F, (float) 0.0F);
    }
}
