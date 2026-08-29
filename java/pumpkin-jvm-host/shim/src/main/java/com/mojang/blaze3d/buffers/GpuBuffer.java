package com.mojang.blaze3d.buffers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import dev.pumpkin.shim.Unimplemented;

public abstract class GpuBuffer implements AutoCloseable {

    public GpuBuffer(int usage, long size) {
        throw Unimplemented.forMember("com/mojang/blaze3d/buffers/GpuBuffer.<init>:(IJ)V");
    }

    public long size() {
        throw Unimplemented.forMember("com/mojang/blaze3d/buffers/GpuBuffer.size:()J");
    }

    public abstract boolean isClosed();

    public abstract void close();

    public abstract GpuBufferSlice.MappedView map(final long offset, final long length, final boolean read, final boolean write);

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface Usage {
    }

    protected GpuBuffer() {
    }
}
