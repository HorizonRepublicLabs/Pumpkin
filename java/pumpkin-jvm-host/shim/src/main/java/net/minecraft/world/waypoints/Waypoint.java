package net.minecraft.world.waypoints;

import java.util.Optional;
import net.minecraft.resources.ResourceKey;

public interface Waypoint {

    class Icon {

        public Icon() {
        }

        private Icon(ResourceKey<WaypointStyleAsset> style, Optional<Integer> color) {
        }
    }
}
