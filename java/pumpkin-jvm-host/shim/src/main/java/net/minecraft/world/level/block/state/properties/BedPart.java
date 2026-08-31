package net.minecraft.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum BedPart implements StringRepresentable {

    HEAD, FOOT;

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BedPart.toString:()Ljava/lang/String;");
    }

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getSerializedName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }
}
