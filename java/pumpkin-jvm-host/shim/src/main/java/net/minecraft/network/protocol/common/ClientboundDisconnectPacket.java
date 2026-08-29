package net.minecraft.network.protocol.common;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundDisconnectPacket(Component reason) implements Packet<ClientCommonPacketListener> {

    public PacketType<ClientboundDisconnectPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundDisconnectPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundDisconnectPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
