package net.minecraft.client.renderer.texture;

import java.io.IOException;
import java.nio.file.Path;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class TextureAtlas extends AbstractTexture implements TickableTexture, Dumpable {

    public static final Identifier LOCATION_BLOCKS = null;

    public static final Identifier LOCATION_ITEMS = null;

    private TextureAtlasSprite missingSprite;

    private final Identifier location = null;

    public TextureAtlas(Identifier location) {
    }

    public void dumpContents(Identifier selfId, Path dir) throws IOException {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.dumpContents:(Lnet/minecraft/resources/Identifier;Ljava/nio/file/Path;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.tick:()V");
    }

    public TextureAtlasSprite getSprite(Identifier location) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.getSprite:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;");
    }

    public TextureAtlasSprite missingSprite() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.missingSprite:()Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;");
    }

    protected void releaseTextures() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.releaseTextures:()V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.close:()V");
    }

    public Identifier location() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.location:()Lnet/minecraft/resources/Identifier;");
    }

    int getWidth() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.getWidth:()I");
    }

    int getHeight() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureAtlas.getHeight:()I");
    }

    public TextureAtlas() {
    }
}
