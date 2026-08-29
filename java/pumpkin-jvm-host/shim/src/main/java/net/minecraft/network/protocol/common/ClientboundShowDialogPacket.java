package net.minecraft.network.protocol.common;

import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.server.dialog.Dialog;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundShowDialogPacket(Holder<Dialog> dialog) implements Packet<ClientCommonPacketListener> {

    public PacketType<ClientboundShowDialogPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundShowDialogPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundShowDialogPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
