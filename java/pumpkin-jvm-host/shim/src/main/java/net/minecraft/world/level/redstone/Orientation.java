package net.minecraft.world.level.redstone;

import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class Orientation {

    private Orientation(Direction up, Direction front, Orientation.SideBias sideBias) {
    }

    public static Orientation of(Direction up, Direction front, Orientation.SideBias sideBias) {
        throw Unimplemented.forMember("net/minecraft/world/level/redstone/Orientation.of:(Lnet/minecraft/core/Direction;Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/redstone/Orientation$SideBias;)Lnet/minecraft/world/level/redstone/Orientation;");
    }

    public Direction getSide() {
        throw Unimplemented.forMember("net/minecraft/world/level/redstone/Orientation.getSide:()Lnet/minecraft/core/Direction;");
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

    public Orientation() {
    }
}
