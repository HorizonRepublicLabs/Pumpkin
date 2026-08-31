package net.minecraft.client.model.monster.skeleton;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.BoggedRenderState;
import dev.pumpkin.shim.Unimplemented;

public class BoggedModel extends SkeletonModel<BoggedRenderState> {

    public BoggedModel(ModelPart root) {
    }

    public static LayerDefinition createBodyLayer() {
        throw Unimplemented.forMember("net/minecraft/client/model/monster/skeleton/BoggedModel.createBodyLayer:()Lnet/minecraft/client/model/geom/builders/LayerDefinition;");
    }

    public BoggedModel() {
    }
}
