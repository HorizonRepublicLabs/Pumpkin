package net.minecraft.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import dev.pumpkin.shim.Unimplemented;

public class CustomHeadLayer<S extends LivingEntityRenderState, M extends EntityModel<S> & HeadedModel> extends RenderLayer<S, M> {

    public CustomHeadLayer(RenderLayerParent<S, M> renderer, EntityModelSet modelSet, PlayerSkinRenderCache playerSkinRenderCache) {
    }

    public CustomHeadLayer(RenderLayerParent<S, M> renderer, EntityModelSet modelSet, PlayerSkinRenderCache playerSkinRenderCache, CustomHeadLayer.Transforms transforms) {
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/layers/CustomHeadLayer.submit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V");
    }

    public record Transforms(float yOffset, float skullYOffset, float horizontalScale) {
    }

    public CustomHeadLayer() {
    }
}
