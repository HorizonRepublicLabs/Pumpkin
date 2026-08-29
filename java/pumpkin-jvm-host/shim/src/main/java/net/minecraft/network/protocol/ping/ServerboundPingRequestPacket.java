package net.minecraft.network.protocol.ping;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundPingRequestPacket implements Packet<ServerPingPacketListener> {

    public ServerboundPingRequestPacket(long time) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ServerboundPingRequestPacket.<init>:(J)V");
    }

    private ServerboundPingRequestPacket(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ServerboundPingRequestPacket.<init>:(Lio/netty/buffer/ByteBuf;)V");
    }

    private void write(ByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ServerboundPingRequestPacket.write:(Lio/netty/buffer/ByteBuf;)V");
    }

    public PacketType<ServerboundPingRequestPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ServerboundPingRequestPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerPingPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/ping/ServerboundPingRequestPacket.handle:(Lnet/minecraft/network/protocol/ping/ServerPingPacketListener;)V");
    }

    protected ServerboundPingRequestPacket() {
    }
}
