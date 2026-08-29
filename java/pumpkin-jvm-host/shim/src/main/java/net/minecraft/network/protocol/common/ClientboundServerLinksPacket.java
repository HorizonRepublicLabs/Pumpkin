package net.minecraft.network.protocol.common;

import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.server.ServerLinks;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundServerLinksPacket(List<ServerLinks.UntrustedEntry> links) implements Packet<ClientCommonPacketListener> {

    public PacketType<ClientboundServerLinksPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundServerLinksPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundServerLinksPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
