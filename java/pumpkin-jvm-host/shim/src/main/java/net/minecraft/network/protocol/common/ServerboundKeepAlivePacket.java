package net.minecraft.network.protocol.common;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundKeepAlivePacket implements Packet<ServerCommonPacketListener> {

    public ServerboundKeepAlivePacket(long id) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundKeepAlivePacket.<init>:(J)V");
    }

    private ServerboundKeepAlivePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundKeepAlivePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundKeepAlivePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundKeepAlivePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundKeepAlivePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundKeepAlivePacket.handle:(Lnet/minecraft/network/protocol/common/ServerCommonPacketListener;)V");
    }

    public long getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundKeepAlivePacket.getId:()J");
    }

    protected ServerboundKeepAlivePacket() {
    }
}
