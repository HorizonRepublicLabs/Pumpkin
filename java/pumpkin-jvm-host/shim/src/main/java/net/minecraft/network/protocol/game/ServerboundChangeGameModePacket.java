package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.GameType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundChangeGameModePacket(GameType mode) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundChangeGameModePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChangeGameModePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChangeGameModePacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
