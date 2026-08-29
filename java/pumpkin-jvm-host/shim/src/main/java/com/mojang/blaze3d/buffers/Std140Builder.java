package com.mojang.blaze3d.buffers;

import java.nio.ByteBuffer;
import dev.pumpkin.shim.Unimplemented;

public class Std140Builder {

    private Std140Builder(ByteBuffer buffer) {
    }

    public ByteBuffer get() {
        throw Unimplemented.forMember("com/mojang/blaze3d/buffers/Std140Builder.get:()Ljava/nio/ByteBuffer;");
    }

    public Std140Builder() {
    }
}
