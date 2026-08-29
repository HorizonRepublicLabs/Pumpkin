package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundAttackPacket(int entityId) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundAttackPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundAttackPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundAttackPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
