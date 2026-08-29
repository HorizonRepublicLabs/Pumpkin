package net.minecraft.client.model.geom;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record ModelLayerLocation(Identifier model, String layer) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelLayerLocation.toString:()Ljava/lang/String;");
    }
}
