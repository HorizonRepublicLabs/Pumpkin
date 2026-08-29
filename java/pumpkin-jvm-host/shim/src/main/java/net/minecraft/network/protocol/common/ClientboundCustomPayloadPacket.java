package net.minecraft.network.protocol.common;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundCustomPayloadPacket(CustomPacketPayload payload) implements Packet<ClientCommonPacketListener> {

    public PacketType<ClientboundCustomPayloadPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundCustomPayloadPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundCustomPayloadPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
