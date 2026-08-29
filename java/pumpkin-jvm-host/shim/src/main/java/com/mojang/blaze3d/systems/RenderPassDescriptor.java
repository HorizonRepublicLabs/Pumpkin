package com.mojang.blaze3d.systems;

import com.mojang.blaze3d.textures.GpuTextureView;
import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public class RenderPassDescriptor {

    public static RenderPassDescriptor create(Supplier<String> label) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPassDescriptor.create:(Ljava/util/function/Supplier;)Lcom/mojang/blaze3d/systems/RenderPassDescriptor;");
    }

    private RenderPassDescriptor(Supplier<String> label) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPassDescriptor.<init>:(Ljava/util/function/Supplier;)V");
    }

    public record Attachment<T>(GpuTextureView textureView, T clearValue) {
    }

    protected RenderPassDescriptor() {
    }
}
