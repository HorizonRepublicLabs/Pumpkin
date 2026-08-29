package com.mojang.blaze3d.platform;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.lwjgl.stb.STBIWriteCallback;
import dev.pumpkin.shim.Unimplemented;

public final class NativeImage implements AutoCloseable {

    public NativeImage(int width, int height, boolean zero) {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.<init>:(IIZ)V");
    }

    public NativeImage(NativeImage.Format format, int width, int height, boolean zero) {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.<init>:(Lcom/mojang/blaze3d/platform/NativeImage$Format;IIZ)V");
    }

    public NativeImage(NativeImage.Format format, int width, int height, boolean useStbFree, long pixels) {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.<init>:(Lcom/mojang/blaze3d/platform/NativeImage$Format;IIZJ)V");
    }

    public String toString() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.toString:()Ljava/lang/String;");
    }

    public static NativeImage read(InputStream inputStream) throws IOException {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.read:(Ljava/io/InputStream;)Lcom/mojang/blaze3d/platform/NativeImage;");
    }

    public static NativeImage read(NativeImage.Format format, InputStream inputStream) throws IOException {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.read:(Lcom/mojang/blaze3d/platform/NativeImage$Format;Ljava/io/InputStream;)Lcom/mojang/blaze3d/platform/NativeImage;");
    }

    public static NativeImage read(ByteBuffer bytes) throws IOException {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.read:(Ljava/nio/ByteBuffer;)Lcom/mojang/blaze3d/platform/NativeImage;");
    }

    public static NativeImage read(byte[] bytes) throws IOException {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.read:([B)Lcom/mojang/blaze3d/platform/NativeImage;");
    }

    public static NativeImage read(NativeImage.Format format, ByteBuffer bytes) throws IOException {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.read:(Lcom/mojang/blaze3d/platform/NativeImage$Format;Ljava/nio/ByteBuffer;)Lcom/mojang/blaze3d/platform/NativeImage;");
    }

    public void close() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.close:()V");
    }

    public boolean isClosed() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.isClosed:()Z");
    }

    public int getWidth() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.getWidth:()I");
    }

    public int getHeight() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.getHeight:()I");
    }

    public NativeImage.Format format() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage.format:()Lcom/mojang/blaze3d/platform/NativeImage$Format;");
    }

    public enum Format {

        RGBA, RGB, LUMINANCE_ALPHA, LUMINANCE;

        public int components() {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage$Format.components:()I");
        }
    }

    private static class WriteCallback extends STBIWriteCallback {

        private WriteCallback(WritableByteChannel output) {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage$WriteCallback.<init>:(Ljava/nio/channels/WritableByteChannel;)V");
        }

        public void invoke(long context, long data, int size) {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage$WriteCallback.invoke:(JJI)V");
        }

        public void throwIfException() throws IOException {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/NativeImage$WriteCallback.throwIfException:()V");
        }

        protected WriteCallback() {
        }
    }

    protected NativeImage() {
    }
}
