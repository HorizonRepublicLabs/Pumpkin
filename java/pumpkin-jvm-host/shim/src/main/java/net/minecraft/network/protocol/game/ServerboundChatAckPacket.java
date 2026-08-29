package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundChatAckPacket(int offset) implements Packet<ServerGamePacketListener> {

    private ServerboundChatAckPacket(FriendlyByteBuf input) {
        this((int) 0);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatAckPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatAckPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundChatAckPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatAckPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatAckPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
