package net.minecraft.network.protocol.login;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundLoginDisconnectPacket(Component reason) implements Packet<ClientLoginPacketListener> {

    public PacketType<ClientboundLoginDisconnectPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginDisconnectPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientLoginPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginDisconnectPacket.handle:(Lnet/minecraft/network/protocol/login/ClientLoginPacketListener;)V");
    }
}
