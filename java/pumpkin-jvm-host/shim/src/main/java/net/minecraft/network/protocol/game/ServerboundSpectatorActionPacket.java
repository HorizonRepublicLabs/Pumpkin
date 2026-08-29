package net.minecraft.network.protocol.game;

import java.util.OptionalInt;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundSpectatorActionPacket(OptionalInt spectateEntityId) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundSpectatorActionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSpectatorActionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSpectatorActionPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
