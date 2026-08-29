package net.minecraft.world.clock;

public record ClockNetworkState(long totalTicks, float partialTick, float rate) {
}
