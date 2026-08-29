package com.mojang.blaze3d.buffers;

import java.nio.ByteBuffer;
import dev.pumpkin.shim.Unimplemented;

public record GpuBufferSlice(GpuBuffer buffer, long offset, long length) {

    public record MappedView(GpuBufferSlice slice, ByteBuffer data, Runnable onClose) implements AutoCloseable {

        public void close() {
            throw Unimplemented.forMember("com/mojang/blaze3d/buffers/GpuBufferSlice$MappedView.close:()V");
        }
    }
}
