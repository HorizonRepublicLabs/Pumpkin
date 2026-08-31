package net.minecraft.world.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public abstract class ThrowableProjectile extends Projectile {

    protected ThrowableProjectile(EntityType<? extends ThrowableProjectile> type, Level level) {
    }

    protected ThrowableProjectile(EntityType<? extends ThrowableProjectile> type, double x, double y, double z, Level level) {
    }

    public boolean shouldRenderAtSqrDistance(double distance) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/ThrowableProjectile.shouldRenderAtSqrDistance:(D)Z");
    }

    public boolean canUsePortal(boolean ignorePassenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/ThrowableProjectile.canUsePortal:(Z)Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/ThrowableProjectile.tick:()V");
    }

    protected float getAirDrag() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/ThrowableProjectile.getAirDrag:()F");
    }

    protected double getDefaultGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/ThrowableProjectile.getDefaultGravity:()D");
    }

    public ThrowableProjectile() {
    }
}
