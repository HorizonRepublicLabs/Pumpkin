package net.minecraft.world.waypoints;

import java.util.Optional;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public interface Waypoint {

    class Icon {

        public Icon() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/Waypoint$Icon.<init>:()V");
        }

        private Icon(ResourceKey<WaypointStyleAsset> style, Optional<Integer> color) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/Waypoint$Icon.<init>:(Lnet/minecraft/resources/ResourceKey;Ljava/util/Optional;)V");
        }
    }
}
