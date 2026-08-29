package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundClientCommandPacket implements Packet<ServerGamePacketListener> {

    public ServerboundClientCommandPacket(ServerboundClientCommandPacket.Action action) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundClientCommandPacket.<init>:(Lnet/minecraft/network/protocol/game/ServerboundClientCommandPacket$Action;)V");
    }

    private ServerboundClientCommandPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundClientCommandPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundClientCommandPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundClientCommandPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundClientCommandPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundClientCommandPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public enum Action {

        PERFORM_RESPAWN, REQUEST_STATS, REQUEST_GAMERULE_VALUES
    }

    protected ServerboundClientCommandPacket() {
    }
}
