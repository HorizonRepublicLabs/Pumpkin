package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.Mob;
import dev.pumpkin.shim.Unimplemented;

public abstract class MobRenderer<T extends Mob, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends LivingEntityRenderer<T, S, M> {

    public MobRenderer(EntityRendererProvider.Context context, M model, float shadow) {
    }

    protected float getShadowRadius(S state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/MobRenderer.getShadowRadius:(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)F");
    }

    public MobRenderer() {
    }
}
