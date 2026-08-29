package net.minecraft.client.renderer.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.feature.submit.BatchableSubmit;
import net.minecraft.client.renderer.feature.submit.TranslucentSubmit;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import dev.pumpkin.shim.Unimplemented;

public class ModelFeatureRenderer extends RenderTypeFeatureRenderer<ModelFeatureRenderer.Submit<?>> {

    protected void buildGroup(FeatureFrameContext context, List<ModelFeatureRenderer.Submit<?>> submits) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/ModelFeatureRenderer.buildGroup:(Lnet/minecraft/client/renderer/feature/FeatureFrameContext;Ljava/util/List;)V");
    }

    public record CrumblingOverlay(int progress, PoseStack.Pose cameraPose) {
    }

    public record Submit<S>(RenderType renderType, PoseStack.Pose pose, Model<? super S> model, S state, int lightCoords, int overlayCoords, int tintedColor, TextureAtlasSprite sprite, PoseStack.Pose sheetedDecalPose) implements BatchableSubmit, TranslucentSubmit {

        public Object batchKey() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/ModelFeatureRenderer$Submit.batchKey:()Ljava/lang/Object;");
        }

        public float distanceToCameraSq() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/ModelFeatureRenderer$Submit.distanceToCameraSq:()F");
        }

        public FeatureRendererType<ModelFeatureRenderer.Submit<S>> featureType() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/ModelFeatureRenderer$Submit.featureType:()Lnet/minecraft/client/renderer/feature/FeatureRendererType;");
        }
    }

    protected ModelFeatureRenderer() {
    }
}
