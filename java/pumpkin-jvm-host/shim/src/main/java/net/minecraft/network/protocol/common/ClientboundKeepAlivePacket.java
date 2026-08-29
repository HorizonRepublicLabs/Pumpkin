package net.minecraft.network.protocol.common;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundKeepAlivePacket implements Packet<ClientCommonPacketListener> {

    public ClientboundKeepAlivePacket(long id) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundKeepAlivePacket.<init>:(J)V");
    }

    private ClientboundKeepAlivePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundKeepAlivePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundKeepAlivePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundKeepAlivePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundKeepAlivePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundKeepAlivePacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }

    public long getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundKeepAlivePacket.getId:()J");
    }

    public ClientboundKeepAlivePacket() {
    }
}
