package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.platform.BlendFactor;
import com.mojang.blaze3d.platform.BlendOp;
import dev.pumpkin.shim.Unimplemented;

public record BlendFunction(BlendEquation color, BlendEquation alpha) {

    public static final BlendFunction TRANSLUCENT = null;

    public BlendFunction(BlendFactor srcColorFactor, BlendFactor dstColorFactor, BlendOp colorOp, BlendFactor srcAlphaFactor, BlendFactor dstAlphaFactor, BlendOp alphaOp) {
        this((BlendEquation) null, (BlendEquation) null);
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BlendFunction.<init>:(Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendOp;Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendOp;)V");
    }

    public BlendFunction(BlendFactor srcColorFactor, BlendFactor dstColorFactor, BlendFactor srcAlphaFactor, BlendFactor dstAlphaFactor) {
        this((BlendEquation) null, (BlendEquation) null);
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BlendFunction.<init>:(Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendFactor;)V");
    }

    public BlendFunction(BlendEquation equation) {
        this((BlendEquation) null, (BlendEquation) null);
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BlendFunction.<init>:(Lcom/mojang/blaze3d/pipeline/BlendEquation;)V");
    }

    public BlendFunction(BlendFactor srcFactor, BlendFactor dstFactor, BlendOp op) {
        this((BlendEquation) null, (BlendEquation) null);
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BlendFunction.<init>:(Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendOp;)V");
    }

    public BlendFunction(BlendFactor srcFactor, BlendFactor dstFactor) {
        this((BlendEquation) null, (BlendEquation) null);
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BlendFunction.<init>:(Lcom/mojang/blaze3d/platform/BlendFactor;Lcom/mojang/blaze3d/platform/BlendFactor;)V");
    }
}
