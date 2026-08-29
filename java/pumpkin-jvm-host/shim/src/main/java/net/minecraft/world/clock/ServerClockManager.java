package net.minecraft.world.clock;

import net.minecraft.core.Holder;
import net.minecraft.world.level.saveddata.SavedData;
import dev.pumpkin.shim.Unimplemented;

public class ServerClockManager extends SavedData implements ClockManager {

    private ServerClockManager(PackedClockStates packedClockStates) {
        throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager.<init>:(Lnet/minecraft/world/clock/PackedClockStates;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager.tick:()V");
    }

    public void setRate(Holder<WorldClock> clock, float rate) {
        throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager.setRate:(Lnet/minecraft/core/Holder;F)V");
    }

    public long getTotalTicks(Holder<WorldClock> definition) {
        throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager.getTotalTicks:(Lnet/minecraft/core/Holder;)J");
    }

    public float getPartialTick(Holder<WorldClock> definition) {
        throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager.getPartialTick:(Lnet/minecraft/core/Holder;)F");
    }

    private long getGameTime() {
        throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager.getGameTime:()J");
    }

    private static class ClockInstance {

        public ClockInstance(Holder<WorldClock> holder) {
            throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager$ClockInstance.<init>:(Lnet/minecraft/core/Holder;)V");
        }

        public ClockInstance() {
            throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager$ClockInstance.<init>:()V");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/clock/ServerClockManager$ClockInstance.tick:()V");
        }
    }

    public ServerClockManager() {
    }
}
