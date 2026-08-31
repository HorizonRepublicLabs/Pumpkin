package net.minecraft.world.entity.monster.zombie;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class ZombifiedPiglin extends Zombie implements NeutralMob {

    public ZombifiedPiglin(EntityType<? extends ZombifiedPiglin> type, Level level) {
    }

    protected void addBehaviourGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.addBehaviourGoals:()V");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    protected boolean convertsInWater() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.convertsInWater:()Z");
    }

    protected void customServerAiStep(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.customServerAiStep:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public void setTarget(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.setTarget:(Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    public void startPersistentAngerTimer() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.startPersistentAngerTimer:()V");
    }

    public boolean checkSpawnObstruction(LevelReader level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.checkSpawnObstruction:(Lnet/minecraft/world/level/LevelReader;)Z");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void setPersistentAngerEndTime(long endTime) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.setPersistentAngerEndTime:(J)V");
    }

    public long getPersistentAngerEndTime() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.getPersistentAngerEndTime:()J");
    }

    public void setPersistentAngerTarget(EntityReference<LivingEntity> persistentAngerTarget) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.setPersistentAngerTarget:(Lnet/minecraft/world/entity/EntityReference;)V");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.populateDefaultEquipmentSlots:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    protected void randomizeReinforcementsChance() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.randomizeReinforcementsChance:()V");
    }

    public EntityReference<LivingEntity> getPersistentAngerTarget() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.getPersistentAngerTarget:()Lnet/minecraft/world/entity/EntityReference;");
    }

    public boolean isPreventingPlayerRest(ServerLevel level, Player player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.isPreventingPlayerRest:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public boolean wantsToPickUp(ServerLevel level, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/ZombifiedPiglin.wantsToPickUp:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public ZombifiedPiglin() {
    }
}
