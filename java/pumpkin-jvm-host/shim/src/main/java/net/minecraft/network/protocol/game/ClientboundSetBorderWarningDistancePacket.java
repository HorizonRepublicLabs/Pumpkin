package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.border.WorldBorder;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetBorderWarningDistancePacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetBorderWarningDistancePacket(WorldBorder border) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDistancePacket.<init>:(Lnet/minecraft/world/level/border/WorldBorder;)V");
    }

    private ClientboundSetBorderWarningDistancePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDistancePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDistancePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetBorderWarningDistancePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDistancePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDistancePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundSetBorderWarningDistancePacket() {
    }
}
