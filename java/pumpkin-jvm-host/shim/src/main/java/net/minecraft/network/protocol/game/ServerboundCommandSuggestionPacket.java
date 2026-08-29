package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundCommandSuggestionPacket implements Packet<ServerGamePacketListener> {

    public ServerboundCommandSuggestionPacket(int id, String command) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundCommandSuggestionPacket.<init>:(ILjava/lang/String;)V");
    }

    private ServerboundCommandSuggestionPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundCommandSuggestionPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundCommandSuggestionPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundCommandSuggestionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundCommandSuggestionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundCommandSuggestionPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundCommandSuggestionPacket.getId:()I");
    }

    protected ServerboundCommandSuggestionPacket() {
    }
}
