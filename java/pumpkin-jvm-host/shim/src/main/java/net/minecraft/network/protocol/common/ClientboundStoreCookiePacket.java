package net.minecraft.network.protocol.common;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundStoreCookiePacket(Identifier key, byte[] payload) implements Packet<ClientCommonPacketListener> {

    private ClientboundStoreCookiePacket(FriendlyByteBuf input) {
        this((Identifier) null, (byte[]) null);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundStoreCookiePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundStoreCookiePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundStoreCookiePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundStoreCookiePacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
