package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.waypoints.TrackedWaypoint;
import net.minecraft.world.waypoints.TrackedWaypointManager;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundTrackedWaypointPacket(ClientboundTrackedWaypointPacket.Operation operation, TrackedWaypoint waypoint) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundTrackedWaypointPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTrackedWaypointPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTrackedWaypointPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public void apply(TrackedWaypointManager manager) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTrackedWaypointPacket.apply:(Lnet/minecraft/world/waypoints/TrackedWaypointManager;)V");
    }

    private enum Operation {

        TRACK, UNTRACK, UPDATE
    }
}
