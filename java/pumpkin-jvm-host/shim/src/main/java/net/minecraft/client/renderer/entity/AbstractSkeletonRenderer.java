package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractSkeletonRenderer<T extends AbstractSkeleton, S extends SkeletonRenderState> extends HumanoidMobRenderer<T, S, SkeletonModel<S>> {

    public AbstractSkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation body, ArmorModelSet<ModelLayerLocation> armorSet) {
    }

    public AbstractSkeletonRenderer(EntityRendererProvider.Context context, ArmorModelSet<ModelLayerLocation> armorSet, SkeletonModel<S> bodyModel) {
    }

    public void extractRenderState(T entity, S state, float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/AbstractSkeletonRenderer.extractRenderState:(Lnet/minecraft/world/entity/monster/skeleton/AbstractSkeleton;Lnet/minecraft/client/renderer/entity/state/SkeletonRenderState;F)V");
    }

    public AbstractSkeletonRenderer() {
    }
}
