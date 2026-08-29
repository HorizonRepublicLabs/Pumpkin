package net.minecraft.network.protocol.game;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetTitleTextPacket(Component text) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSetTitleTextPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTitleTextPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTitleTextPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
