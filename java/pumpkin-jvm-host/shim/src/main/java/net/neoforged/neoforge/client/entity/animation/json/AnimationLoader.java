package net.neoforged.neoforge.client.entity.animation.json;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.neoforged.neoforge.resource.ContextAwareReloadListener;
import dev.pumpkin.shim.Unimplemented;

public final class AnimationLoader extends ContextAwareReloadListener implements PreparableReloadListener {

    protected AnimationLoader() {
    }

    public void prepareSharedState(SharedState sharedState) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/entity/animation/json/AnimationLoader.prepareSharedState:(Lnet/neoforged/neoforge/client/entity/animation/json/SharedState;)V");
    }

    public CompletableFuture<Void> reload(SharedState sharedState, Executor prepareExecutor, PreparationBarrier barrier, Executor applyExecutor) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/entity/animation/json/AnimationLoader.reload:(Lnet/neoforged/neoforge/client/entity/animation/json/SharedState;Ljava/util/concurrent/Executor;Lnet/neoforged/neoforge/client/entity/animation/json/PreparationBarrier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    private void apply(Map<Identifier, AnimationDefinition> animationJsons) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/entity/animation/json/AnimationLoader.apply:(Ljava/util/Map;)V");
    }

    public static final class PendingAnimations {

        protected PendingAnimations() {
        }

        public AnimationDefinition get(Identifier id) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/entity/animation/json/AnimationLoader$PendingAnimations.get:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/animation/AnimationDefinition;");
        }
    }
}
