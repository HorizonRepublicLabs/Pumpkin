package net.minecraft.network.protocol.game;

import com.mojang.brigadier.suggestion.Suggestions;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundCommandSuggestionsPacket(int id, int start, int length, List<ClientboundCommandSuggestionsPacket.Entry> suggestions) implements Packet<ClientGamePacketListener> {

    public ClientboundCommandSuggestionsPacket(int id, Suggestions suggestions) {
        this((int) 0, (int) 0, (int) 0, (List<ClientboundCommandSuggestionsPacket.Entry>) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandSuggestionsPacket.<init>:(ILcom/mojang/brigadier/suggestion/Suggestions;)V");
    }

    public PacketType<ClientboundCommandSuggestionsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandSuggestionsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandSuggestionsPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public record Entry(String text, Optional<Component> tooltip) {
    }
}
