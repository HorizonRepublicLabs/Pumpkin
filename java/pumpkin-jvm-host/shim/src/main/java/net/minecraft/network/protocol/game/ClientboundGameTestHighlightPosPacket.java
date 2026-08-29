package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundGameTestHighlightPosPacket(BlockPos absolutePos, BlockPos relativePos) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundGameTestHighlightPosPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundGameTestHighlightPosPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundGameTestHighlightPosPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
