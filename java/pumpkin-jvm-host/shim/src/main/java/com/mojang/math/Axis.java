package com.mojang.math;

import org.joml.Quaternionf;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface Axis {

    Axis XN = Stubs.of(Axis.class, "com/mojang/math/Axis");

    Axis XP = Stubs.of(Axis.class, "com/mojang/math/Axis");

    Axis YP = Stubs.of(Axis.class, "com/mojang/math/Axis");

    Axis ZP = Stubs.of(Axis.class, "com/mojang/math/Axis");

    Quaternionf rotation(float angle);

    default Quaternionf rotationDegrees(float angle) {
        throw Unimplemented.forMember("com/mojang/math/Axis.rotationDegrees:(F)Lorg/joml/Quaternionf;");
    }
}
