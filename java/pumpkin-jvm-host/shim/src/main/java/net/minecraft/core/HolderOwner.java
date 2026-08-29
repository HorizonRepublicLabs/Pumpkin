package net.minecraft.core;

import dev.pumpkin.shim.Unimplemented;

public interface HolderOwner<T> {

    default boolean canSerializeIn(HolderOwner<T> context) {
        throw Unimplemented.forMember("net/minecraft/core/HolderOwner.canSerializeIn:(Lnet/minecraft/core/HolderOwner;)Z");
    }
}
