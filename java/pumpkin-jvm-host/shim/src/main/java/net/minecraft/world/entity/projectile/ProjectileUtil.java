package net.minecraft.world.entity.projectile;

import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public final class ProjectileUtil {

    public static EntityHitResult getEntityHitResult(Entity except, Vec3 from, Vec3 to, AABB box, Predicate<Entity> matching, double maxValue) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/ProjectileUtil.getEntityHitResult:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;D)Lnet/minecraft/world/phys/EntityHitResult;");
    }

    public static EntityHitResult getEntityHitResult(Level level, Projectile source, Vec3 from, Vec3 to, AABB targetSearchArea, Predicate<Entity> matching) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/ProjectileUtil.getEntityHitResult:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/projectile/Projectile;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Lnet/minecraft/world/phys/EntityHitResult;");
    }

    public static EntityHitResult getEntityHitResult(Level level, Entity source, Vec3 from, Vec3 to, AABB targetSearchArea, Predicate<Entity> matching, float entityMargin) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/ProjectileUtil.getEntityHitResult:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;F)Lnet/minecraft/world/phys/EntityHitResult;");
    }

    public ProjectileUtil() {
    }
}
