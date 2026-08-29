package net.minecraft.client.renderer;

import com.mojang.blaze3d.IndexType;
import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuFence;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexSorting;
import java.util.List;
import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public class StagedVertexBuffer implements AutoCloseable {

    public StagedVertexBuffer(Supplier<String> label, int initialCapacity) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/StagedVertexBuffer.<init>:(Ljava/util/function/Supplier;I)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/StagedVertexBuffer.close:()V");
    }

    public static class Draw {

        private Draw(VertexFormat format, PrimitiveTopology primitiveTopology, VertexSorting quadSorting) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/StagedVertexBuffer$Draw.<init>:(Lcom/mojang/blaze3d/vertex/VertexFormat;Lcom/mojang/blaze3d/PrimitiveTopology;Lcom/mojang/blaze3d/vertex/VertexSorting;)V");
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/StagedVertexBuffer$Draw.isEmpty:()Z");
        }

        public Draw() {
        }
    }

    public record ExecuteInfo(GpuBuffer vertexBuffer, GpuBuffer indexBuffer, IndexType indexType, int baseVertex, int firstIndex, int indexCount) {
    }

    private static class GpuBufferPool implements AutoCloseable {

        private GpuBufferPool(Supplier<String> label, int usage) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/StagedVertexBuffer$GpuBufferPool.<init>:(Ljava/util/function/Supplier;I)V");
        }

        public void close() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/StagedVertexBuffer$GpuBufferPool.close:()V");
        }

        private record PendingRecycle(List<GpuBuffer> buffers, GpuFence fence) implements AutoCloseable {

            public void close() {
                throw Unimplemented.forMember("net/minecraft/client/renderer/StagedVertexBuffer$GpuBufferPool$PendingRecycle.close:()V");
            }
        }

        protected GpuBufferPool() {
        }
    }

    public StagedVertexBuffer() {
    }
}
