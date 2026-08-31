package net.minecraft.client.resources.model.cuboid;

import net.minecraft.core.Direction;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public record CuboidRotation(Vector3fc origin, CuboidRotation.RotationValue value, boolean rescale, Matrix4fc transform) {

    public CuboidRotation(Vector3fc origin, CuboidRotation.RotationValue value, boolean rescale) {
        this((Vector3fc) null, (CuboidRotation.RotationValue) null, (boolean) false, (Matrix4fc) null);
    }

    public record EulerXYZRotation(float x, float y, float z) implements CuboidRotation.RotationValue {

        public Matrix4f transformation() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/CuboidRotation$EulerXYZRotation.transformation:()Lorg/joml/Matrix4f;");
        }
    }

    public interface RotationValue {

        Matrix4f transformation();
    }

    public record SingleAxisRotation(Direction.Axis axis, float angle) implements CuboidRotation.RotationValue {

        public Matrix4f transformation() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/CuboidRotation$SingleAxisRotation.transformation:()Lorg/joml/Matrix4f;");
        }
    }
}
