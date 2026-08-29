package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.border.WorldBorder;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetBorderSizePacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetBorderSizePacket(WorldBorder border) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderSizePacket.<init>:(Lnet/minecraft/world/level/border/WorldBorder;)V");
    }

    private ClientboundSetBorderSizePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderSizePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderSizePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetBorderSizePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderSizePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderSizePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public double getSize() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderSizePacket.getSize:()D");
    }

    protected ClientboundSetBorderSizePacket() {
    }
}
