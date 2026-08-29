package com.mojang.blaze3d.vertex;

import org.joml.Vector3f;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public class CompactVectorArray {

    public CompactVectorArray(int count) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/CompactVectorArray.<init>:(I)V");
    }

    public int size() {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/CompactVectorArray.size:()I");
    }

    public void set(int index, Vector3fc v) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/CompactVectorArray.set:(ILorg/joml/Vector3fc;)V");
    }

    public Vector3f get(int index, Vector3f output) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/CompactVectorArray.get:(ILorg/joml/Vector3f;)Lorg/joml/Vector3f;");
    }

    protected CompactVectorArray() {
    }
}
