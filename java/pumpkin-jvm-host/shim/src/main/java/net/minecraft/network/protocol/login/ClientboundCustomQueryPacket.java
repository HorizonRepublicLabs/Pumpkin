package net.minecraft.network.protocol.login;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.login.custom.CustomQueryPayload;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundCustomQueryPacket(int transactionId, CustomQueryPayload payload) implements Packet<ClientLoginPacketListener> {

    private ClientboundCustomQueryPacket(FriendlyByteBuf input) {
        this((int) 0, (CustomQueryPayload) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundCustomQueryPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundCustomQueryPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundCustomQueryPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundCustomQueryPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientLoginPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundCustomQueryPacket.handle:(Lnet/minecraft/network/protocol/login/ClientLoginPacketListener;)V");
    }
}
