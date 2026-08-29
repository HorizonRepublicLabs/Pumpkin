package net.minecraft.server.packs.resources;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import dev.pumpkin.shim.Unimplemented;

public interface PreparableReloadListener {

    CompletableFuture<Void> reload(PreparableReloadListener.SharedState currentReload, Executor taskExecutor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor reloadExecutor);

    default String getName() {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/PreparableReloadListener.getName:()Ljava/lang/String;");
    }

    interface PreparationBarrier {

        <T> CompletableFuture<T> wait(T t);
    }

    final class SharedState {

        public SharedState(ResourceManager manager) {
        }

        public ResourceManager resourceManager() {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/PreparableReloadListener$SharedState.resourceManager:()Lnet/minecraft/server/packs/resources/ResourceManager;");
        }

        public <T> void set(PreparableReloadListener.StateKey<T> key, T value) {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/PreparableReloadListener$SharedState.set:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$StateKey;Ljava/lang/Object;)V");
        }

        public <T> T get(PreparableReloadListener.StateKey<T> key) {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/PreparableReloadListener$SharedState.get:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$StateKey;)Ljava/lang/Object;");
        }

        protected SharedState() {
        }
    }

    final class StateKey<T> {

        protected StateKey() {
        }
    }
}
