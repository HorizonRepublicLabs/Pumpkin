package net.minecraft.network.protocol.game;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetSubtitleTextPacket(Component text) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSetSubtitleTextPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetSubtitleTextPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetSubtitleTextPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
