package net.minecraft.world.clock;

import java.util.Map;
import net.minecraft.core.Holder;

public record PackedClockStates(Map<Holder<WorldClock>, ClockState> clocks) {
}
