package net.neoforged.neoforge.common;

import dev.pumpkin.shim.Unimplemented;

public final class ItemAbility {

    public static ItemAbility get(String name) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ItemAbility.get:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ItemAbility;");
    }

    public String name() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ItemAbility.name:()Ljava/lang/String;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ItemAbility.toString:()Ljava/lang/String;");
    }

    private ItemAbility(String name) {
    }

    public ItemAbility() {
    }
}
