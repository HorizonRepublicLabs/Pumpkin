package net.minecraft.client.renderer;

import net.minecraft.core.Direction;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public enum FaceInfo {

    DOWN,
    UP,
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public static FaceInfo fromFacing(Direction direction) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/FaceInfo.fromFacing:(Lnet/minecraft/core/Direction;)Lnet/minecraft/client/renderer/FaceInfo;");
    }

    public FaceInfo.VertexInfo getVertexInfo(int index) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/FaceInfo.getVertexInfo:(I)Lnet/minecraft/client/renderer/FaceInfo$VertexInfo;");
    }

    public enum Extent {

        MIN_X,
        MIN_Y,
        MIN_Z,
        MAX_X,
        MAX_Y,
        MAX_Z;

        public float select(Vector3fc min, Vector3fc max) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/FaceInfo$Extent.select:(Lorg/joml/Vector3fc;Lorg/joml/Vector3fc;)F");
        }

        public float select(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/FaceInfo$Extent.select:(FFFFFF)F");
        }
    }

    public record VertexInfo(FaceInfo.Extent xFace, FaceInfo.Extent yFace, FaceInfo.Extent zFace) {

        public Vector3f select(Vector3fc min, Vector3fc max) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/FaceInfo$VertexInfo.select:(Lorg/joml/Vector3fc;Lorg/joml/Vector3fc;)Lorg/joml/Vector3f;");
        }
    }
}
