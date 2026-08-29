package net.minecraft.client.model.geom.builders;

import java.util.List;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import dev.pumpkin.shim.Unimplemented;

public class PartDefinition {

    PartDefinition(List<CubeDefinition> cubes, PartPose partPose) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/PartDefinition.<init>:(Ljava/util/List;Lnet/minecraft/client/model/geom/PartPose;)V");
    }

    public ModelPart bake(int texScaleX, int texScaleY) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/PartDefinition.bake:(II)Lnet/minecraft/client/model/geom/ModelPart;");
    }

    protected PartDefinition() {
    }
}
