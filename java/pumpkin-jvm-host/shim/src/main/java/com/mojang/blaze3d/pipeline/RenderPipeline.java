package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.platform.PolygonMode;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.renderer.ShaderDefines;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class RenderPipeline {

    protected RenderPipeline(Identifier location, Identifier vertexShader, Identifier fragmentShader, ShaderDefines shaderDefines, List<BindGroupLayout> bindGroupLayouts, ColorTargetState[] colorTargetStates, DepthStencilState depthStencilState, PolygonMode polygonMode, boolean cull, VertexFormat[] vertexFormatPerBuffer, PrimitiveTopology primitiveTopology, int sortKey) {
    }

    protected RenderPipeline(Identifier location, Identifier vertexShader, Identifier fragmentShader, ShaderDefines shaderDefines, List<BindGroupLayout> bindGroupLayouts, ColorTargetState[] colorTargetStates, DepthStencilState depthStencilState, PolygonMode polygonMode, boolean cull, VertexFormat[] vertexFormatPerBuffer, PrimitiveTopology primitiveTopology, Optional<net.neoforged.neoforge.client.stencil.StencilTest> stencilTest, int sortKey) {
    }

    public String toString() {
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline.toString:()Ljava/lang/String;");
    }

    public static RenderPipeline.Builder builder(RenderPipeline.Snippet... snippets) {
        throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline.builder:([Lcom/mojang/blaze3d/pipeline/RenderPipeline$Snippet;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
    }

    public static class Builder {

        protected Builder() {
        }

        public RenderPipeline.Builder withLocation(String location) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withLocation:(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withLocation(Identifier location) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withLocation:(Lnet/minecraft/resources/Identifier;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withFragmentShader(String fragmentShader) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withFragmentShader:(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withFragmentShader(Identifier fragmentShader) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withFragmentShader:(Lnet/minecraft/resources/Identifier;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withVertexShader(String vertexShader) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withVertexShader:(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withVertexShader(Identifier vertexShader) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withVertexShader:(Lnet/minecraft/resources/Identifier;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withShaderDefine(String key) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withShaderDefine:(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withShaderDefine(String key, int value) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withShaderDefine:(Ljava/lang/String;I)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withShaderDefine(String key, float value) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withShaderDefine:(Ljava/lang/String;F)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withBindGroupLayout(BindGroupLayout bindGroupLayout) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withBindGroupLayout:(Lcom/mojang/blaze3d/pipeline/BindGroupLayout;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withCull(boolean cull) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withCull:(Z)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withColorTargetState(int index, ColorTargetState colorTargetState) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withColorTargetState:(ILcom/mojang/blaze3d/pipeline/ColorTargetState;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withColorTargetState(ColorTargetState colorTargetState) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withColorTargetState:(Lcom/mojang/blaze3d/pipeline/ColorTargetState;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withDepthStencilState(DepthStencilState depthStencilState) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withDepthStencilState:(Lcom/mojang/blaze3d/pipeline/DepthStencilState;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withDepthStencilState(Optional<DepthStencilState> depthStencilState) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withDepthStencilState:(Ljava/util/Optional;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withVertexBinding(int bindingIndex, VertexFormat vertexFormat) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withVertexBinding:(ILcom/mojang/blaze3d/vertex/VertexFormat;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline.Builder withPrimitiveTopology(PrimitiveTopology primitiveTopology) {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.withPrimitiveTopology:(Lcom/mojang/blaze3d/PrimitiveTopology;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;");
        }

        public RenderPipeline build() {
            throw Unimplemented.forMember("com/mojang/blaze3d/pipeline/RenderPipeline$Builder.build:()Lcom/mojang/blaze3d/pipeline/RenderPipeline;");
        }
    }

    public record Snippet(Optional<Identifier> vertexShader, Optional<Identifier> fragmentShader, Optional<ShaderDefines> shaderDefines, Optional<List<BindGroupLayout>> bindGroupLayouts, ColorTargetState[] colorTargetStates, int activeColorTargetStateCount, Optional<DepthStencilState> depthStencilState, Optional<PolygonMode> polygonMode, Optional<Boolean> cull, VertexFormat[] vertexFormatPerBuffer, Optional<PrimitiveTopology> vertexFormatMode, Optional<net.neoforged.neoforge.client.stencil.StencilTest> stencilTest) {

        public Snippet(Optional<Identifier> vertexShader, Optional<Identifier> fragmentShader, Optional<ShaderDefines> shaderDefines, Optional<List<BindGroupLayout>> bindGroupLayouts, ColorTargetState[] colorTargetStates, int activeColorTargetStateCount, Optional<DepthStencilState> depthStencilState, Optional<PolygonMode> polygonMode, Optional<Boolean> cull, VertexFormat[] vertexFormatPerBuffer, Optional<PrimitiveTopology> vertexFormatMode) {
            this((Optional<Identifier>) null, (Optional<Identifier>) null, (Optional<ShaderDefines>) null, (Optional<List<BindGroupLayout>>) null, (ColorTargetState[]) null, (int) 0, (Optional<DepthStencilState>) null, (Optional<PolygonMode>) null, (Optional<Boolean>) null, (VertexFormat[]) null, (Optional<PrimitiveTopology>) null, (Optional<net.neoforged.neoforge.client.stencil.StencilTest>) null);
        }
    }

    public RenderPipeline() {
    }
}
