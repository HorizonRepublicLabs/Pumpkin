package net.minecraft.world.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public interface ItemOwner {

    Level level();

    Vec3 position();

    float getVisualRotationYInDegrees();

    default LivingEntity asLivingEntity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ItemOwner.asLivingEntity:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    record OffsetFromOwner(ItemOwner owner, Vec3 offset) implements ItemOwner {

        public Level level() {
            throw Unimplemented.forMember("net/minecraft/world/entity/ItemOwner$OffsetFromOwner.level:()Lnet/minecraft/world/level/Level;");
        }

        public Vec3 position() {
            throw Unimplemented.forMember("net/minecraft/world/entity/ItemOwner$OffsetFromOwner.position:()Lnet/minecraft/world/phys/Vec3;");
        }

        public float getVisualRotationYInDegrees() {
            throw Unimplemented.forMember("net/minecraft/world/entity/ItemOwner$OffsetFromOwner.getVisualRotationYInDegrees:()F");
        }

        public LivingEntity asLivingEntity() {
            throw Unimplemented.forMember("net/minecraft/world/entity/ItemOwner$OffsetFromOwner.asLivingEntity:()Lnet/minecraft/world/entity/LivingEntity;");
        }
    }
}
