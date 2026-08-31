package net.minecraft.client.model.geom;

import dev.pumpkin.shim.Unimplemented;

public record PartPose(float x, float y, float z, float xRot, float yRot, float zRot, float xScale, float yScale, float zScale) {

    public static final PartPose ZERO = null;

    public static PartPose offset(float x, float y, float z) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/PartPose.offset:(FFF)Lnet/minecraft/client/model/geom/PartPose;");
    }

    public static PartPose rotation(float x, float y, float z) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/PartPose.rotation:(FFF)Lnet/minecraft/client/model/geom/PartPose;");
    }

    public static PartPose offsetAndRotation(float offsetX, float offsetY, float offsetZ, float rotationX, float rotationY, float rotationZ) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/PartPose.offsetAndRotation:(FFFFFF)Lnet/minecraft/client/model/geom/PartPose;");
    }

    public PartPose scaled(float factor) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/PartPose.scaled:(F)Lnet/minecraft/client/model/geom/PartPose;");
    }

    public PartPose scaled(float scaleX, float scaleY, float scaleZ) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/PartPose.scaled:(FFF)Lnet/minecraft/client/model/geom/PartPose;");
    }
}
