package net.minecraft.util.thread;

import java.util.List;
import java.util.concurrent.Executor;
import net.minecraft.util.profiling.metrics.MetricSampler;
import net.minecraft.util.profiling.metrics.ProfilerMeasured;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractConsecutiveExecutor<T extends Runnable> implements Runnable, TaskScheduler<T>, ProfilerMeasured {

    public AbstractConsecutiveExecutor(StrictQueue<T> queue, Executor executor, String name) {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.<init>:(Lnet/minecraft/util/thread/StrictQueue;Ljava/util/concurrent/Executor;Ljava/lang/String;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.close:()V");
    }

    public void run() {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.run:()V");
    }

    public void schedule(T task) {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.schedule:(Ljava/lang/Runnable;)V");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.size:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.toString:()Ljava/lang/String;");
    }

    public String name() {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.name:()Ljava/lang/String;");
    }

    public List<MetricSampler> profiledMetrics() {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.profiledMetrics:()Ljava/util/List;");
    }

    private boolean isClosed() {
        throw Unimplemented.forMember("net/minecraft/util/thread/AbstractConsecutiveExecutor.isClosed:()Z");
    }

    private enum Status {

        SLEEPING, RUNNING, CLOSED
    }

    protected AbstractConsecutiveExecutor() {
    }
}
