package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.Difficulty;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundChangeDifficultyPacket(Difficulty difficulty) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundChangeDifficultyPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChangeDifficultyPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChangeDifficultyPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
