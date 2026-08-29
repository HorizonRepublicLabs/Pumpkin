package com.mojang.blaze3d.systems;

import com.mojang.blaze3d.GpuFormat;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.CompiledRenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.shaders.ShaderSource;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuSampler;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public class GpuDevice {

    public GpuDevice(GpuDeviceBackend backend, Runnable criticalShaderLoader) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.<init>:(Lcom/mojang/blaze3d/systems/GpuDeviceBackend;Ljava/lang/Runnable;)V");
    }

    public GpuSurface createSurface(long windowHandle) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createSurface:(J)Lcom/mojang/blaze3d/systems/GpuSurface;");
    }

    public CommandEncoder createCommandEncoder() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createCommandEncoder:()Lcom/mojang/blaze3d/systems/CommandEncoder;");
    }

    public GpuSampler createSampler(AddressMode addressModeU, AddressMode addressModeV, FilterMode minFilter, FilterMode magFilter, int maxAnisotropy, OptionalDouble maxLod) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createSampler:(Lcom/mojang/blaze3d/textures/AddressMode;Lcom/mojang/blaze3d/textures/AddressMode;Lcom/mojang/blaze3d/textures/FilterMode;Lcom/mojang/blaze3d/textures/FilterMode;ILjava/util/OptionalDouble;)Lcom/mojang/blaze3d/textures/GpuSampler;");
    }

    public GpuTexture createTexture(Supplier<String> label, int usage, GpuFormat format, int width, int height, int depthOrLayers, int mipLevels) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createTexture:(Ljava/util/function/Supplier;ILcom/mojang/blaze3d/GpuFormat;IIII)Lcom/mojang/blaze3d/textures/GpuTexture;");
    }

    public GpuTexture createTexture(String label, int usage, GpuFormat format, int width, int height, int depthOrLayers, int mipLevels) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createTexture:(Ljava/lang/String;ILcom/mojang/blaze3d/GpuFormat;IIII)Lcom/mojang/blaze3d/textures/GpuTexture;");
    }

    public GpuTextureView createTextureView(GpuTexture texture) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createTextureView:(Lcom/mojang/blaze3d/textures/GpuTexture;)Lcom/mojang/blaze3d/textures/GpuTextureView;");
    }

    public GpuTextureView createTextureView(GpuTexture texture, int baseMipLevel, int mipLevels) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createTextureView:(Lcom/mojang/blaze3d/textures/GpuTexture;II)Lcom/mojang/blaze3d/textures/GpuTextureView;");
    }

    public GpuBuffer createBuffer(Supplier<String> label, int usage, long size) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createBuffer:(Ljava/util/function/Supplier;IJ)Lcom/mojang/blaze3d/buffers/GpuBuffer;");
    }

    public GpuBuffer createBuffer(Supplier<String> label, int usage, ByteBuffer data) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createBuffer:(Ljava/util/function/Supplier;ILjava/nio/ByteBuffer;)Lcom/mojang/blaze3d/buffers/GpuBuffer;");
    }

    public List<String> getLastDebugMessages() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.getLastDebugMessages:()Ljava/util/List;");
    }

    public boolean isDebuggingEnabled() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.isDebuggingEnabled:()Z");
    }

    public CompiledRenderPipeline precompilePipeline(RenderPipeline pipeline, ShaderSource shaderSource) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.precompilePipeline:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/shaders/ShaderSource;)Lcom/mojang/blaze3d/pipeline/CompiledRenderPipeline;");
    }

    public void clearPipelineCache() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.clearPipelineCache:()V");
    }

    public void close() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.close:()V");
    }

    public GpuQueryPool createTimestampQueryPool(int size) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.createTimestampQueryPool:(I)Lcom/mojang/blaze3d/systems/GpuQueryPool;");
    }

    protected long getTimestampNow() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.getTimestampNow:()J");
    }

    public DeviceInfo getDeviceInfo() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuDevice.getDeviceInfo:()Lcom/mojang/blaze3d/systems/DeviceInfo;");
    }

    public GpuDevice() {
    }
}
