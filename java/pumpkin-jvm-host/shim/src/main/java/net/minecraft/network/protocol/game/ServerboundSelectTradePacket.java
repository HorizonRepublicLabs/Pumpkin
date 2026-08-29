package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSelectTradePacket implements Packet<ServerGamePacketListener> {

    public ServerboundSelectTradePacket(int item) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSelectTradePacket.<init>:(I)V");
    }

    private ServerboundSelectTradePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSelectTradePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
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
