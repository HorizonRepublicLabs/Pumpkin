package net.minecraft.world.phys;

import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class EntityHitResult extends HitResult {

    public EntityHitResult(Entity entity) {
    }

    public EntityHitResult(Entity entity, Vec3 location) {
    }

    public Entity getEntity() {
        throw Unimplemented.forMember("net/minecraft/world/phys/EntityHitResult.getEntity:()Lnet/minecraft/world/entity/Entity;");
    }

    public HitResult.Type getType() {
        throw Unimplemented.forMember("net/minecraft/world/phys/EntityHitResult.getType:()Lnet/minecraft/world/phys/HitResult$Type;");
    }

    public EntityHitResult() {
    }
}
