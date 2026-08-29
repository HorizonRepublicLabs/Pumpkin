package com.mojang.math;

import org.joml.Quaternionf;
import dev.pumpkin.shim.Unimplemented;

public interface Axis {

    Axis XN = null;

    Axis XP = null;

    Axis YP = null;

    Axis ZP = null;

    Quaternionf rotation(float angle);

    default Quaternionf rotationDegrees(float angle) {
        throw Unimplemented.forMember("com/mojang/math/Axis.rotationDegrees:(F)Lorg/joml/Quaternionf;");
    }
}
