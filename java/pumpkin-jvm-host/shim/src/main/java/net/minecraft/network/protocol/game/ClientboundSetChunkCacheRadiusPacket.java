package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetChunkCacheRadiusPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetChunkCacheRadiusPacket(int radius) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheRadiusPacket.<init>:(I)V");
    }

    private ClientboundSetChunkCacheRadiusPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheRadiusPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheRadiusPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetChunkCacheRadiusPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheRadiusPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheRadiusPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundSetChunkCacheRadiusPacket() {
    }
}
