package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSelectTradePacket implements Packet<ServerGamePacketListener> {

    public ServerboundSelectTradePacket(int item) {
    }

    private ServerboundSelectTradePacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSelectTradePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSelectTradePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSelectTradePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSelectTradePacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public ServerboundSelectTradePacket() {
    }
}
