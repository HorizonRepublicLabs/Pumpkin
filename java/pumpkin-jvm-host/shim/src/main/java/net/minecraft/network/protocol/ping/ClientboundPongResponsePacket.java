package net.minecraft.network.protocol.ping;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundPongResponsePacket(long time) implements Packet<ClientPongPacketListener> {

    private ClientboundPongResponsePacket(FriendlyByteBuf input) {
        this((long) 0L);
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ClientboundPongResponsePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ClientboundPongResponsePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundPongResponsePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ClientboundPongResponsePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientPongPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ClientboundPongResponsePacket.handle:(Lnet/minecraft/network/protocol/ping/ClientPongPacketListener;)V");
    }
}
