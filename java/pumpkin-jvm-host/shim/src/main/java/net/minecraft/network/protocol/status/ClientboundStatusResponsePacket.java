package net.minecraft.network.protocol.status;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundStatusResponsePacket(ServerStatus status, String cachedStatus) implements Packet<ClientStatusPacketListener> {

    public ClientboundStatusResponsePacket(ServerStatus status) {
        this((ServerStatus) null, (String) null);
    }

    public PacketType<ClientboundStatusResponsePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/status/ClientboundStatusResponsePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientStatusPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/status/ClientboundStatusResponsePacket.handle:(Lnet/minecraft/network/protocol/status/ClientStatusPacketListener;)V");
    }
}
