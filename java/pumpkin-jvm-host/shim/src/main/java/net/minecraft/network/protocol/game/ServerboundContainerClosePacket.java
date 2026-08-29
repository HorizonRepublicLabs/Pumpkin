package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundContainerClosePacket implements Packet<ServerGamePacketListener> {

    public ServerboundContainerClosePacket(int containerId) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerClosePacket.<init>:(I)V");
    }

    private ServerboundContainerClosePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerClosePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerClosePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundContainerClosePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerClosePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerClosePacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public ServerboundContainerClosePacket() {
    }
}
