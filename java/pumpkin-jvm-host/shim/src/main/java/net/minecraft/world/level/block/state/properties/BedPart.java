package net.minecraft.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum BedPart implements StringRepresentable {

    HEAD, FOOT;

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BedPart.toString:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BedPart.getSerializedName:()Ljava/lang/String;");
    }
}
