package net.minecraft.network.protocol.game;

import java.time.Instant;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.LastSeenMessages;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundChatPacket(String message, Instant timeStamp, long salt, MessageSignature signature, LastSeenMessages.Update lastSeenMessages) implements Packet<ServerGamePacketListener> {

    private ServerboundChatPacket(FriendlyByteBuf input) {
        this((String) null, (Instant) null, (long) 0L, (MessageSignature) null, (LastSeenMessages.Update) null);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundChatPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
