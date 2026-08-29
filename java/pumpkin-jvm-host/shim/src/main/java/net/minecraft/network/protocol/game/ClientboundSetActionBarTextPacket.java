package net.minecraft.network.protocol.game;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetActionBarTextPacket(Component text) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSetActionBarTextPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetActionBarTextPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetActionBarTextPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
