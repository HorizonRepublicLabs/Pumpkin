package net.minecraft.world.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class EnderMan extends Monster implements NeutralMob {

    public EnderMan(EntityType<? extends EnderMan> type, Level level) {
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.registerGoals:()V");
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.getWalkTargetValue:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/LevelReader;)F");
    }

    public static AttributeSupplier.Builder createAttributes() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.createAttributes:()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;");
    }

    public void setTarget(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.setTarget:(Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public void startPersistentAngerTimer() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.startPersistentAngerTimer:()V");
    }

    public void setPersistentAngerEndTime(long endTime) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.setPersistentAngerEndTime:(J)V");
    }

    public long getPersistentAngerEndTime() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.getPersistentAngerEndTime:()J");
    }

    public void setPersistentAngerTarget(EntityReference<LivingEntity> persistentAngerTarget) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.setPersistentAngerTarget:(Lnet/minecraft/world/entity/EntityReference;)V");
    }

    public EntityReference<LivingEntity> getPersistentAngerTarget() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.getPersistentAngerTarget:()Lnet/minecraft/world/entity/EntityReference;");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.aiStep:()V");
    }

    public boolean isSensitiveToWater() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.isSensitiveToWater:()Z");
    }

    protected void customServerAiStep(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.customServerAiStep:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean killedByPlayer) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.dropCustomDeathLoot:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;Z)V");
    }

    public BlockState getCarriedBlock() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.getCarriedBlock:()Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public boolean isCreepy() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.isCreepy:()Z");
    }

    public boolean requiresCustomPersistence() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan.requiresCustomPersistence:()Z");
    }

    private static class EndermanFreezeWhenLookedAt extends Goal {

        public EndermanFreezeWhenLookedAt(EnderMan enderman) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanFreezeWhenLookedAt.canUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanFreezeWhenLookedAt.start:()V");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanFreezeWhenLookedAt.tick:()V");
        }

        protected EndermanFreezeWhenLookedAt() {
        }
    }

    private static class EndermanLeaveBlockGoal extends Goal {

        public EndermanLeaveBlockGoal(EnderMan enderman) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanLeaveBlockGoal.canUse:()Z");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanLeaveBlockGoal.tick:()V");
        }

        protected EndermanLeaveBlockGoal() {
        }
    }

    private static class EndermanLookForPlayerGoal extends NearestAttackableTargetGoal<Player> {

        public EndermanLookForPlayerGoal(EnderMan enderman, TargetingConditions.Selector isAngryAt) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanLookForPlayerGoal.canUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanLookForPlayerGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanLookForPlayerGoal.stop:()V");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanLookForPlayerGoal.canContinueToUse:()Z");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanLookForPlayerGoal.tick:()V");
        }

        protected EndermanLookForPlayerGoal() {
        }
    }

    private static class EndermanTakeBlockGoal extends Goal {

        public EndermanTakeBlockGoal(EnderMan enderman) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanTakeBlockGoal.canUse:()Z");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/EnderMan$EndermanTakeBlockGoal.tick:()V");
        }

        protected EndermanTakeBlockGoal() {
        }
    }

    public EnderMan() {
    }
}
