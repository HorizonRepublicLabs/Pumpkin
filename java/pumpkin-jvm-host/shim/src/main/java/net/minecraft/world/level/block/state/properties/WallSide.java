package net.minecraft.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

// Pumpkin divergence: vanilla's constants in vanilla's order. States are numbered by the
// order a property's values are declared, so this order is part of the wire format.
public enum WallSide implements StringRepresentable {

    NONE, LOW, TALL;

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getSerializedName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }
}
