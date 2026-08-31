package net.minecraft.world.entity.monster.skeleton;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class Skeleton extends AbstractSkeleton {

    public Skeleton(EntityType<? extends Skeleton> type, Level level) {
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public boolean isShaking() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.isShaking:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.tick:()V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public boolean canFreeze() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.canFreeze:()Z");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getStepSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Skeleton.getStepSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public Skeleton() {
    }
}
