package net.minecraft.network.protocol.game;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundTagQueryPacket implements Packet<ClientGamePacketListener> {

    public ClientboundTagQueryPacket(int transactionId, CompoundTag tag) {
    }

    private ClientboundTagQueryPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTagQueryPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundTagQueryPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTagQueryPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTagQueryPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public boolean isSkippable() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTagQueryPacket.isSkippable:()Z");
    }

    public ClientboundTagQueryPacket() {
    }
}
