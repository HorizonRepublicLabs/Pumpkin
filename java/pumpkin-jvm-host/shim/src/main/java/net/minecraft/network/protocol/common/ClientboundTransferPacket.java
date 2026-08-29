package net.minecraft.network.protocol.common;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundTransferPacket(String host, int port) implements Packet<ClientCommonPacketListener> {

    private ClientboundTransferPacket(FriendlyByteBuf input) {
        this((String) null, (int) 0);
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundTransferPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundTransferPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundTransferPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundTransferPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundTransferPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
