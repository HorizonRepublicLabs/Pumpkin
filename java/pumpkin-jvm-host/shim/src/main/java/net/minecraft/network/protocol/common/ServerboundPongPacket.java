package net.minecraft.network.protocol.common;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundPongPacket implements Packet<ServerCommonPacketListener> {

    public ServerboundPongPacket(int id) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundPongPacket.<init>:(I)V");
    }

    private ServerboundPongPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundPongPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundPongPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundPongPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundPongPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundPongPacket.handle:(Lnet/minecraft/network/protocol/common/ServerCommonPacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundPongPacket.getId:()I");
    }

    protected ServerboundPongPacket() {
    }
}
