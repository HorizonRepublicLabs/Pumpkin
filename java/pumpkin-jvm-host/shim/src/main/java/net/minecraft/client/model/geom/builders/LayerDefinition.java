package net.minecraft.client.model.geom.builders;

import dev.pumpkin.shim.Unimplemented;

public class LayerDefinition {

    private LayerDefinition(MeshDefinition mesh, MaterialDefinition material) {
    }

    public LayerDefinition apply(MeshTransformer transformer) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/LayerDefinition.apply:(Lnet/minecraft/client/model/geom/builders/MeshTransformer;)Lnet/minecraft/client/model/geom/builders/LayerDefinition;");
    }

    public static LayerDefinition create(MeshDefinition mesh, int xTexSize, int yTexSize) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/LayerDefinition.create:(Lnet/minecraft/client/model/geom/builders/MeshDefinition;II)Lnet/minecraft/client/model/geom/builders/LayerDefinition;");
    }

    public LayerDefinition() {
    }
}
