package net.neoforged.neoforge.client.stencil;

import com.mojang.blaze3d.platform.CompareOp;

public record StencilPerFaceTest(StencilOperation fail, StencilOperation depthFail, StencilOperation pass, CompareOp compare) {
}
