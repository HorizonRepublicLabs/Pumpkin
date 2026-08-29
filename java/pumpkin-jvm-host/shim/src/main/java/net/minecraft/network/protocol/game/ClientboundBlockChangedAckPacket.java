package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundBlockChangedAckPacket(int sequence) implements Packet<ClientGamePacketListener> {

    private ClientboundBlockChangedAckPacket(FriendlyByteBuf input) {
        this((int) 0);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockChangedAckPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockChangedAckPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundBlockChangedAckPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockChangedAckPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockChangedAckPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
