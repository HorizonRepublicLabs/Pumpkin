package net.minecraft.world.level.redstone;

import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class Orientation {

    private Orientation(Direction up, Direction front, Orientation.SideBias sideBias) {
        throw Unimplemented.forMember("net/minecraft/world/level/redstone/Orientation.<init>:(Lnet/minecraft/core/Direction;Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/redstone/Orientation$SideBias;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/redstone/Orientation.toString:()Ljava/lang/String;");
    }

    public enum SideBias {

        LEFT, RIGHT;

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/redstone/Orientation$SideBias.toString:()Ljava/lang/String;");
        }
    }

    protected Orientation() {
    }
}
