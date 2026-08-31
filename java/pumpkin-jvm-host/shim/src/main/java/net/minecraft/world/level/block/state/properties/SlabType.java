package net.minecraft.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum SlabType implements StringRepresentable {

    TOP, BOTTOM, DOUBLE;

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/SlabType.toString:()Ljava/lang/String;");
    }

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getSerializedName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }
}
