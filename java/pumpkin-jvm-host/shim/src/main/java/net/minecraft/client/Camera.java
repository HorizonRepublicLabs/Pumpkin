package net.minecraft.client;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.waypoints.TrackedWaypoint;
import dev.pumpkin.shim.Unimplemented;

public class Camera implements TrackedWaypoint.Camera {

    private Entity entity;

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/Camera.tick:()V");
    }

    public void update(DeltaTracker deltaTracker) {
        throw Unimplemented.forMember("net/minecraft/client/Camera.update:(Lnet/minecraft/client/DeltaTracker;)V");
    }

    public Vec3 position() {
        throw Unimplemented.forMember("net/minecraft/client/Camera.position:()Lnet/minecraft/world/phys/Vec3;");
    }

    public BlockPos blockPosition() {
        throw Unimplemented.forMember("net/minecraft/client/Camera.blockPosition:()Lnet/minecraft/core/BlockPos;");
    }

    public float yaw() {
        throw Unimplemented.forMember("net/minecraft/client/Camera.yaw:()F");
    }

    public Entity entity() {
        throw Unimplemented.forMember("net/minecraft/client/Camera.entity:()Lnet/minecraft/world/entity/Entity;");
    }

    public boolean isInitialized() {
        throw Unimplemented.forMember("net/minecraft/client/Camera.isInitialized:()Z");
    }

    public void reset() {
        throw Unimplemented.forMember("net/minecraft/client/Camera.reset:()V");
    }

    public static class NearPlane {

        private NearPlane(Vec3 forward, Vec3 left, Vec3 up) {
        }

        public NearPlane() {
        }
    }

    public Camera() {
    }
}
