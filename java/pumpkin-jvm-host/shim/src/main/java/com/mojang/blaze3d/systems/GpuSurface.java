package com.mojang.blaze3d.systems;

import com.mojang.blaze3d.textures.GpuTextureView;
import java.util.Collection;
import dev.pumpkin.shim.Unimplemented;

public class GpuSurface implements AutoCloseable {

    public GpuSurface(GpuSurfaceBackend backend) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuSurface.<init>:(Lcom/mojang/blaze3d/systems/GpuSurfaceBackend;)V");
    }

    public void close() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuSurface.close:()V");
    }

    public void configure(GpuSurface.Configuration config) throws SurfaceException {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuSurface.configure:(Lcom/mojang/blaze3d/systems/GpuSurface$Configuration;)V");
    }

    public Collection<GpuSurface.PresentMode> supportedPresentModes() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuSurface.supportedPresentModes:()Ljava/util/Collection;");
    }

    public boolean isSuboptimal() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuSurface.isSuboptimal:()Z");
    }

    public void acquireNextTexture() throws SurfaceException {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuSurface.acquireNextTexture:()V");
    }

    public void blitFromTexture(CommandEncoder commandEncoder, GpuTextureView textureView) {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuSurface.blitFromTexture:(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;)V");
    }

    public void present() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/GpuSurface.present:()V");
    }

    public record Configuration(int width, int height, GpuSurface.PresentMode presentMode) {
    }

    public enum PresentMode {

        IMMEDIATE, MAILBOX, FIFO, FIFO_RELAXED
    }

    protected GpuSurface() {
    }
}
