package net.minecraft.client.model.geom.builders;

import dev.pumpkin.shim.Unimplemented;

public interface MeshTransformer {

    static MeshTransformer scaling(float factor) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/MeshTransformer.scaling:(F)Lnet/minecraft/client/model/geom/builders/MeshTransformer;");
    }

    MeshDefinition apply(MeshDefinition mesh);
}
