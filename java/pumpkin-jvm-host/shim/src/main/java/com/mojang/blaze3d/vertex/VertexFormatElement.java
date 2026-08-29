package com.mojang.blaze3d.vertex;

import com.mojang.blaze3d.GpuFormat;
import dev.pumpkin.shim.Unimplemented;

public record VertexFormatElement(String name, int offset, GpuFormat format) {

    public String toString() {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexFormatElement.toString:()Ljava/lang/String;");
    }
}
