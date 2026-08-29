package net.minecraft.world.entity;

import java.util.Set;
import dev.pumpkin.shim.Unimplemented;

public enum Relative {

    X,
    Y,
    Z,
    Y_ROT,
    X_ROT,
    DELTA_X,
    DELTA_Y,
    DELTA_Z,
    ROTATE_DELTA;

    public static Set<Relative> unpack(int value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Relative.unpack:(I)Ljava/util/Set;");
    }

    public static int pack(Set<Relative> set) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Relative.pack:(Ljava/util/Set;)I");
    }
}
