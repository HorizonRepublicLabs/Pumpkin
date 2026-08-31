package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class EntityRenderer<T extends Entity, S extends EntityRenderState> {

    protected EntityRenderer(EntityRendererProvider.Context context) {
    }

    protected int getBlockLightLevel(T entity, BlockPos blockPos) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.getBlockLightLevel:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;)I");
    }

    public boolean shouldRender(T entity, Frustum culler, double camX, double camY, double camZ) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.shouldRender:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/client/renderer/culling/Frustum;DDD)Z");
    }

    protected boolean affectedByCulling(T entity) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.affectedByCulling:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public Vec3 getRenderOffset(S state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.getRenderOffset:(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;)Lnet/minecraft/world/phys/Vec3;");
    }

    public void submit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.submit:(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public abstract S createRenderState();

    public final S createRenderState(T entity, float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.createRenderState:(Lnet/minecraft/world/entity/Entity;F)Lnet/minecraft/client/renderer/entity/state/EntityRenderState;");
    }

    public void extractRenderState(T entity, S state, float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderer.extractRenderState:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/client/renderer/entity/state/EntityRenderState;F)V");
    }

    public EntityRenderer() {
    }
}
