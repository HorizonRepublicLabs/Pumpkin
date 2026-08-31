package com.mojang.math;

import java.util.Optional;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;
import net.neoforged.neoforge.common.extensions.ITransformationExtension;
import dev.pumpkin.shim.Unimplemented;

public final class Transformation implements ITransformationExtension {

    public Transformation(Matrix4fc matrix) {
    }

    public Transformation(Vector3fc translation, Quaternionfc leftRotation, Vector3fc scale, Quaternionfc rightRotation) {
    }

    public Transformation compose(Transformation that) {
        throw Unimplemented.forMember("com/mojang/math/Transformation.compose:(Lcom/mojang/math/Transformation;)Lcom/mojang/math/Transformation;");
    }

    public Transformation inverse() {
        throw Unimplemented.forMember("com/mojang/math/Transformation.inverse:()Lcom/mojang/math/Transformation;");
    }

    private static Matrix4f compose(Vector3fc translation, Quaternionfc leftRotation, Vector3fc scale, Quaternionfc rightRotation) {
        throw Unimplemented.forMember("com/mojang/math/Transformation.compose:(Lorg/joml/Vector3fc;Lorg/joml/Quaternionfc;Lorg/joml/Vector3fc;Lorg/joml/Quaternionfc;)Lorg/joml/Matrix4f;");
    }

    public Vector3fc scale() {
        throw Unimplemented.forMember("com/mojang/math/Transformation.scale:()Lorg/joml/Vector3fc;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("com/mojang/math/Transformation.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("com/mojang/math/Transformation.hashCode:()I");
    }

    public static Matrix4fc compose(Matrix4fc parent, Optional<Transformation> transform) {
        throw Unimplemented.forMember("com/mojang/math/Transformation.compose:(Lorg/joml/Matrix4fc;Ljava/util/Optional;)Lorg/joml/Matrix4fc;");
    }

    public Transformation() {
    }
}
