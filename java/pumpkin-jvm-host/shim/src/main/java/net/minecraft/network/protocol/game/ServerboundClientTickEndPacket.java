package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundClientTickEndPacket() implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundClientTickEndPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundClientTickEndPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundClientTickEndPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
