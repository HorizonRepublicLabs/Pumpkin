package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public abstract class EntityRenderer<T extends Entity, S extends EntityRenderState> {

    protected EntityRenderer(EntityRendererProvider.Context context) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.<init>:(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V");
    }

    public void submit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.submit:(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public abstract S createRenderState();

    public EntityRenderer() {
    }
}
