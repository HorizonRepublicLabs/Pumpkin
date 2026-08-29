package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.Difficulty;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundChangeDifficultyPacket(Difficulty difficulty, boolean locked) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundChangeDifficultyPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundChangeDifficultyPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundChangeDifficultyPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
