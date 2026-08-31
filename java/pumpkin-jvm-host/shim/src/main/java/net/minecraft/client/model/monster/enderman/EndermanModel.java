package net.minecraft.client.model.monster.enderman;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import dev.pumpkin.shim.Unimplemented;

public class EndermanModel<T extends EndermanRenderState> extends HumanoidModel<T> {

    public EndermanModel(ModelPart root) {
    }

    public static LayerDefinition createBodyLayer() {
        throw Unimplemented.forMember("net/minecraft/client/model/monster/enderman/EndermanModel.createBodyLayer:()Lnet/minecraft/client/model/geom/builders/LayerDefinition;");
    }

    public void setupAnim(T state) {
        throw Unimplemented.forMember("net/minecraft/client/model/monster/enderman/EndermanModel.setupAnim:(Lnet/minecraft/client/renderer/entity/state/EndermanRenderState;)V");
    }

    public EndermanModel() {
    }
}
