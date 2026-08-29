package net.minecraft.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum RailShape implements StringRepresentable {

    NORTH_SOUTH,
    EAST_WEST,
    ASCENDING_EAST,
    ASCENDING_WEST,
    ASCENDING_NORTH,
    ASCENDING_SOUTH,
    SOUTH_EAST,
    SOUTH_WEST,
    NORTH_WEST,
    NORTH_EAST;

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/RailShape.getName:()Ljava/lang/String;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/RailShape.toString:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/RailShape.getSerializedName:()Ljava/lang/String;");
    }
}
