package net.minecraft.world.entity.animal.parrot;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public abstract class ShoulderRidingEntity extends TamableAnimal {

    protected ShoulderRidingEntity(EntityType<? extends ShoulderRidingEntity> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/ShoulderRidingEntity.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/ShoulderRidingEntity.tick:()V");
    }

    public ShoulderRidingEntity() {
    }
}
