package net.minecraft.world.timeline;

import java.util.Map;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.EnvironmentAttribute;
import net.minecraft.world.clock.ClockManager;
import net.minecraft.world.clock.ClockTimeMarker;
import net.minecraft.world.clock.WorldClock;
import dev.pumpkin.shim.Unimplemented;

public class Timeline {

    private Timeline(Holder<WorldClock> clock, Optional<Integer> periodTicks, Map<EnvironmentAttribute<?>, AttributeTrack<?, ?>> tracks, Map<ResourceKey<ClockTimeMarker>, Timeline.TimeMarkerInfo> timeMarkers) {
        throw Unimplemented.forMember("net/minecraft/world/timeline/Timeline.<init>:(Lnet/minecraft/core/Holder;Ljava/util/Optional;Ljava/util/Map;Ljava/util/Map;)V");
    }

    public long getTotalTicks(ClockManager clockManager) {
        throw Unimplemented.forMember("net/minecraft/world/timeline/Timeline.getTotalTicks:(Lnet/minecraft/world/clock/ClockManager;)J");
    }

    public static class Builder {

        private Builder(Holder<WorldClock> clock) {
            throw Unimplemented.forMember("net/minecraft/world/timeline/Timeline$Builder.<init>:(Lnet/minecraft/core/Holder;)V");
        }

        public Timeline build() {
            throw Unimplemented.forMember("net/minecraft/world/timeline/Timeline$Builder.build:()Lnet/minecraft/world/timeline/Timeline;");
        }

        protected Builder() {
        }
    }

    private record TimeMarkerInfo(int ticks, boolean showInCommands) {
    }

    protected Timeline() {
    }
}
