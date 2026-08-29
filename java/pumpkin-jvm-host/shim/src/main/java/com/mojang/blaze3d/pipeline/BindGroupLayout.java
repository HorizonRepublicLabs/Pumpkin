package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.GpuFormat;
import com.mojang.blaze3d.shaders.UniformType;
import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public class BindGroupLayout {

    private BindGroupLayout(List<String> samplers, List<BindGroupLayout.UniformDescription> uniforms) {
    }

    public static class Builder {

        protected Builder() {
        }

        public BindGroupLayout build() {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/BindGroupLayout$Builder.build:()Lcom/mojang/blaze3d/pipeline/BindGroupLayout;");
        }
    }

    public record UniformDescription(String name, UniformType type, GpuFormat gpuFormat) {

        public UniformDescription(String name, UniformType type) {
            this((String) null, (UniformType) null, (GpuFormat) null);
        }

        public UniformDescription(String name, GpuFormat gpuFormat) {
            this((String) null, (UniformType) null, (GpuFormat) null);
        }
    }

    public BindGroupLayout() {
    }
}
