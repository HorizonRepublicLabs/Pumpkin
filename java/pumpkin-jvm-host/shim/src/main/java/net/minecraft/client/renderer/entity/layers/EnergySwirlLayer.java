package net.minecraft.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public abstract class EnergySwirlLayer<S extends EntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    public EnergySwirlLayer(RenderLayerParent<S, M> renderer) {
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/layers/EnergySwirlLayer.submit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/EntityRenderState;FF)V");
    }

    protected abstract boolean isPowered(S state);

    protected abstract float xOffset(final float t);

    protected abstract Identifier getTextureLocation();

    protected abstract M model();

    public EnergySwirlLayer() {
    }
}
