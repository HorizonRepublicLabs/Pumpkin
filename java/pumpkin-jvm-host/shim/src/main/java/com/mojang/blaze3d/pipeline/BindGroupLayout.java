package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.GpuFormat;
import com.mojang.blaze3d.shaders.UniformType;
import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public class BindGroupLayout {

    private BindGroupLayout(List<String> samplers, List<BindGroupLayout.UniformDescription> uniforms) {
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BindGroupLayout.<init>:(Ljava/util/List;Ljava/util/List;)V");
    }

    public static class Builder {

        protected Builder() {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BindGroupLayout$Builder.<init>:()V");
        }

        public BindGroupLayout build() {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BindGroupLayout$Builder.build:()Lcom/mojang/blaze3d/pipeline/BindGroupLayout;");
        }
    }

    public record UniformDescription(String name, UniformType type, GpuFormat gpuFormat) {

        public UniformDescription(String name, UniformType type) {
            this((String) null, (UniformType) null, (GpuFormat) null);
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BindGroupLayout$UniformDescription.<init>:(Ljava/lang/String;Lcom/mojang/blaze3d/shaders/UniformType;)V");
        }

        public UniformDescription(String name, GpuFormat gpuFormat) {
            this((String) null, (UniformType) null, (GpuFormat) null);
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BindGroupLayout$UniformDescription.<init>:(Ljava/lang/String;Lcom/mojang/blaze3d/GpuFormat;)V");
        }
    }

    protected BindGroupLayout() {
    }
}
