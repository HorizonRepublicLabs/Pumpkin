package net.minecraft.client;

import it.unimi.dsi.fastutil.floats.FloatUnaryOperator;
import dev.pumpkin.shim.Unimplemented;

public interface DeltaTracker {

    float getGameTimeDeltaTicks();

    float getGameTimeDeltaPartialTick(boolean ignoreFrozenGame);

    float getRealtimeDeltaTicks();

    class DefaultValue implements DeltaTracker {

        private DefaultValue(float value) {
            throw Unimplemented.forMember("net/minecraft/client/DeltaTracker$DefaultValue.<init>:(F)V");
        }

        public float getGameTimeDeltaTicks() {
            throw Unimplemented.forMember("net/minecraft/client/DeltaTracker$DefaultValue.getGameTimeDeltaTicks:()F");
        }

        public float getGameTimeDeltaPartialTick(boolean ignored) {
            throw Unimplemented.forMember("net/minecraft/client/DeltaTracker$DefaultValue.getGameTimeDeltaPartialTick:(Z)F");
        }

        public float getRealtimeDeltaTicks() {
            throw Unimplemented.forMember("net/minecraft/client/DeltaTracker$DefaultValue.getRealtimeDeltaTicks:()F");
        }

        protected DefaultValue() {
        }
    }

    class Timer implements DeltaTracker {

        public Timer(float ticksPerSecond, long currentMs, FloatUnaryOperator targetMsptProvider) {
            throw Unimplemented.forMember("net/minecraft/client/DeltaTracker$Timer.<init>:(FJLit/unimi/dsi/fastutil/floats/FloatUnaryOperator;)V");
        }

        public float getGameTimeDeltaTicks() {
            throw Unimplemented.forMember("net/minecraft/client/DeltaTracker$Timer.getGameTimeDeltaTicks:()F");
        }

        public float getGameTimeDeltaPartialTick(boolean ignoreFrozenGame) {
            throw Unimplemented.forMember("net/minecraft/client/DeltaTracker$Timer.getGameTimeDeltaPartialTick:(Z)F");
        }

        public float getRealtimeDeltaTicks() {
            throw Unimplemented.forMember("net/minecraft/client/DeltaTracker$Timer.getRealtimeDeltaTicks:()F");
        }

        protected Timer() {
        }
    }
}
