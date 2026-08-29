package net.minecraft.world.entity.vehicle.minecart;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class MinecartBehavior {

    protected MinecartBehavior(AbstractMinecart minecart) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartBehavior.<init>:(Lnet/minecraft/world/entity/vehicle/minecart/AbstractMinecart;)V");
    }

    public abstract void tick();

    public Level level() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartBehavior.level:()Lnet/minecraft/world/level/Level;");
    }

    public abstract void moveAlongTrack(ServerLevel level);

    public abstract double stepAlongTrack(final BlockPos pos, final RailShape shape, final double movementLeft);

    public abstract boolean pushAndPickupEntities();

    public Vec3 position() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartBehavior.position:()Lnet/minecraft/world/phys/Vec3;");
    }

    public double getX() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartBehavior.getX:()D");
    }

    public double getY() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartBehavior.getY:()D");
    }

    public double getZ() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartBehavior.getZ:()D");
    }

    public abstract double getMaxSpeed(ServerLevel level);

    public abstract double getSlowdownFactor();

    public MinecartBehavior() {
    }
}
