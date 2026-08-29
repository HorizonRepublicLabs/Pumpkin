package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSeenAdvancementsPacket implements Packet<ServerGamePacketListener> {

    public ServerboundSeenAdvancementsPacket(ServerboundSeenAdvancementsPacket.Action action, Identifier tab) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSeenAdvancementsPacket.<init>:(Lnet/minecraft/network/protocol/game/ServerboundSeenAdvancementsPacket$Action;Lnet/minecraft/resources/Identifier;)V");
    }

    private ServerboundSeenAdvancementsPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSeenAdvancementsPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSeenAdvancementsPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSeenAdvancementsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSeenAdvancementsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSeenAdvancementsPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public enum Action {

        OPENED_TAB, CLOSED_SCREEN
    }

    protected ServerboundSeenAdvancementsPacket() {
    }
}
