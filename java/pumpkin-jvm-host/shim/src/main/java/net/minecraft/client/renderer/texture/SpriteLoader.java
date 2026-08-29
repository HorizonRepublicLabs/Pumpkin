package net.minecraft.client.renderer.texture;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class SpriteLoader {

    public SpriteLoader(Identifier location, int maxSupportedTextureSize) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteLoader.<init>:(Lnet/minecraft/resources/Identifier;I)V");
    }

    public static SpriteLoader create(TextureAtlas atlas) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteLoader.create:(Lnet/minecraft/client/renderer/texture/TextureAtlas;)Lnet/minecraft/client/renderer/texture/SpriteLoader;");
    }

    public record Preparations(int width, int height, int mipLevel, TextureAtlasSprite missing, Map<Identifier, TextureAtlasSprite> regions, CompletableFuture<Void> readyForUpload) {
    }

    public SpriteLoader() {
    }
}
