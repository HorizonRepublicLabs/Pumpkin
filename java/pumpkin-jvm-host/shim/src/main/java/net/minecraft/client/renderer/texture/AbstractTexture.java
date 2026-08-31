package net.minecraft.client.renderer.texture;

import com.mojang.blaze3d.textures.GpuSampler;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractTexture implements AutoCloseable {

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/AbstractTexture.close:()V");
    }

    public GpuTextureView getTextureView() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/AbstractTexture.getTextureView:()Lcom/mojang/blaze3d/textures/GpuTextureView;");
    }

    public GpuSampler getSampler() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/AbstractTexture.getSampler:()Lcom/mojang/blaze3d/textures/GpuSampler;");
    }

    public AbstractTexture() {
    }
}
