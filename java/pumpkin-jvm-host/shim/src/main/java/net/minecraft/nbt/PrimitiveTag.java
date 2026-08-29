package net.minecraft.nbt;

import dev.pumpkin.shim.Unimplemented;

public interface PrimitiveTag extends Tag {

    default Tag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/PrimitiveTag.copy:()Lnet/minecraft/nbt/Tag;");
    }
}
