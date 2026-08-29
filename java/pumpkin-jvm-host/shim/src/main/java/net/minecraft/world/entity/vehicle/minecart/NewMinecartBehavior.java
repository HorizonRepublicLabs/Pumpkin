package net.minecraft.world.entity.vehicle.minecart;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class NewMinecartBehavior extends MinecartBehavior {

    public NewMinecartBehavior(AbstractMinecart minecart) {
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/NewMinecartBehavior.tick:()V");
    }

    public void moveAlongTrack(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/NewMinecartBehavior.moveAlongTrack:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public double stepAlongTrack(BlockPos pos, RailShape shape, double movementLeft) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/NewMinecartBehavior.stepAlongTrack:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/properties/RailShape;D)D");
    }

    public double getMaxSpeed(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/NewMinecartBehavior.getMaxSpeed:(Lnet/minecraft/server/level/ServerLevel;)D");
    }

    public double getSlowdownFactor() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/NewMinecartBehavior.getSlowdownFactor:()D");
    }

    public boolean pushAndPickupEntities() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/NewMinecartBehavior.pushAndPickupEntities:()Z");
    }

    public record MinecartStep(Vec3 position, Vec3 movement, float yRot, float xRot, float weight) {
    }

    private record StepPartialTicks(float partialTicksInStep, NewMinecartBehavior.MinecartStep currentStep, NewMinecartBehavior.MinecartStep previousStep) {
    }

    private static class TrackIteration {

        protected TrackIteration() {
        }
    }

    public NewMinecartBehavior() {
    }
}
