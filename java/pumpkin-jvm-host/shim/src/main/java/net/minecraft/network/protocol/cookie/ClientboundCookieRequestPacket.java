package net.minecraft.network.protocol.cookie;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundCookieRequestPacket(Identifier key) implements Packet<ClientCookiePacketListener> {

    private ClientboundCookieRequestPacket(FriendlyByteBuf input) {
        this((Identifier) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/cookie/ClientboundCookieRequestPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/cookie/ClientboundCookieRequestPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundCookieRequestPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/cookie/ClientboundCookieRequestPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCookiePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/cookie/ClientboundCookieRequestPacket.handle:(Lnet/minecraft/network/protocol/cookie/ClientCookiePacketListener;)V");
    }
}
