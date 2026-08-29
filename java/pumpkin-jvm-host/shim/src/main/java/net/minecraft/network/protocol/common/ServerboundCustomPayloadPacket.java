package net.minecraft.network.protocol.common;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundCustomPayloadPacket(CustomPacketPayload payload) implements Packet<ServerCommonPacketListener> {

    public PacketType<ServerboundCustomPayloadPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundCustomPayloadPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundCustomPayloadPacket.handle:(Lnet/minecraft/network/protocol/common/ServerCommonPacketListener;)V");
    }
}
