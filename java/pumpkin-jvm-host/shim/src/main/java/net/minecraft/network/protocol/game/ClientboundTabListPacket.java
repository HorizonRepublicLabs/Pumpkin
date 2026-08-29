package net.minecraft.network.protocol.game;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundTabListPacket(Component header, Component footer) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundTabListPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTabListPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTabListPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
