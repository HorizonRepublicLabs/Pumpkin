package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import dev.pumpkin.shim.Unimplemented;

public abstract class LivingEntityRenderer<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer<T, S> implements RenderLayerParent<S, M> {

    public LivingEntityRenderer(EntityRendererProvider.Context context, M model, float shadow) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.<init>:(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;Lnet/minecraft/client/model/EntityModel;F)V");
    }

    public M getModel() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.getModel:()Lnet/minecraft/client/model/EntityModel;");
    }

    public void submit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/LivingEntityRenderer.submit:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public abstract Identifier getTextureLocation(final S state);

    public LivingEntityRenderer() {
    }
}
