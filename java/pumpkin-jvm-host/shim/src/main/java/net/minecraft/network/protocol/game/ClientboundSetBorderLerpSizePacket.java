package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.border.WorldBorder;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetBorderLerpSizePacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetBorderLerpSizePacket(WorldBorder border) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderLerpSizePacket.<init>:(Lnet/minecraft/world/level/border/WorldBorder;)V");
    }

    private ClientboundSetBorderLerpSizePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderLerpSizePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderLerpSizePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetBorderLerpSizePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderLerpSizePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderLerpSizePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public long getLerpTime() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderLerpSizePacket.getLerpTime:()J");
    }

    protected ClientboundSetBorderLerpSizePacket() {
    }
}
