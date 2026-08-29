package com.mojang.blaze3d.systems;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import java.nio.ByteBuffer;
import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public interface TransientMemory {

    ByteBuffer allocateCpu(final long size, final long alignment, final long minimumAllocation, final long elementSize);

    GpuBufferSlice.MappedView allocateStaging(final long size, final long alignment, final int usage, final long minimumAllocation, final long elementSize);

    GpuBufferSlice allocateGpu(final long size, final long alignment, final int usage, final long minimumAllocation, final long elementSize);

    GpuBufferSlice.MappedView allocateGpuMapped(final long size, final long alignment, final int usage, final long minimumAllocation, final long elementSize);

    default GpuBufferSlice uploadStaging(ByteBuffer data, long alignment, int usage, long minimumAllocation, long elementSize) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/TransientMemory.uploadStaging:(Ljava/nio/ByteBuffer;JIJJ)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;");
    }

    GpuBufferSlice uploadStaging(final List<ByteBuffer> data, final long alignment, final int usage, final long minimumAllocation, final long elementSize);

    default GpuBufferSlice uploadGpu(ByteBuffer data, long alignment, int usage, long minimumAllocation, long elementSize) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/TransientMemory.uploadGpu:(Ljava/nio/ByteBuffer;JIJJ)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;");
    }

    GpuBufferSlice uploadGpu(final List<ByteBuffer> data, final long alignment, final int usage, final long minimumAllocation, final long elementSize);

    List<GpuBufferSlice> multiUploadStaging(final List<ByteBuffer> data, final long alignment, final int usage);

    List<GpuBufferSlice> multiUploadGpu(final List<ByteBuffer> data, final long alignment, final int usage);
}
