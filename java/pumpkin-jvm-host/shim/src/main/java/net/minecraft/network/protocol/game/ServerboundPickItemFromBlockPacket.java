package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundPickItemFromBlockPacket(BlockPos pos, boolean includeData) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundPickItemFromBlockPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPickItemFromBlockPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPickItemFromBlockPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
