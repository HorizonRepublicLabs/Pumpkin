package net.minecraft.world.entity.animal.golem;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractGolem extends PathfinderMob {

    protected AbstractGolem(EntityType<? extends AbstractGolem> type, Level level) {
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/golem/AbstractGolem.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/golem/AbstractGolem.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/golem/AbstractGolem.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public int getAmbientSoundInterval() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/golem/AbstractGolem.getAmbientSoundInterval:()I");
    }

    public boolean removeWhenFarAway(double distSqr) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/golem/AbstractGolem.removeWhenFarAway:(D)Z");
    }

    public AbstractGolem() {
    }
}
