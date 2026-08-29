package net.minecraft.world.entity;

import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public interface OwnableEntity {

    EntityReference<LivingEntity> getOwnerReference();

    Level level();

    default LivingEntity getOwner() {
        throw Unimplemented.forMember("net/minecraft/world/entity/OwnableEntity.getOwner:()Lnet/minecraft/world/entity/LivingEntity;");
    }
}
