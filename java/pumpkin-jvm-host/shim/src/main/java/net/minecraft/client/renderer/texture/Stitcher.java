package net.minecraft.client.renderer.texture;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class Stitcher<T extends Stitcher.Entry> {

    public Stitcher(int maxWidth, int maxHeight, int mipLevel, int anisotropyBit) {
    }

    public int getWidth() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/Stitcher.getWidth:()I");
    }

    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/Stitcher.getHeight:()I");
    }

    public interface Entry {

        int width();

        int height();

        Identifier name();
    }

    private record Holder<T extends Stitcher.Entry>(T entry, int width, int height) {
    }

    public static class Region<T extends Stitcher.Entry> {

        public Region(int originX, int originY, int width, int height) {
        }

        public int getX() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/texture/Stitcher$Region.getX:()I");
        }

        public int getY() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/texture/Stitcher$Region.getY:()I");
        }

        public boolean add(Stitcher.Holder<T> holder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/texture/Stitcher$Region.add:(Lnet/minecraft/client/renderer/texture/Stitcher$Holder;)Z");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/texture/Stitcher$Region.toString:()Ljava/lang/String;");
        }

        public Region() {
        }
    }

    public interface SpriteLoader<T extends Stitcher.Entry> {

        void load(T entry, int x, int z, int padding);
    }

    public Stitcher() {
    }
}
