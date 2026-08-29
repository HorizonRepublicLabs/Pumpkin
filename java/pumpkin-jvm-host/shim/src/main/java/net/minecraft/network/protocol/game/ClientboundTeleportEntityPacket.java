package net.minecraft.network.protocol.game;

import java.util.Set;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.entity.Relative;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundTeleportEntityPacket(int id, PositionMoveRotation change, Set<Relative> relatives, boolean onGround) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundTeleportEntityPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTeleportEntityPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTeleportEntityPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
