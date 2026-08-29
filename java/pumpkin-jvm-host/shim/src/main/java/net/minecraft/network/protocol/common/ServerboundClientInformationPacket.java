package net.minecraft.network.protocol.common;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.server.level.ClientInformation;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundClientInformationPacket(ClientInformation information) implements Packet<ServerCommonPacketListener> {

    private ServerboundClientInformationPacket(FriendlyByteBuf input) {
        this((ClientInformation) null);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundClientInformationPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundClientInformationPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundClientInformationPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundClientInformationPacket.handle:(Lnet/minecraft/network/protocol/common/ServerCommonPacketListener;)V");
    }
}
