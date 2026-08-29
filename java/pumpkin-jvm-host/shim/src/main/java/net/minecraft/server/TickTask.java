package net.minecraft.server;

import dev.pumpkin.shim.Unimplemented;

public class TickTask implements Runnable {

    public TickTask(int tick, Runnable runnable) {
    }

    public void run() {
        throw Unimplemented.forMember("net/minecraft/server/TickTask.run:()V");
    }

    public TickTask() {
    }
}
