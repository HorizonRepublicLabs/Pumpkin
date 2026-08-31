package net.minecraft.client.renderer.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.submit.BatchableSubmit;
import net.minecraft.client.renderer.rendertype.RenderType;
import dev.pumpkin.shim.Unimplemented;

public class CustomFeatureRenderer extends RenderTypeFeatureRenderer<CustomFeatureRenderer.Submit> {

    protected void buildGroup(FeatureFrameContext context, List<CustomFeatureRenderer.Submit> submits) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/CustomFeatureRenderer.buildGroup:(Lnet/minecraft/client/renderer/feature/FeatureFrameContext;Ljava/util/List;)V");
    }

    public record Submit(PoseStack.Pose pose, RenderType renderType, SubmitNodeCollector.CustomGeometryRenderer customGeometryRenderer) implements BatchableSubmit {

        public Object batchKey() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/CustomFeatureRenderer$Submit.batchKey:()Ljava/lang/Object;");
        }

        public FeatureRendererType<CustomFeatureRenderer.Submit> featureType() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/CustomFeatureRenderer$Submit.featureType:()Lnet/minecraft/client/renderer/feature/FeatureRendererType;");
        }
    }

    public CustomFeatureRenderer() {
    }
}
