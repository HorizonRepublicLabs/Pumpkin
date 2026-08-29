package net.minecraft.world.waypoints;

import com.mojang.datafixers.util.Either;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class TrackedWaypoint implements Waypoint {

    private TrackedWaypoint(Either<UUID, String> identifier, Waypoint.Icon icon, TrackedWaypoint.Type type) {
    }

    public Either<UUID, String> id() {
        throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint.id:()Lcom/mojang/datafixers/util/Either;");
    }

    public abstract void update(final TrackedWaypoint other);

    public void write(ByteBuf buf) {
        throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint.write:(Lio/netty/buffer/ByteBuf;)V");
    }

    public abstract void writeContents(final ByteBuf buf);

    private static TrackedWaypoint read(ByteBuf buf) {
        throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint.read:(Lio/netty/buffer/ByteBuf;)Lnet/minecraft/world/waypoints/TrackedWaypoint;");
    }

    public abstract double yawAngleToCamera(final Level level, final TrackedWaypoint.Camera camera, final PartialTickSupplier partialTickSupplier);

    public abstract TrackedWaypoint.PitchDirection pitchDirectionToCamera(Level level, TrackedWaypoint.Projector projector, final PartialTickSupplier partialTickSupplier);

    public abstract double distanceSquared(final Entity fromEntity);

    private static class AzimuthWaypoint extends TrackedWaypoint {

        public AzimuthWaypoint(UUID identifier, Waypoint.Icon icon, float angle) {
        }

        public AzimuthWaypoint(Either<UUID, String> identifier, Waypoint.Icon icon, FriendlyByteBuf byteBuf) {
        }

        public void update(TrackedWaypoint other) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$AzimuthWaypoint.update:(Lnet/minecraft/world/waypoints/TrackedWaypoint;)V");
        }

        public void writeContents(ByteBuf buf) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$AzimuthWaypoint.writeContents:(Lio/netty/buffer/ByteBuf;)V");
        }

        public double yawAngleToCamera(Level level, TrackedWaypoint.Camera camera, PartialTickSupplier partialTickSupplier) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$AzimuthWaypoint.yawAngleToCamera:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/TrackedWaypoint$Camera;Lnet/minecraft/world/waypoints/PartialTickSupplier;)D");
        }

        public TrackedWaypoint.PitchDirection pitchDirectionToCamera(Level level, TrackedWaypoint.Projector projector, PartialTickSupplier partialTickSupplier) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$AzimuthWaypoint.pitchDirectionToCamera:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/TrackedWaypoint$Projector;Lnet/minecraft/world/waypoints/PartialTickSupplier;)Lnet/minecraft/world/waypoints/TrackedWaypoint$PitchDirection;");
        }

        public double distanceSquared(Entity fromEntity) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$AzimuthWaypoint.distanceSquared:(Lnet/minecraft/world/entity/Entity;)D");
        }

        protected AzimuthWaypoint() {
        }
    }

    public interface Camera {

        float yaw();

        Vec3 position();
    }

    private static class ChunkWaypoint extends TrackedWaypoint {

        public ChunkWaypoint(UUID identifier, Waypoint.Icon icon, ChunkPos chunkPos) {
        }

        public ChunkWaypoint(Either<UUID, String> identifier, Waypoint.Icon icon, FriendlyByteBuf byteBuf) {
        }

        public void update(TrackedWaypoint other) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$ChunkWaypoint.update:(Lnet/minecraft/world/waypoints/TrackedWaypoint;)V");
        }

        public void writeContents(ByteBuf buf) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$ChunkWaypoint.writeContents:(Lio/netty/buffer/ByteBuf;)V");
        }

        public double yawAngleToCamera(Level level, TrackedWaypoint.Camera camera, PartialTickSupplier partialTickSupplier) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$ChunkWaypoint.yawAngleToCamera:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/TrackedWaypoint$Camera;Lnet/minecraft/world/waypoints/PartialTickSupplier;)D");
        }

        public TrackedWaypoint.PitchDirection pitchDirectionToCamera(Level level, TrackedWaypoint.Projector projector, PartialTickSupplier partialTickSupplier) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$ChunkWaypoint.pitchDirectionToCamera:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/TrackedWaypoint$Projector;Lnet/minecraft/world/waypoints/PartialTickSupplier;)Lnet/minecraft/world/waypoints/TrackedWaypoint$PitchDirection;");
        }

        public double distanceSquared(Entity fromEntity) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$ChunkWaypoint.distanceSquared:(Lnet/minecraft/world/entity/Entity;)D");
        }

        protected ChunkWaypoint() {
        }
    }

    private static class EmptyWaypoint extends TrackedWaypoint {

        private EmptyWaypoint(Either<UUID, String> identifier, Waypoint.Icon icon, FriendlyByteBuf byteBuf) {
        }

        private EmptyWaypoint(UUID identifier) {
        }

        public void update(TrackedWaypoint other) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$EmptyWaypoint.update:(Lnet/minecraft/world/waypoints/TrackedWaypoint;)V");
        }

        public void writeContents(ByteBuf buf) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$EmptyWaypoint.writeContents:(Lio/netty/buffer/ByteBuf;)V");
        }

        public double yawAngleToCamera(Level level, TrackedWaypoint.Camera camera, PartialTickSupplier partialTickSupplier) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$EmptyWaypoint.yawAngleToCamera:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/TrackedWaypoint$Camera;Lnet/minecraft/world/waypoints/PartialTickSupplier;)D");
        }

        public TrackedWaypoint.PitchDirection pitchDirectionToCamera(Level level, TrackedWaypoint.Projector projector, PartialTickSupplier partialTickSupplier) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$EmptyWaypoint.pitchDirectionToCamera:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/TrackedWaypoint$Projector;Lnet/minecraft/world/waypoints/PartialTickSupplier;)Lnet/minecraft/world/waypoints/TrackedWaypoint$PitchDirection;");
        }

        public double distanceSquared(Entity fromEntity) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$EmptyWaypoint.distanceSquared:(Lnet/minecraft/world/entity/Entity;)D");
        }

        protected EmptyWaypoint() {
        }
    }

    public enum PitchDirection {

        NONE, UP, DOWN
    }

    public interface Projector {

        Vec3 projectPointToScreen(final Vec3 point);

        double projectHorizonToScreen();
    }

    private enum Type {

        EMPTY, VEC3I, CHUNK, AZIMUTH
    }

    private static class Vec3iWaypoint extends TrackedWaypoint {

        public Vec3iWaypoint(UUID identifier, Waypoint.Icon icon, Vec3i vector) {
        }

        public Vec3iWaypoint(Either<UUID, String> identifier, Waypoint.Icon icon, FriendlyByteBuf byteBuf) {
        }

        public void update(TrackedWaypoint other) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$Vec3iWaypoint.update:(Lnet/minecraft/world/waypoints/TrackedWaypoint;)V");
        }

        public void writeContents(ByteBuf buf) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$Vec3iWaypoint.writeContents:(Lio/netty/buffer/ByteBuf;)V");
        }

        public double yawAngleToCamera(Level level, TrackedWaypoint.Camera camera, PartialTickSupplier partialTickSupplier) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$Vec3iWaypoint.yawAngleToCamera:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/TrackedWaypoint$Camera;Lnet/minecraft/world/waypoints/PartialTickSupplier;)D");
        }

        public TrackedWaypoint.PitchDirection pitchDirectionToCamera(Level level, TrackedWaypoint.Projector projector, PartialTickSupplier partialTickSupplier) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$Vec3iWaypoint.pitchDirectionToCamera:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/waypoints/TrackedWaypoint$Projector;Lnet/minecraft/world/waypoints/PartialTickSupplier;)Lnet/minecraft/world/waypoints/TrackedWaypoint$PitchDirection;");
        }

        public double distanceSquared(Entity fromEntity) {
            throw Unimplemented.forMember("net/minecraft/world/waypoints/TrackedWaypoint$Vec3iWaypoint.distanceSquared:(Lnet/minecraft/world/entity/Entity;)D");
        }

        protected Vec3iWaypoint() {
        }
    }

    public TrackedWaypoint() {
    }
}
