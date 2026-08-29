package net.minecraft.client.renderer.texture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import dev.pumpkin.shim.Unimplemented;

public class TextureManager implements PreparableReloadListener, AutoCloseable {

    public TextureManager(ResourceManager resourceManager) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureManager.<init>:(Lnet/minecraft/server/packs/resources/ResourceManager;)V");
    }

    public void register(Identifier location, AbstractTexture texture) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureManager.register:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/renderer/texture/AbstractTexture;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureManager.tick:()V");
    }

    public void release(Identifier location) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureManager.release:(Lnet/minecraft/resources/Identifier;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureManager.close:()V");
    }

    public CompletableFuture<Void> reload(PreparableReloadListener.SharedState currentReload, Executor taskExecutor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor reloadExecutor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureManager.reload:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$SharedState;Ljava/util/concurrent/Executor;Lnet/minecraft/server/packs/resources/PreparableReloadListener$PreparationBarrier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    private record PendingReload(ReloadableTexture texture, CompletableFuture<TextureContents> newContents) {
    }

    public TextureManager() {
    }
}
