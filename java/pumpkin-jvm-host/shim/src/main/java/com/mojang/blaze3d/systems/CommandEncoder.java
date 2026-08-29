package com.mojang.blaze3d.systems;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.GpuFence;
import com.mojang.blaze3d.textures.GpuTexture;
import java.nio.ByteBuffer;
import org.joml.Vector4fc;
import dev.pumpkin.shim.Unimplemented;

public class CommandEncoder {

    public CommandEncoder(TracyGpuProfiler profiler, GpuDeviceBackend device, CommandEncoderBackend backend) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.<init>:(Lcom/mojang/blaze3d/systems/TracyGpuProfiler;Lcom/mojang/blaze3d/systems/GpuDeviceBackend;Lcom/mojang/blaze3d/systems/CommandEncoderBackend;)V");
    }

    public void submit() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.submit:()V");
    }

    public TransientMemory transientMemory() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.transientMemory:()Lcom/mojang/blaze3d/systems/TransientMemory;");
    }

    public RenderPass createRenderPass(RenderPassDescriptor descriptor) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.createRenderPass:(Lcom/mojang/blaze3d/systems/RenderPassDescriptor;)Lcom/mojang/blaze3d/systems/RenderPass;");
    }

    protected void submitRenderPass() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.submitRenderPass:()V");
    }

    public void clearColorTexture(GpuTexture colorTexture, Vector4fc clearColor) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.clearColorTexture:(Lcom/mojang/blaze3d/textures/GpuTexture;Lorg/joml/Vector4fc;)V");
    }

    public void clearColorAndDepthTextures(GpuTexture colorTexture, Vector4fc clearColor, GpuTexture depthTexture, double clearDepth) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.clearColorAndDepthTextures:(Lcom/mojang/blaze3d/textures/GpuTexture;Lorg/joml/Vector4fc;Lcom/mojang/blaze3d/textures/GpuTexture;D)V");
    }

    public void clearColorAndDepthTextures(GpuTexture colorTexture, Vector4fc clearColor, GpuTexture depthTexture, double clearDepth, int regionX, int regionY, int regionWidth, int regionHeight) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.clearColorAndDepthTextures:(Lcom/mojang/blaze3d/textures/GpuTexture;Lorg/joml/Vector4fc;Lcom/mojang/blaze3d/textures/GpuTexture;DIIII)V");
    }

    public void clearDepthTexture(GpuTexture depthTexture, double clearDepth) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.clearDepthTexture:(Lcom/mojang/blaze3d/textures/GpuTexture;D)V");
    }

    public void clearStencilTexture(GpuTexture texture, int value) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.clearStencilTexture:(Lcom/mojang/blaze3d/textures/GpuTexture;I)V");
    }

    public void writeToBuffer(GpuBufferSlice destination, ByteBuffer data) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.writeToBuffer:(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Ljava/nio/ByteBuffer;)V");
    }

    public void copyToBuffer(GpuBufferSlice source, GpuBufferSlice target) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.copyToBuffer:(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V");
    }

    public void writeToTexture(GpuTexture destination, ByteBuffer source, int mipLevel, int depthOrLayer, int destX, int destY, int width, int height) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.writeToTexture:(Lcom/mojang/blaze3d/textures/GpuTexture;Ljava/nio/ByteBuffer;IIIIII)V");
    }

    public void copyBufferToTexture(GpuBufferSlice source, int sourceX, int sourceY, int sourceWidth, int sourceHeight, GpuTexture destination, int destinationX, int destinationY, int copyWidth, int copyHeight, int mipLevel, int arrayLayer) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.copyBufferToTexture:(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;IIIILcom/mojang/blaze3d/textures/GpuTexture;IIIIII)V");
    }

    public void copyTextureToBuffer(GpuTexture source, GpuBuffer destination, long offset, Runnable callback, int mipLevel) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.copyTextureToBuffer:(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/buffers/GpuBuffer;JLjava/lang/Runnable;I)V");
    }

    public void copyTextureToBuffer(GpuTexture source, GpuBuffer destination, long offset, Runnable callback, int mipLevel, int x, int y, int width, int height) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.copyTextureToBuffer:(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/buffers/GpuBuffer;JLjava/lang/Runnable;IIIII)V");
    }

    public void copyTextureToTexture(GpuTexture source, GpuTexture destination, int mipLevel, int destX, int destY, int sourceX, int sourceY, int width, int height) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.copyTextureToTexture:(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/textures/GpuTexture;IIIIIII)V");
    }

    public GpuFence createFence() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.createFence:()Lcom/mojang/blaze3d/buffers/GpuFence;");
    }

    public void writeTimestamp(GpuQueryPool pool, int index) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/CommandEncoder.writeTimestamp:(Lcom/mojang/blaze3d/systems/GpuQueryPool;I)V");
    }

    protected CommandEncoder() {
    }
}
