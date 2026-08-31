package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import dev.pumpkin.shim.Unimplemented;

public abstract class HumanoidMobRenderer<T extends Mob, S extends HumanoidRenderState, M extends HumanoidModel<S>> extends AgeableMobRenderer<T, S, M> {

    public HumanoidMobRenderer(EntityRendererProvider.Context context, M model, float shadow) {
    }

    public HumanoidMobRenderer(EntityRendererProvider.Context context, M model, M babyModel, float shadow) {
    }

    public HumanoidMobRenderer(EntityRendererProvider.Context context, M model, M babyModel, float shadow, CustomHeadLayer.Transforms customHeadTransforms) {
    }

    public void extractRenderState(T entity, S state, float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/HumanoidMobRenderer.extractRenderState:(Lnet/minecraft/world/entity/Mob;Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;F)V");
    }

    public static void extractHumanoidRenderState(LivingEntity entity, HumanoidRenderState state, float partialTicks, ItemModelResolver itemModelResolver) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/HumanoidMobRenderer.extractHumanoidRenderState:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FLnet/minecraft/client/renderer/item/ItemModelResolver;)V");
    }

    public HumanoidMobRenderer() {
    }
}
