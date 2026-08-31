package net.minecraft.client.model.geom;

import java.util.Map;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import dev.pumpkin.shim.Unimplemented;

public class EntityModelSet {

    public EntityModelSet(Map<ModelLayerLocation, LayerDefinition> roots) {
    }

    public ModelPart bakeLayer(ModelLayerLocation id) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/EntityModelSet.bakeLayer:(Lnet/minecraft/client/model/geom/ModelLayerLocation;)Lnet/minecraft/client/model/geom/ModelPart;");
    }

    public EntityModelSet() {
    }
}
