package net.minecraft.server.packs.repository;

import dev.pumpkin.shim.Unimplemented;

public record KnownPack(String namespace, String id, String version) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/server/packs/repository/KnownPack.toString:()Ljava/lang/String;");
    }
}
