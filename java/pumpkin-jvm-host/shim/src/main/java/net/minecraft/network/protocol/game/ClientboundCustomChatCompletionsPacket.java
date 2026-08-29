package net.minecraft.network.protocol.game;

import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundCustomChatCompletionsPacket(ClientboundCustomChatCompletionsPacket.Action action, List<String> entries) implements Packet<ClientGamePacketListener> {

    private ClientboundCustomChatCompletionsPacket(FriendlyByteBuf input) {
        this((ClientboundCustomChatCompletionsPacket.Action) null, (List<String>) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCustomChatCompletionsPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCustomChatCompletionsPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundCustomChatCompletionsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCustomChatCompletionsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCustomChatCompletionsPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public enum Action {

        ADD, REMOVE, SET
    }
}
