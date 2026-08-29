package net.minecraft.network.protocol.login;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundLoginCompressionPacket implements Packet<ClientLoginPacketListener> {

    public ClientboundLoginCompressionPacket(int compressionThreshold) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginCompressionPacket.<init>:(I)V");
    }

    private ClientboundLoginCompressionPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginCompressionPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginCompressionPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundLoginCompressionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginCompressionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientLoginPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginCompressionPacket.handle:(Lnet/minecraft/network/protocol/login/ClientLoginPacketListener;)V");
    }

    public ClientboundLoginCompressionPacket() {
    }
}
