package net.minecraft.client.model.geom.builders;

import java.util.function.UnaryOperator;
import net.minecraft.client.model.geom.PartPose;
import dev.pumpkin.shim.Unimplemented;

public class MeshDefinition {

    public MeshDefinition() {
    }

    private MeshDefinition(PartDefinition root) {
    }

    public PartDefinition getRoot() {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/MeshDefinition.getRoot:()Lnet/minecraft/client/model/geom/builders/PartDefinition;");
    }

    public MeshDefinition transformed(UnaryOperator<PartPose> function) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/MeshDefinition.transformed:(Ljava/util/function/UnaryOperator;)Lnet/minecraft/client/model/geom/builders/MeshDefinition;");
    }

    public MeshDefinition apply(MeshTransformer transformer) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/MeshDefinition.apply:(Lnet/minecraft/client/model/geom/builders/MeshTransformer;)Lnet/minecraft/client/model/geom/builders/MeshDefinition;");
    }
}
