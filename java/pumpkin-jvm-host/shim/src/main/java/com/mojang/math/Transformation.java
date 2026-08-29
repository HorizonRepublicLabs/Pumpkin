package com.mojang.math;

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

    public Transformation inverse() {
        throw Unimplemented.forMember("com/mojang/math/Transformation.inverse:()Lcom/mojang/math/Transformation;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("com/mojang/math/Transformation.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("com/mojang/math/Transformation.hashCode:()I");
    }

    public Transformation() {
    }
}
