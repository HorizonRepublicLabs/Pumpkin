package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.util.debugchart.RemoteDebugSampleType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundDebugSamplePacket(long[] sample, RemoteDebugSampleType debugSampleType) implements Packet<ClientGamePacketListener> {

    private ClientboundDebugSamplePacket(FriendlyByteBuf input) {
        this((long[]) null, (RemoteDebugSampleType) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugSamplePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugSamplePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundDebugSamplePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugSamplePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugSamplePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
