package net.minecraft.network.protocol.game;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSystemChatPacket(Component content, boolean overlay) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSystemChatPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSystemChatPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSystemChatPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public boolean isSkippable() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSystemChatPacket.isSkippable:()Z");
    }
}
