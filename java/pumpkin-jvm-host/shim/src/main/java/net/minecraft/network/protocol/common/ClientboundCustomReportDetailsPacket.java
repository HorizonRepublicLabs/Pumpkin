package net.minecraft.network.protocol.common;

import java.util.Map;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundCustomReportDetailsPacket(Map<String, String> details) implements Packet<ClientCommonPacketListener> {

    public PacketType<ClientboundCustomReportDetailsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundCustomReportDetailsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundCustomReportDetailsPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
