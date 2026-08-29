package net.minecraft.network.protocol.game;

import java.time.Instant;
import net.minecraft.commands.arguments.ArgumentSignatures;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.LastSeenMessages;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundChatCommandSignedPacket(String command, Instant timeStamp, long salt, ArgumentSignatures argumentSignatures, LastSeenMessages.Update lastSeenMessages) implements Packet<ServerGamePacketListener> {

    private ServerboundChatCommandSignedPacket(FriendlyByteBuf input) {
        this((String) null, (Instant) null, (long) 0L, (ArgumentSignatures) null, (LastSeenMessages.Update) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatCommandSignedPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatCommandSignedPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundChatCommandSignedPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatCommandSignedPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChatCommandSignedPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
