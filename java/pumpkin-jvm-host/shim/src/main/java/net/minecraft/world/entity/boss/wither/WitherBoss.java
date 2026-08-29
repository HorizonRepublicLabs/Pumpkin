package net.minecraft.world.entity.boss.wither;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class WitherBoss extends Monster implements RangedAttackMob {

    public WitherBoss(EntityType<? extends WitherBoss> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    protected PathNavigation createNavigation(Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.createNavigation:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/ai/navigation/PathNavigation;");
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.registerGoals:()V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void setCustomName(Component name) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.setCustomName:(Lnet/minecraft/network/chat/Component;)V");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.aiStep:()V");
    }

    protected void customServerAiStep(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.customServerAiStep:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public void makeStuckInBlock(BlockState blockState, Vec3 speedMultiplier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.makeStuckInBlock:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public void startSeenByPlayer(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.startSeenByPlayer:(Lnet/minecraft/server/level/ServerPlayer;)V");
    }

    public void stopSeenByPlayer(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.stopSeenByPlayer:(Lnet/minecraft/server/level/ServerPlayer;)V");
    }

    private void performRangedAttack(int head, LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.performRangedAttack:(ILnet/minecraft/world/entity/LivingEntity;)V");
    }

    public void performRangedAttack(LivingEntity target, float power) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.performRangedAttack:(Lnet/minecraft/world/entity/LivingEntity;F)V");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean killedByPlayer) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.dropCustomDeathLoot:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;Z)V");
    }

    public void checkDespawn() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.checkDespawn:()V");
    }

    public boolean addEffect(MobEffectInstance newEffect, Entity source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.addEffect:(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z");
    }

    protected boolean canRide(Entity vehicle) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.canRide:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean canUsePortal(boolean ignorePassenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.canUsePortal:(Z)Z");
    }

    public boolean canBeAffected(MobEffectInstance newEffect) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss.canBeAffected:(Lnet/minecraft/world/effect/MobEffectInstance;)Z");
    }

    private class WitherDoNothingGoal extends Goal {

        public WitherDoNothingGoal() {
            throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss$WitherDoNothingGoal.<init>:()V");
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/boss/wither/WitherBoss$WitherDoNothingGoal.canUse:()Z");
        }
    }

    public WitherBoss() {
    }
}
