package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.EnderMan;
import dev.pumpkin.shim.Unimplemented;

public class EndermanRenderer extends MobRenderer<EnderMan, EndermanRenderState, EndermanModel<EndermanRenderState>> {

    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = null;

    public EndermanRenderer(EntityRendererProvider.Context context) {
    }

    public Identifier getTextureLocation(EndermanRenderState state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EndermanRenderer.getTextureLocation:(Lnet/minecraft/client/renderer/entity/state/EndermanRenderState;)Lnet/minecraft/resources/Identifier;");
    }

    public EndermanRenderState createRenderState() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EndermanRenderer.createRenderState:()Lnet/minecraft/client/renderer/entity/state/EndermanRenderState;");
    }

    public EndermanRenderer() {
    }
}
