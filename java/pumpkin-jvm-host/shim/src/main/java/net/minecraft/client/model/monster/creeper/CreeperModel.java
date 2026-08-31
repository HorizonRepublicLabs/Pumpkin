package net.minecraft.client.model.monster.creeper;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import dev.pumpkin.shim.Unimplemented;

public class CreeperModel extends EntityModel<CreeperRenderState> {

    public CreeperModel(ModelPart root) {
    }

    public static LayerDefinition createBodyLayer(CubeDeformation g) {
        throw Unimplemented.forMember("net/minecraft/client/model/monster/creeper/CreeperModel.createBodyLayer:(Lnet/minecraft/client/model/geom/builders/CubeDeformation;)Lnet/minecraft/client/model/geom/builders/LayerDefinition;");
    }

    public CreeperModel() {
    }
}
