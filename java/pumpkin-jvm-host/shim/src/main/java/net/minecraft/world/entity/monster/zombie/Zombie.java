package net.minecraft.world.entity.monster.zombie;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class Zombie extends Monster {

    public Zombie(EntityType<? extends Zombie> type, Level level) {
    }

    public Zombie(Level level) {
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.registerGoals:()V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public boolean isBaby() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.isBaby:()Z");
    }

    protected int getBaseExperienceReward(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.getBaseExperienceReward:(Lnet/minecraft/server/level/ServerLevel;)I");
    }

    public void setBaby(boolean baby) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.setBaby:(Z)V");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.tick:()V");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public boolean doHurtTarget(ServerLevel level, Entity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.doHurtTarget:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;)Z");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getStepSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.getStepSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected void playStepSound(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.playStepSound:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public EntityType<? extends Zombie> getType() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.getType:()Lnet/minecraft/world/entity/EntityType;");
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.populateDefaultEquipmentSlots:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public boolean killedEntity(ServerLevel level, LivingEntity entity, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.killedEntity:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public boolean canHoldItem(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.canHoldItem:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean wantsToPickUp(ServerLevel level, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.wantsToPickUp:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    protected void onOffspringSpawnedFromEgg(Player spawner, Mob offspring) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie.onOffspringSpawnedFromEgg:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Mob;)V");
    }

    private class ZombieAttackTurtleEggGoal extends RemoveBlockGoal {

        public ZombieAttackTurtleEggGoal(PathfinderMob mob, double speedModifier, int verticalSearchRange) {
        }

        public void playDestroyProgressSound(LevelAccessor level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie$ZombieAttackTurtleEggGoal.playDestroyProgressSound:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;)V");
        }

        public void playBreakSound(Level level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie$ZombieAttackTurtleEggGoal.playBreakSound:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V");
        }

        public double acceptedDistance() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Zombie$ZombieAttackTurtleEggGoal.acceptedDistance:()D");
        }

        protected ZombieAttackTurtleEggGoal() {
        }
    }

    public static class ZombieGroupData implements SpawnGroupData {

        public ZombieGroupData(boolean baby, boolean canSpawnJockey) {
        }

        public ZombieGroupData() {
        }
    }

    public Zombie() {
    }
}
