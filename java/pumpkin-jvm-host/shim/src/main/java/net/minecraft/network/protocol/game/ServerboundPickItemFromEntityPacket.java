package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundPickItemFromEntityPacket(int id, boolean includeData) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundPickItemFromEntityPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPickItemFromEntityPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPickItemFromEntityPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
