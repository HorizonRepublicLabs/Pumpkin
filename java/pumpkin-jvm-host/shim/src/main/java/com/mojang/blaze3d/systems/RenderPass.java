package com.mojang.blaze3d.systems;

import com.mojang.blaze3d.IndexType;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuSampler;
import com.mojang.blaze3d.textures.GpuTextureView;
import java.nio.IntBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import org.joml.Vector4fc;
import org.lwjgl.PointerBuffer;
import dev.pumpkin.shim.Unimplemented;

public class RenderPass implements AutoCloseable {

    public RenderPass(RenderPassBackend backend, GpuDeviceBackend device, List<RenderPassDescriptor.Attachment<Optional<Vector4fc>>> colorAttachments, Runnable onFinish, RenderPass.RenderArea renderArea) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.<init>:(Lcom/mojang/blaze3d/systems/RenderPassBackend;Lcom/mojang/blaze3d/systems/GpuDeviceBackend;Ljava/util/List;Ljava/lang/Runnable;Lcom/mojang/blaze3d/systems/RenderPass$RenderArea;)V");
    }

    public void pushDebugGroup(Supplier<String> label) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.pushDebugGroup:(Ljava/util/function/Supplier;)V");
    }

    public void popDebugGroup() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.popDebugGroup:()V");
    }

    public void writeTimestamp(GpuQueryPool pool, int index) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.writeTimestamp:(Lcom/mojang/blaze3d/systems/GpuQueryPool;I)V");
    }

    public void setPipeline(RenderPipeline pipeline) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.setPipeline:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)V");
    }

    public void bindTexture(String name, GpuTextureView textureView, GpuSampler sampler) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.bindTexture:(Ljava/lang/String;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuSampler;)V");
    }

    public void setUniform(String name, GpuBuffer value) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.setUniform:(Ljava/lang/String;Lcom/mojang/blaze3d/buffers/GpuBuffer;)V");
    }

    public void setUniform(String name, GpuBufferSlice value) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.setUniform:(Ljava/lang/String;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V");
    }

    public void setViewport(int x, int y, int width, int height) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.setViewport:(IIII)V");
    }

    public void enableScissor(int x, int y, int width, int height) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.enableScissor:(IIII)V");
    }

    public void disableScissor() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.disableScissor:()V");
    }

    public void setVertexBuffer(int slot, GpuBufferSlice vertexBuffer) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.setVertexBuffer:(ILcom/mojang/blaze3d/buffers/GpuBufferSlice;)V");
    }

    public void setIndexBuffer(GpuBuffer indexBuffer, IndexType indexType) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.setIndexBuffer:(Lcom/mojang/blaze3d/buffers/GpuBuffer;Lcom/mojang/blaze3d/IndexType;)V");
    }

    public void drawIndexed(int indexCount, int instanceCount, int firstIndex, int vertexOffset, int firstInstance) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.drawIndexed:(IIIII)V");
    }

    public void multiDrawIndexed(IntBuffer drawParameters, int instanceCount, int firstInstance, int drawCount) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.multiDrawIndexed:(Ljava/nio/IntBuffer;III)V");
    }

    public void multiDrawIndexed(PointerBuffer firstIndexOffsets, IntBuffer indexCounts, IntBuffer vertexOffsets, int drawCount) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.multiDrawIndexed:(Lorg/lwjgl/PointerBuffer;Ljava/nio/IntBuffer;Ljava/nio/IntBuffer;I)V");
    }

    public void drawIndexedIndirect(GpuBufferSlice commands, int drawCount) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.drawIndexedIndirect:(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;I)V");
    }

    public <T> void drawMultipleIndexed(Collection<RenderPass.Draw<T>> draws, GpuBuffer defaultIndexBuffer, IndexType defaultIndexType, Collection<String> dynamicUniforms, T uniformArgument) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.drawMultipleIndexed:(Ljava/util/Collection;Lcom/mojang/blaze3d/buffers/GpuBuffer;Lcom/mojang/blaze3d/IndexType;Ljava/util/Collection;Ljava/lang/Object;)V");
    }

    public void draw(int vertexCount, int instanceCount, int firstVertex, int firstInstance) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.draw:(IIII)V");
    }

    public void multiDraw(IntBuffer drawParameters, int instanceCount, int firstInstance, int drawCount) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.multiDraw:(Ljava/nio/IntBuffer;III)V");
    }

    public void multiDraw(IntBuffer firstVertices, IntBuffer vertexCounts, int drawCount) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.multiDraw:(Ljava/nio/IntBuffer;Ljava/nio/IntBuffer;I)V");
    }

    public void drawIndirect(GpuBufferSlice commands, int drawCount) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.drawIndirect:(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;I)V");
    }

    public void close() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass.close:()V");
    }

    public record Draw<T>(int slot, GpuBuffer vertexBuffer, GpuBuffer indexBuffer, IndexType indexType, int firstIndex, int indexCount, int baseVertex, BiConsumer<T, RenderPass.UniformUploader> uniformUploaderConsumer) {

        public Draw(int slot, GpuBuffer vertexBuffer, GpuBuffer indexBuffer, IndexType indexType, int firstIndex, int indexCount, int baseVertex) {
            this((int) 0, (GpuBuffer) null, (GpuBuffer) null, (IndexType) null, (int) 0, (int) 0, (int) 0, (BiConsumer<T, RenderPass.UniformUploader>) null);
            throw Unimplemented.forMember("com/mojang/blaze3d/systems/RenderPass$Draw.<init>:(ILcom/mojang/blaze3d/buffers/GpuBuffer;Lcom/mojang/blaze3d/buffers/GpuBuffer;Lcom/mojang/blaze3d/IndexType;III)V");
        }
    }

    public record RenderArea(int x, int y, int width, int height) {
    }

    public interface UniformUploader {

        void upload(String name, GpuBufferSlice buffer);
    }

    public RenderPass() {
    }
}
