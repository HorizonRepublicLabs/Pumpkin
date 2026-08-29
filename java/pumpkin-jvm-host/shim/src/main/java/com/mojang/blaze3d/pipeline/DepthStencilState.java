package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.platform.CompareOp;
import dev.pumpkin.shim.Unimplemented;

public record DepthStencilState(CompareOp depthTest, boolean writeDepth, float depthBiasScaleFactor, float depthBiasConstant) {

    public static final DepthStencilState DEFAULT = null;

    public DepthStencilState(CompareOp depthTest, boolean depthWrite) {
        this((CompareOp) null, (boolean) false, (float) 0.0F, (float) 0.0F);
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/DepthStencilState.<init>:(Lcom/mojang/blaze3d/platform/CompareOp;Z)V");
    }
}
