package net.minecraft.network.protocol.common;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundPingPacket implements Packet<ClientCommonPacketListener> {

    public ClientboundPingPacket(int id) {
    }

    private ClientboundPingPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundPingPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundPingPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundPingPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundPingPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundPingPacket.getId:()I");
    }

    public ClientboundPingPacket() {
    }
}
