package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.util.debug.DebugSubscription;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundDebugBlockValuePacket(BlockPos blockPos, DebugSubscription.Update<?> update) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundDebugBlockValuePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugBlockValuePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugBlockValuePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
