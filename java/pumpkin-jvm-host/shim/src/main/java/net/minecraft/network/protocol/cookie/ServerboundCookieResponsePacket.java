package net.minecraft.network.protocol.cookie;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundCookieResponsePacket(Identifier key, byte[] payload) implements Packet<ServerCookiePacketListener> {

    private ServerboundCookieResponsePacket(FriendlyByteBuf input) {
        this((Identifier) null, (byte[]) null);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/cookie/ServerboundCookieResponsePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundCookieResponsePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/cookie/ServerboundCookieResponsePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerCookiePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/cookie/ServerboundCookieResponsePacket.handle:(Lnet/minecraft/network/protocol/cookie/ServerCookiePacketListener;)V");
    }
}
