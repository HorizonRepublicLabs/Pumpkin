package net.minecraft.client.renderer;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.textures.GpuTextureView;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.server.players.ProfileResolver;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.item.component.ResolvableProfile;
import dev.pumpkin.shim.Unimplemented;

public class PlayerSkinRenderCache {

    public PlayerSkinRenderCache(TextureManager textureManager, SkinManager skinManager, ProfileResolver profileResolver) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/PlayerSkinRenderCache.<init>:(Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/resources/SkinManager;Lnet/minecraft/server/players/ProfileResolver;)V");
    }

    public CompletableFuture<Optional<PlayerSkinRenderCache.RenderInfo>> lookup(ResolvableProfile profile) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/PlayerSkinRenderCache.lookup:(Lnet/minecraft/world/item/component/ResolvableProfile;)Ljava/util/concurrent/CompletableFuture;");
    }

    public final class RenderInfo {

        public RenderInfo(GameProfile gameProfile, PlayerSkin playerSkin, PlayerSkin.Patch patch) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/PlayerSkinRenderCache$RenderInfo.<init>:(Lcom/mojang/authlib/GameProfile;Lnet/minecraft/world/entity/player/PlayerSkin;Lnet/minecraft/world/entity/player/PlayerSkin$Patch;)V");
        }

        public GpuTextureView textureView() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/PlayerSkinRenderCache$RenderInfo.textureView:()Lcom/mojang/blaze3d/textures/GpuTextureView;");
        }

        public boolean equals(Object o) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/PlayerSkinRenderCache$RenderInfo.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/PlayerSkinRenderCache$RenderInfo.hashCode:()I");
        }

        protected RenderInfo() {
        }
    }

    protected PlayerSkinRenderCache() {
    }
}
