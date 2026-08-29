package net.minecraft.client.renderer.rendertype;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuSampler;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public final class RenderSetup {

    private RenderSetup(RenderPipeline pipeline, Map<String, RenderSetup.TextureBinding> textures, boolean useLightmap, boolean useOverlay, LayeringTransform layeringTransform, OutputTarget outputTarget, TextureTransform textureTransform, RenderSetup.OutlineProperty outlineProperty, boolean affectsCrumbling, boolean sortOnUpload) {
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup.toString:()Ljava/lang/String;");
    }

    public static RenderSetup.RenderSetupBuilder builder(RenderPipeline pipeline) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup.builder:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lnet/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder;");
    }

    public enum OutlineProperty {

        NONE, IS_OUTLINE, AFFECTS_OUTLINE;

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup$OutlineProperty.toString:()Ljava/lang/String;");
        }
    }

    public static class RenderSetupBuilder {

        private boolean useLightmap;

        private boolean useOverlay;

        private boolean affectsCrumbling;

        private RenderSetupBuilder(RenderPipeline pipeline) {
        }

        public RenderSetup.RenderSetupBuilder withTexture(String name, Identifier texture) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder.withTexture:(Ljava/lang/String;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder;");
        }

        public RenderSetup.RenderSetupBuilder withTexture(String name, Identifier texture, Supplier<GpuSampler> sampler) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder.withTexture:(Ljava/lang/String;Lnet/minecraft/resources/Identifier;Ljava/util/function/Supplier;)Lnet/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder;");
        }

        public RenderSetup.RenderSetupBuilder useLightmap() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder.useLightmap:()Lnet/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder;");
        }

        public RenderSetup.RenderSetupBuilder useOverlay() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder.useOverlay:()Lnet/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder;");
        }

        public RenderSetup.RenderSetupBuilder affectsCrumbling() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder.affectsCrumbling:()Lnet/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder;");
        }

        public RenderSetup.RenderSetupBuilder setOutline(RenderSetup.OutlineProperty outlineProperty) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder.setOutline:(Lnet/minecraft/client/renderer/rendertype/RenderSetup$OutlineProperty;)Lnet/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder;");
        }

        public RenderSetup createRenderSetup() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/RenderSetup$RenderSetupBuilder.createRenderSetup:()Lnet/minecraft/client/renderer/rendertype/RenderSetup;");
        }

        public RenderSetupBuilder() {
        }
    }

    record TextureBinding(Identifier location, Supplier<GpuSampler> sampler) {
    }

    public RenderSetup() {
    }
}
