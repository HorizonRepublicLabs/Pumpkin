package net.minecraft.util.thread;

import java.util.List;
import java.util.concurrent.Executor;
import net.minecraft.util.profiling.metrics.MetricSampler;
import net.minecraft.util.profiling.metrics.ProfilerMeasured;
import dev.pumpkin.shim.Unimplemented;

public abstract class BlockableEventLoop<R extends Runnable> implements Executor, TaskScheduler<R>, ProfilerMeasured {

    protected BlockableEventLoop(String name, boolean propagatesCrashes) {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.<init>:(Ljava/lang/String;Z)V");
    }

    protected abstract boolean shouldRun(final R task);

    protected abstract Thread getRunningThread();

    public String name() {
        throw Unimplemented.forMember("net/minecraft/util/thread/BlockableEventLoop.name:()Ljava/lang/String;");
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

    protected BlockableEventLoop() {
    }
}
