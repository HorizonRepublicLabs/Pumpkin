package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.PositionMoveRotation;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundEntityPositionSyncPacket(int id, PositionMoveRotation values, boolean onGround) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundEntityPositionSyncPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundEntityPositionSyncPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundEntityPositionSyncPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
