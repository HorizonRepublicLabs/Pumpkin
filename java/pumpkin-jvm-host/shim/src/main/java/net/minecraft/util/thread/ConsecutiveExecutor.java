package net.minecraft.util.thread;

import java.util.concurrent.Executor;
import dev.pumpkin.shim.Unimplemented;

public class ConsecutiveExecutor extends AbstractConsecutiveExecutor<Runnable> {

    public ConsecutiveExecutor(Executor dispatcher, String name) {
        throw Unimplemented.forMember("net/minecraft/util/thread/ConsecutiveExecutor.<init>:(Ljava/util/concurrent/Executor;Ljava/lang/String;)V");
    }

    public Runnable wrapRunnable(Runnable runnable) {
        throw Unimplemented.forMember("net/minecraft/util/thread/ConsecutiveExecutor.wrapRunnable:(Ljava/lang/Runnable;)Ljava/lang/Runnable;");
    }

    public ConsecutiveExecutor() {
    }
}
