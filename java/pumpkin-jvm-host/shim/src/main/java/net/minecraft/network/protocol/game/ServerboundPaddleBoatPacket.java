package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundPaddleBoatPacket implements Packet<ServerGamePacketListener> {

    public ServerboundPaddleBoatPacket(boolean left, boolean right) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPaddleBoatPacket.<init>:(ZZ)V");
    }

    private ServerboundPaddleBoatPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPaddleBoatPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPaddleBoatPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPaddleBoatPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public PacketType<ServerboundPaddleBoatPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPaddleBoatPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    protected ServerboundPaddleBoatPacket() {
    }
}
