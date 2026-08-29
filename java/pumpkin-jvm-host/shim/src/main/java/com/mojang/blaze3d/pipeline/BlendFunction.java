package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.platform.BlendFactor;
import com.mojang.blaze3d.platform.BlendOp;

public record BlendFunction(BlendEquation color, BlendEquation alpha) {

    public static final BlendFunction TRANSLUCENT = null;

    public BlendFunction(BlendFactor srcColorFactor, BlendFactor dstColorFactor, BlendOp colorOp, BlendFactor srcAlphaFactor, BlendFactor dstAlphaFactor, BlendOp alphaOp) {
        this((BlendEquation) null, (BlendEquation) null);
    }

    public BlendFunction(BlendFactor srcColorFactor, BlendFactor dstColorFactor, BlendFactor srcAlphaFactor, BlendFactor dstAlphaFactor) {
        this((BlendEquation) null, (BlendEquation) null);
    }

    public BlendFunction(BlendEquation equation) {
        this((BlendEquation) null, (BlendEquation) null);
    }

    public BlendFunction(BlendFactor srcFactor, BlendFactor dstFactor, BlendOp op) {
        this((BlendEquation) null, (BlendEquation) null);
    }

    public BlendFunction(BlendFactor srcFactor, BlendFactor dstFactor) {
        this((BlendEquation) null, (BlendEquation) null);
    }
}
