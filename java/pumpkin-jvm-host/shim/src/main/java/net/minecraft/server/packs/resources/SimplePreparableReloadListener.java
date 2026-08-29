package net.minecraft.server.packs.resources;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.util.profiling.ProfilerFiller;
import dev.pumpkin.shim.Unimplemented;

public abstract class SimplePreparableReloadListener<T> extends net.neoforged.neoforge.resource.ContextAwareReloadListener implements PreparableReloadListener {

    public final CompletableFuture<Void> reload(PreparableReloadListener.SharedState currentReload, Executor taskExecutor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor reloadExecutor) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/SimplePreparableReloadListener.reload:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$SharedState;Ljava/util/concurrent/Executor;Lnet/minecraft/server/packs/resources/PreparableReloadListener$PreparationBarrier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    protected abstract T prepare(final ResourceManager manager, final ProfilerFiller profiler);

    protected abstract void apply(final T preparations, final ResourceManager manager, final ProfilerFiller profiler);

    public SimplePreparableReloadListener() {
    }
}
