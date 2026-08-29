package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundPlayerLoadedPacket() implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundPlayerLoadedPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerLoadedPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerLoadedPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
