package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundChatCommandPacket(String command) implements Packet<ServerGamePacketListener> {

    private ServerboundChatCommandPacket(FriendlyByteBuf input) {
        this((String) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatCommandPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatCommandPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundChatCommandPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatCommandPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatCommandPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
