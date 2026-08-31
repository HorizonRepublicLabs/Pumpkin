package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import dev.pumpkin.shim.Unimplemented;

public abstract class LivingEntityRenderer<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer<T, S> implements RenderLayerParent<S, M> {

    protected final ItemModelResolver itemModelResolver = null;

    protected final List<RenderLayer<S, M>> layers = null;

    public LivingEntityRenderer(EntityRendererProvider.Context context, M model, float shadow) {
    }

    public final boolean addLayer(RenderLayer<S, M> layer) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.addLayer:(Lnet/minecraft/client/renderer/entity/layers/RenderLayer;)Z");
    }

    public M getModel() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.getModel:()Lnet/minecraft/client/model/EntityModel;");
    }

    public void submit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.submit:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public abstract Identifier getTextureLocation(final S state);

    protected RenderType getRenderType(S state, boolean isBodyVisible, boolean forceTransparent, boolean appearGlowing) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.getRenderType:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;ZZZ)Lnet/minecraft/client/renderer/rendertype/RenderType;");
    }

    public static int getOverlayCoords(LivingEntityRenderState state, float whiteOverlayProgress) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.getOverlayCoords:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)I");
    }

    protected boolean isBodyVisible(S state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.isBodyVisible:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)Z");
    }

    protected void setupRotations(S state, PoseStack poseStack, float bodyRot, float entityScale) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.setupRotations:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;FF)V");
    }

    protected float getWhiteOverlayProgress(S state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.getWhiteOverlayProgress:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)F");
    }

    protected void scale(S state, PoseStack poseStack) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.scale:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;)V");
    }

    public void extractRenderState(T entity, S state, float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.extractRenderState:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V");
    }

    public LivingEntityRenderer() {
    }
}
