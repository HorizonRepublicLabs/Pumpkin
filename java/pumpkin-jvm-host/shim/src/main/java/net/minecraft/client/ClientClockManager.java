package net.minecraft.client;

import net.minecraft.core.Holder;
import net.minecraft.world.clock.ClockManager;
import net.minecraft.world.clock.WorldClock;
import dev.pumpkin.shim.Unimplemented;

public class ClientClockManager implements ClockManager {

    public long getTotalTicks(Holder<WorldClock> definition) {
        throw Unimplemented.forMember("net/minecraft/client/ClientClockManager.getTotalTicks:(Lnet/minecraft/core/Holder;)J");
    }

    public float getPartialTick(Holder<WorldClock> definition) {
        throw Unimplemented.forMember("net/minecraft/client/ClientClockManager.getPartialTick:(Lnet/minecraft/core/Holder;)F");
    }

    private static class ClockInstance {

        protected ClockInstance() {
        }
    }

    protected ClientClockManager() {
    }
}
