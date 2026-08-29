package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.border.WorldBorder;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetBorderCenterPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetBorderCenterPacket(WorldBorder border) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderCenterPacket.<init>:(Lnet/minecraft/world/level/border/WorldBorder;)V");
    }

    private ClientboundSetBorderCenterPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderCenterPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderCenterPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetBorderCenterPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderCenterPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderCenterPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundSetBorderCenterPacket() {
    }
}
