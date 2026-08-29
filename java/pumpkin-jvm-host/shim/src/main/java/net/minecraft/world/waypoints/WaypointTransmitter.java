package net.minecraft.world.waypoints;

import java.util.Optional;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import dev.pumpkin.shim.Unimplemented;

public interface WaypointTransmitter extends Waypoint {

    boolean isTransmittingWaypoint();

    Optional<WaypointTransmitter.Connection> makeWaypointConnectionWith(ServerPlayer player);

    Waypoint.Icon waypointIcon();

    interface BlockConnection extends WaypointTransmitter.Connection {

        int distanceManhattan();

        default boolean isBroken() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$BlockConnection.isBroken:()Z");
        }
    }

    interface ChunkConnection extends WaypointTransmitter.Connection {

        int distanceChessboard();

        default boolean isBroken() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$ChunkConnection.isBroken:()Z");
        }
    }

    interface Connection {

        void connect();

        void disconnect();

        void update();

        boolean isBroken();
    }

    class EntityAzimuthConnection implements WaypointTransmitter.Connection {

        public EntityAzimuthConnection(LivingEntity source, Waypoint.Icon icon, ServerPlayer receiver) {
        }

        public boolean isBroken() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityAzimuthConnection.isBroken:()Z");
        }

        public void connect() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityAzimuthConnection.connect:()V");
        }

        public void disconnect() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityAzimuthConnection.disconnect:()V");
        }

        public void update() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityAzimuthConnection.update:()V");
        }

        protected EntityAzimuthConnection() {
        }
    }

    class EntityBlockConnection implements WaypointTransmitter.BlockConnection {

        public EntityBlockConnection(LivingEntity source, Waypoint.Icon icon, ServerPlayer receiver) {
        }

        public void connect() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityBlockConnection.connect:()V");
        }

        public void disconnect() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityBlockConnection.disconnect:()V");
        }

        public void update() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityBlockConnection.update:()V");
        }

        public int distanceManhattan() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityBlockConnection.distanceManhattan:()I");
        }

        public boolean isBroken() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityBlockConnection.isBroken:()Z");
        }

        protected EntityBlockConnection() {
        }
    }

    class EntityChunkConnection implements WaypointTransmitter.ChunkConnection {

        public EntityChunkConnection(LivingEntity source, Waypoint.Icon icon, ServerPlayer receiver) {
        }

        public int distanceChessboard() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityChunkConnection.distanceChessboard:()I");
        }

        public void connect() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityChunkConnection.connect:()V");
        }

        public void disconnect() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityChunkConnection.disconnect:()V");
        }

        public void update() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityChunkConnection.update:()V");
        }

        public boolean isBroken() {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/WaypointTransmitter$EntityChunkConnection.isBroken:()Z");
        }

        protected EntityChunkConnection() {
        }
    }
}
