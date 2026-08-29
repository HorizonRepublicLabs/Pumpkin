package net.minecraft.client.resources;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.authlib.properties.Property;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.client.renderer.texture.SkinTextureDownloader;
import net.minecraft.resources.Identifier;
import net.minecraft.server.Services;
import net.minecraft.world.entity.player.PlayerSkin;
import dev.pumpkin.shim.Unimplemented;

public class SkinManager {

    public SkinManager(Path skinsDirectory, Services services, SkinTextureDownloader skinTextureDownloader, Executor mainThreadExecutor) {
        throw Unimplemented.forMember("net/minecraft/client/resources/SkinManager.<init>:(Ljava/nio/file/Path;Lnet/minecraft/server/Services;Lnet/minecraft/client/renderer/texture/SkinTextureDownloader;Ljava/util/concurrent/Executor;)V");
    }

    public CompletableFuture<Optional<PlayerSkin>> get(GameProfile profile) {
        throw Unimplemented.forMember("net/minecraft/client/resources/SkinManager.get:(Lcom/mojang/authlib/GameProfile;)Ljava/util/concurrent/CompletableFuture;");
    }

    private record CacheKey(UUID profileId, Property packedTextures) {
    }

    private class TextureCache {

        private TextureCache(Path root, Type type) {
            throw Unimplemented.forMember("net/minecraft/client/resources/SkinManager$TextureCache.<init>:(Ljava/nio/file/Path;Lcom/mojang/authlib/minecraft/MinecraftProfileTexture$Type;)V");
        }

        private Identifier getTextureLocation(String textureHash) {
            throw Unimplemented.forMember("net/minecraft/client/resources/SkinManager$TextureCache.getTextureLocation:(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
        }

        protected TextureCache() {
        }
    }

    public SkinManager() {
    }
}
