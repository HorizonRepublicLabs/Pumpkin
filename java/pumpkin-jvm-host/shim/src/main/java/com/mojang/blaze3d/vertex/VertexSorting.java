package com.mojang.blaze3d.vertex;

import org.joml.Vector3f;

public interface VertexSorting {

    int[] sort(CompactVectorArray points);

    interface DistanceFunction {

        float apply(Vector3f value);
    }
}
