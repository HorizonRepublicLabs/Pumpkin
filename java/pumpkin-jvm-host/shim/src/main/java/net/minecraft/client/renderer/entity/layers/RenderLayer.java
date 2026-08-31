package net.minecraft.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public abstract class RenderLayer<S extends EntityRenderState, M extends EntityModel<? super S>> {

    public RenderLayer(RenderLayerParent<S, M> renderer) {
    }

    public abstract void submit(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, S state, float yRot, float xRot);

    public RenderLayer() {
    }
}
