package net.minecraft.util.thread;

import java.util.concurrent.Executor;
import dev.pumpkin.shim.Unimplemented;

public class ConsecutiveExecutor extends AbstractConsecutiveExecutor<Runnable> {

    public ConsecutiveExecutor(Executor dispatcher, String name) {
    }

    public Runnable wrapRunnable(Runnable runnable) {
        throw Unimplemented.forMember("net/minecraft/util/thread/ConsecutiveExecutor.wrapRunnable:(Ljava/lang/Runnable;)Ljava/lang/Runnable;");
    }

    public ConsecutiveExecutor() {
    }
}
