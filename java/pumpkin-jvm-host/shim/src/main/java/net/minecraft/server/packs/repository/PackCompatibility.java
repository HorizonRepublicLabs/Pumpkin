package net.minecraft.server.packs.repository;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public enum PackCompatibility {

    TOO_OLD, TOO_NEW, UNKNOWN, COMPATIBLE;

    public Component getDescription() {
        throw Unimplemented.forMember("net/minecraft/server/packs/repository/PackCompatibility.getDescription:()Lnet/minecraft/network/chat/Component;");
    }
}
