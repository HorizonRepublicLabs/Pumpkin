package net.minecraft.client.model.geom.builders;

import java.util.Set;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public final class CubeDefinition {

    public CubeDefinition(String comment, float xTexOffs, float yTexOffs, float minX, float minY, float minZ, float width, float height, float depth, CubeDeformation grow, boolean mirror, float xTexScale, float yTexScale, Set<Direction> visibleFaces) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/CubeDefinition.<init>:(Ljava/lang/String;FFFFFFFFLnet/minecraft/client/model/geom/builders/CubeDeformation;ZFFLjava/util/Set;)V");
    }

    public ModelPart.Cube bake(int texScaleX, int texScaleY) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/builders/CubeDefinition.bake:(II)Lnet/minecraft/client/model/geom/ModelPart$Cube;");
    }

    public CubeDefinition() {
    }
}
