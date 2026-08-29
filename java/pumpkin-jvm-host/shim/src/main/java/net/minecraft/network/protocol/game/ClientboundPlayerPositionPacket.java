package net.minecraft.network.protocol.game;

import java.util.Set;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.entity.Relative;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundPlayerPositionPacket(int id, PositionMoveRotation change, Set<Relative> relatives) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundPlayerPositionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerPositionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerPositionPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
