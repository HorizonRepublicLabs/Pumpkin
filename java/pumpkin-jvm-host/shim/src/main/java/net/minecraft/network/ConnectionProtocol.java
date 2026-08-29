package net.minecraft.network;

import dev.pumpkin.shim.Unimplemented;

public enum ConnectionProtocol {

    HANDSHAKING, PLAY, STATUS, LOGIN, CONFIGURATION;

    public String id() {
        throw Unimplemented.forMember("net/minecraft/network/ConnectionProtocol.id:()Ljava/lang/String;");
    }
}
