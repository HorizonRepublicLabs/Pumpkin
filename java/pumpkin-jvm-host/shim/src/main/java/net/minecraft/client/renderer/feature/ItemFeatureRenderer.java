package net.minecraft.client.renderer.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.renderer.feature.submit.TranslucentSubmit;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import dev.pumpkin.shim.Unimplemented;

public class ItemFeatureRenderer extends RenderTypeFeatureRenderer<ItemFeatureRenderer.Submit> {

    public static final Identifier ENCHANTED_GLINT_ARMOR = null;

    protected void buildGroup(FeatureFrameContext context, List<ItemFeatureRenderer.Submit> submits) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/ItemFeatureRenderer.buildGroup:(Lnet/minecraft/client/renderer/feature/FeatureFrameContext;Ljava/util/List;)V");
    }

    public record Submit(PoseStack.Pose pose, ItemDisplayContext displayContext, int lightCoords, int overlayCoords, int outlineColor, int[] tintLayers, List<BakedQuad> quads, ItemStackRenderState.FoilType foilType) implements TranslucentSubmit {

        public float distanceToCameraSq() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/ItemFeatureRenderer$Submit.distanceToCameraSq:()F");
        }

        public FeatureRendererType<ItemFeatureRenderer.Submit> featureType() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/ItemFeatureRenderer$Submit.featureType:()Lnet/minecraft/client/renderer/feature/FeatureRendererType;");
        }
    }

    public ItemFeatureRenderer() {
    }
}
