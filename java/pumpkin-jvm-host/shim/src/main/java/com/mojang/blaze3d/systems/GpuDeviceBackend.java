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

public interface GpuDeviceBackend {

    GpuSurfaceBackend createSurface(long windowHandle);

    CommandEncoderBackend createCommandEncoder();

    GpuSampler createSampler(AddressMode addressModeU, AddressMode addressModeV, FilterMode minFilter, FilterMode magFilter, int maxAnisotropy, OptionalDouble maxLod);

    GpuTexture createTexture(Supplier<String> label, int usage, GpuFormat format, int width, int height, int depthOrLayers, int mipLevels);

    GpuTexture createTexture(String label, int usage, GpuFormat format, int width, int height, int depthOrLayers, int mipLevels);

    GpuTextureView createTextureView(GpuTexture texture);

    GpuTextureView createTextureView(GpuTexture texture, int baseMipLevel, int mipLevels);

    GpuBuffer createBuffer(Supplier<String> label, int usage, long size);

    GpuBuffer createBuffer(Supplier<String> label, int usage, ByteBuffer data);

    List<String> getLastDebugMessages();

    boolean isDebuggingEnabled();

    CompiledRenderPipeline precompilePipeline(RenderPipeline pipeline, ShaderSource shaderSource);

    void clearPipelineCache();

    void close();

    GpuQueryPool createTimestampQueryPool(int size);

    long getTimestampNow();

    DeviceInfo getDeviceInfo();
}
