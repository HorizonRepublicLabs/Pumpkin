package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundContainerSlotStateChangedPacket(int slotId, int containerId, boolean newState) implements Packet<ServerGamePacketListener> {

    private ServerboundContainerSlotStateChangedPacket(FriendlyByteBuf input) {
        this((int) 0, (int) 0, (boolean) false);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerSlotStateChangedPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundContainerSlotStateChangedPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerSlotStateChangedPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerSlotStateChangedPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
