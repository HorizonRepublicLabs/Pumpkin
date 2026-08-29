package net.minecraft.world.clock;

import java.util.Optional;
import net.minecraft.core.Holder;

public record ClockTimeMarker(Holder<WorldClock> clock, int ticks, Optional<Integer> periodTicks, boolean showInCommands) {
}
