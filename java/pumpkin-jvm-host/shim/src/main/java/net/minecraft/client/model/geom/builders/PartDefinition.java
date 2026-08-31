package net.minecraft.client.model.geom.builders;

import java.util.List;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import dev.pumpkin.shim.Unimplemented;

public class PartDefinition {

    PartDefinition(List<CubeDefinition> cubes, PartPose partPose) {
    }

    public PartDefinition addOrReplaceChild(String name, CubeListBuilder cubes, PartPose partPose) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/PartDefinition.addOrReplaceChild:(Ljava/lang/String;Lnet/minecraft/client/model/geom/builders/CubeListBuilder;Lnet/minecraft/client/model/geom/PartPose;)Lnet/minecraft/client/model/geom/builders/PartDefinition;");
    }

    public PartDefinition addOrReplaceChild(String name, PartDefinition child) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/PartDefinition.addOrReplaceChild:(Ljava/lang/String;Lnet/minecraft/client/model/geom/builders/PartDefinition;)Lnet/minecraft/client/model/geom/builders/PartDefinition;");
    }

    public ModelPart bake(int texScaleX, int texScaleY) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/PartDefinition.bake:(II)Lnet/minecraft/client/model/geom/ModelPart;");
    }

    public PartDefinition() {
    }
}
