package com.mojang.blaze3d.textures;

import dev.pumpkin.shim.Unimplemented;

public abstract class GpuTextureView implements AutoCloseable {

    protected GpuTextureView(GpuTexture texture, int baseMipLevel, int mipLevels) {
        throw Unimplemented.forMember("com/mojang/blaze3d/textures/GpuTextureView.<init>:(Lcom/mojang/blaze3d/textures/GpuTexture;II)V");
    }

    public abstract void close();

    public int getWidth(int mipLevel) {
        throw Unimplemented.forMember("com/mojang/blaze3d/textures/GpuTextureView.getWidth:(I)I");
    }

    public int getHeight(int mipLevel) {
        throw Unimplemented.forMember("com/mojang/blaze3d/textures/GpuTextureView.getHeight:(I)I");
    }

    public abstract boolean isClosed();

    protected GpuTextureView() {
    }
}
