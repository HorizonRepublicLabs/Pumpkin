package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetChunkCacheCenterPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetChunkCacheCenterPacket(int x, int z) {
    }

    private ClientboundSetChunkCacheCenterPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheCenterPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetChunkCacheCenterPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheCenterPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheCenterPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getX() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheCenterPacket.getX:()I");
    }

    public int getZ() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetChunkCacheCenterPacket.getZ:()I");
    }

    public ClientboundSetChunkCacheCenterPacket() {
    }
}
