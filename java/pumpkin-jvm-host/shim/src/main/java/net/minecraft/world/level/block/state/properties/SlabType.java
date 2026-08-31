package net.minecraft.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum SlabType implements StringRepresentable {

    TOP, BOTTOM, DOUBLE;

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/SlabType.toString:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/SlabType.getSerializedName:()Ljava/lang/String;");
    }
}
