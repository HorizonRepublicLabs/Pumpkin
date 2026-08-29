package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.border.WorldBorder;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetBorderWarningDelayPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetBorderWarningDelayPacket(WorldBorder border) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDelayPacket.<init>:(Lnet/minecraft/world/level/border/WorldBorder;)V");
    }

    private ClientboundSetBorderWarningDelayPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDelayPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDelayPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetBorderWarningDelayPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDelayPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetBorderWarningDelayPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundSetBorderWarningDelayPacket() {
    }
}
