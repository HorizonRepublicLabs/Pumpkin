package net.minecraft.world;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public interface Nameable {

    Component getName();

    default String getPlainTextName() {
        throw Unimplemented.forMember("net/minecraft/world/Nameable.getPlainTextName:()Ljava/lang/String;");
    }

    default boolean hasCustomName() {
        throw Unimplemented.forMember("net/minecraft/world/Nameable.hasCustomName:()Z");
    }

    default Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/Nameable.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    default Component getCustomName() {
        throw Unimplemented.forMember("net/minecraft/world/Nameable.getCustomName:()Lnet/minecraft/network/chat/Component;");
    }
}
