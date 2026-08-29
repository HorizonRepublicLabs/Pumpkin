package net.minecraft.network.protocol.game;

import java.util.UUID;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FilterMask;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.chat.SignedMessageBody;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundPlayerChatPacket(int globalIndex, UUID sender, int index, MessageSignature signature, SignedMessageBody.Packed body, Component unsignedContent, FilterMask filterMask, ChatType.Bound chatType) implements Packet<ClientGamePacketListener> {

    private ClientboundPlayerChatPacket(RegistryFriendlyByteBuf input) {
        this((int) 0, (UUID) null, (int) 0, (MessageSignature) null, (SignedMessageBody.Packed) null, (Component) null, (FilterMask) null, (ChatType.Bound) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerChatPacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerChatPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundPlayerChatPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerChatPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerChatPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public boolean isSkippable() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerChatPacket.isSkippable:()Z");
    }
}
