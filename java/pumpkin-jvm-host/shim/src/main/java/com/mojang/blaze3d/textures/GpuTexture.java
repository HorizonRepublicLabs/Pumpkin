package com.mojang.blaze3d.textures;

import com.mojang.blaze3d.GpuFormat;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import dev.pumpkin.shim.Unimplemented;

public abstract class GpuTexture implements AutoCloseable {

    public GpuTexture(int usage, String label, GpuFormat format, int width, int height, int depthOrLayers, int mipLevels) {
    }

    public int getWidth(int mipLevel) {
        throw Unimplemented.forMember("com/mojang/blaze3d/textures/GpuTexture.getWidth:(I)I");
    }

    public int getHeight(int mipLevel) {
        throw Unimplemented.forMember("com/mojang/blaze3d/textures/GpuTexture.getHeight:(I)I");
    }

    public abstract void close();

    public abstract boolean isClosed();

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface Usage {
    }

    public GpuTexture() {
    }
}
