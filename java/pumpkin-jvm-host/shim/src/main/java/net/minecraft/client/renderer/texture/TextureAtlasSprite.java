package net.minecraft.client.renderer.texture;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class TextureAtlasSprite implements AutoCloseable {

    protected TextureAtlasSprite(Identifier atlasLocation, SpriteContents contents, int atlasWidth, int atlasHeight, int x, int y, int padding) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.<init>:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/renderer/texture/SpriteContents;IIIII)V");
    }

    public int getX() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.getX:()I");
    }

    public int getY() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.getY:()I");
    }

    public float getU0() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.getU0:()F");
    }

    public float getU1() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.getU1:()F");
    }

    public SpriteContents contents() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.contents:()Lnet/minecraft/client/renderer/texture/SpriteContents;");
    }

    public float getU(float offset) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.getU:(F)F");
    }

    public float getV0() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.getV0:()F");
    }

    public float getV1() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.getV1:()F");
    }

    public float getV(float offset) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.getV:(F)F");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.toString:()Ljava/lang/String;");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlasSprite.close:()V");
    }

    protected TextureAtlasSprite() {
    }
}
