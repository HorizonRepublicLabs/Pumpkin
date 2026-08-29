package net.minecraft.server.packs.resources;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import dev.pumpkin.shim.Unimplemented;

public interface ResourceManagerReloadListener extends PreparableReloadListener {

    default CompletableFuture<Void> reload(PreparableReloadListener.SharedState currentReload, Executor taskExecutor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor reloadExecutor) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceManagerReloadListener.reload:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$SharedState;Ljava/util/concurrent/Executor;Lnet/minecraft/server/packs/resources/PreparableReloadListener$PreparationBarrier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    void onResourceManagerReload(ResourceManager resourceManager);
}
