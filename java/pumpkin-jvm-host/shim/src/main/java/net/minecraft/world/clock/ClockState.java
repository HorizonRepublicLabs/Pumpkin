package net.minecraft.world.clock;

public record ClockState(long totalTicks, float partialTick, float rate, boolean paused) {
}
