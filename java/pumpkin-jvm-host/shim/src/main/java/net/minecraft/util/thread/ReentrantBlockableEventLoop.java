package net.minecraft.util.thread;

import dev.pumpkin.shim.Unimplemented;

public abstract class ReentrantBlockableEventLoop<R extends Runnable> extends BlockableEventLoop<R> {

    public ReentrantBlockableEventLoop(String name, boolean propagatesCrashes) {
    }

    protected boolean scheduleExecutables() {
        throw Unimplemented.forMember("net/minecraft/util/thread/ReentrantBlockableEventLoop.scheduleExecutables:()Z");
    }

    protected void doRunTask(R task) {
        throw Unimplemented.forMember("net/minecraft/util/thread/ReentrantBlockableEventLoop.doRunTask:(Ljava/lang/Runnable;)V");
    }

    public ReentrantBlockableEventLoop() {
    }
}
