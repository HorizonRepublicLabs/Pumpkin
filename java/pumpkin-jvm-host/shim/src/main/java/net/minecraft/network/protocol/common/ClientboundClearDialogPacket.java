package net.minecraft.network.protocol.common;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundClearDialogPacket implements Packet<ClientCommonPacketListener> {

    protected ClientboundClearDialogPacket() {
    }

    public PacketType<ClientboundClearDialogPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundClearDialogPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundClearDialogPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
