package net.minecraft.network.protocol.handshake;

import dev.pumpkin.shim.Unimplemented;

public enum ClientIntent {

    STATUS, LOGIN, TRANSFER;

    public static ClientIntent byId(int id) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/handshake/ClientIntent.byId:(I)Lnet/minecraft/network/protocol/handshake/ClientIntent;");
    }

    public int id() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/handshake/ClientIntent.id:()I");
    }
}
