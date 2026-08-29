package net.minecraft.network.protocol.login;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundHelloPacket implements Packet<ClientLoginPacketListener> {

    public ClientboundHelloPacket(String serverId, byte[] publicKey, byte[] challenge, boolean shouldAuthenticate) {
    }

    private ClientboundHelloPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundHelloPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundHelloPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundHelloPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientLoginPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundHelloPacket.handle:(Lnet/minecraft/network/protocol/login/ClientLoginPacketListener;)V");
    }

    public ClientboundHelloPacket() {
    }
}
