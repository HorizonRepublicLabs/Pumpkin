package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundContainerButtonClickPacket(int containerId, int buttonId) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundContainerButtonClickPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerButtonClickPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerButtonClickPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
