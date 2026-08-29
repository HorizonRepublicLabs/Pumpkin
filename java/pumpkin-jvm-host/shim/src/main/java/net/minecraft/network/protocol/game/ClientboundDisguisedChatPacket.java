package net.minecraft.network.protocol.game;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundDisguisedChatPacket(Component message, ChatType.Bound chatType) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundDisguisedChatPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDisguisedChatPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDisguisedChatPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public boolean isSkippable() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDisguisedChatPacket.isSkippable:()Z");
    }
}
