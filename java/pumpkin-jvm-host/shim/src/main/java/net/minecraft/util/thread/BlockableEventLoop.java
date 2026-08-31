package net.minecraft.util.thread;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import net.minecraft.util.profiling.metrics.MetricSampler;
import net.minecraft.util.profiling.metrics.ProfilerMeasured;
import dev.pumpkin.shim.Unimplemented;

public abstract class BlockableEventLoop<R extends Runnable> implements Executor, TaskScheduler<R>, ProfilerMeasured {

    protected BlockableEventLoop(String name, boolean propagatesCrashes) {
    }

    protected abstract boolean shouldRun(final R task);

    public boolean isSameThread() {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.isSameThread:()Z");
    }

    protected abstract Thread getRunningThread();

    public String name() {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.name:()Ljava/lang/String;");
    }

    public <V> CompletableFuture<V> submit(Supplier<V> supplier) {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.submit:(Ljava/util/function/Supplier;)Ljava/util/concurrent/CompletableFuture;");
    }

    public CompletableFuture<Void> submit(Runnable runnable) {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/CompletableFuture;");
    }

    public void schedule(R r) {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.schedule:(Ljava/lang/Runnable;)V");
    }

    public void execute(Runnable command) {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.execute:(Ljava/lang/Runnable;)V");
    }

    public List<MetricSampler> profiledMetrics() {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.profiledMetrics:()Ljava/util/List;");
    }

    public BlockableEventLoop() {
    }
}
