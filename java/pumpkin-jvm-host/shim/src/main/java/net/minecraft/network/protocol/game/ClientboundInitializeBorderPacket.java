package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.border.WorldBorder;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundInitializeBorderPacket implements Packet<ClientGamePacketListener> {

    private ClientboundInitializeBorderPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundInitializeBorderPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public ClientboundInitializeBorderPacket(WorldBorder border) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundInitializeBorderPacket.<init>:(Lnet/minecraft/world/level/border/WorldBorder;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundInitializeBorderPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundInitializeBorderPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundInitializeBorderPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundInitializeBorderPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public long getLerpTime() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundInitializeBorderPacket.getLerpTime:()J");
    }

    public ClientboundInitializeBorderPacket() {
    }
}
