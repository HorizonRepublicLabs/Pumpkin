package net.minecraft.world.entity.boss.enderdragon;

import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class EnderDragon extends Mob implements Enemy {

    public EnderDragon(EntityType<? extends EnderDragon> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    public boolean isFlapping() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.isFlapping:()Z");
    }

    public void onFlap() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.onFlap:()V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.aiStep:()V");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    protected void handleKillingBlow() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.handleKillingBlow:()V");
    }

    public void knockback(double power, double xd, double zd, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.knockback:(DDDLnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    public void kill(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.kill:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    protected void tickDeath() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.tickDeath:()V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void checkDespawn() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.checkDespawn:()V");
    }

    public boolean isPickable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.isPickable:()Z");
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected float getSoundVolume() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.getSoundVolume:()F");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public boolean addEffect(MobEffectInstance newEffect, Entity source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.addEffect:(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z");
    }

    protected boolean canRide(Entity vehicle) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.canRide:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean canUsePortal(boolean ignorePassenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.canUsePortal:(Z)Z");
    }

    public boolean isMultipartEntity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.isMultipartEntity:()Z");
    }

    public net.neoforged.neoforge.entity.PartEntity<?>[] getParts() {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.getParts:()[Lnet/neoforged/neoforge/entity/PartEntity;");
    }

    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.recreateFromPacket:(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V");
    }

    public boolean canAttack(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.canAttack:(Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    protected float sanitizeScale(float scale) {
        throw Unimplemented.forMember("net/minecraft/world/entity/boss/enderdragon/EnderDragon.sanitizeScale:(F)F");
    }

    protected EnderDragon() {
    }
}
