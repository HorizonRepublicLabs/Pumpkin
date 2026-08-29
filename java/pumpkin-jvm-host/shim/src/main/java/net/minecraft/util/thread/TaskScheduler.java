package net.minecraft.util.thread;

import dev.pumpkin.shim.Unimplemented;

public interface TaskScheduler<R extends Runnable> extends AutoCloseable {

    String name();

    void schedule(final R r);

    default void close() {
        throw Unimplemented.forMember("net/minecraft/util/thread/TaskScheduler.close:()V");
    }

    R wrapRunnable(final Runnable runnable);
}
