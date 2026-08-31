package net.minecraft.client.renderer.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.feature.submit.TranslucentSubmit;
import net.minecraft.client.renderer.rendertype.RenderType;
import dev.pumpkin.shim.Unimplemented;

public class BlockModelFeatureRenderer extends RenderTypeFeatureRenderer<BlockModelFeatureRenderer.Submit> {

    protected void buildGroup(FeatureFrameContext context, List<BlockModelFeatureRenderer.Submit> submits) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/BlockModelFeatureRenderer.buildGroup:(Lnet/minecraft/client/renderer/feature/FeatureFrameContext;Ljava/util/List;)V");
    }

    public record Submit(PoseStack.Pose pose, RenderType renderType, List<BlockStateModelPart> modelParts, int[] tintLayers, int lightCoords, int overlayCoords, int tintColor, PoseStack.Pose sheetedDecalPose) implements TranslucentSubmit {

        public float distanceToCameraSq() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/BlockModelFeatureRenderer$Submit.distanceToCameraSq:()F");
        }

        public FeatureRendererType<BlockModelFeatureRenderer.Submit> featureType() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/BlockModelFeatureRenderer$Submit.featureType:()Lnet/minecraft/client/renderer/feature/FeatureRendererType;");
        }
    }

    public BlockModelFeatureRenderer() {
    }
}
