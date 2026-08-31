package net.minecraft.util;

import dev.pumpkin.shim.Unimplemented;

public enum TriState implements StringRepresentable {

    TRUE, FALSE, DEFAULT;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/util/TriState.getSerializedName:()Ljava/lang/String;");
    }
}
