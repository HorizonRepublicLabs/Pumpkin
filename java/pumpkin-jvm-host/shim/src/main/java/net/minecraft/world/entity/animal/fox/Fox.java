package net.minecraft.world.entity.animal.fox;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.StrollThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class Fox extends Animal {

    public Fox(EntityType<? extends Fox> type, Level level) {
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.registerGoals:()V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.aiStep:()V");
    }

    protected boolean isImmobile() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.isImmobile:()Z");
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.populateDefaultEquipmentSlots:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.handleEntityEvent:(B)V");
    }

    public Fox getBreedOffspring(ServerLevel level, AgeableMob partner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.getBreedOffspring:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/fox/Fox;");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    protected void playEatingSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.playEatingSound:()V");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.applyImplicitComponent:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Z");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public boolean isSleeping() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.isSleeping:()Z");
    }

    protected boolean canDispenserEquipIntoSlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.canDispenserEquipIntoSlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    public boolean canHoldItem(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.canHoldItem:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected void pickUpItem(ServerLevel level, ItemEntity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.pickUpItem:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/item/ItemEntity;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.tick:()V");
    }

    public boolean isFood(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.isFood:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected void onOffspringSpawnedFromEgg(Player spawner, Mob offspring) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.onOffspringSpawnedFromEgg:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Mob;)V");
    }

    public boolean isCrouching() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.isCrouching:()Z");
    }

    public void setTarget(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.setTarget:(Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    public void playAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.playAmbientSound:()V");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected void dropAllDeathLoot(ServerLevel level, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.dropAllDeathLoot:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    protected void dropEquipment(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.dropEquipment:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public Vec3 getLeashOffset() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox.getLeashOffset:()Lnet/minecraft/world/phys/Vec3;");
    }

    private class DefendTrustedTargetGoal extends NearestAttackableTargetGoal<LivingEntity> {

        public DefendTrustedTargetGoal(Class<LivingEntity> targetType, boolean mustSee, boolean mustReach, TargetingConditions.Selector subselector) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$DefendTrustedTargetGoal.canUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$DefendTrustedTargetGoal.start:()V");
        }

        protected DefendTrustedTargetGoal() {
        }
    }

    private class FaceplantGoal extends Goal {

        public FaceplantGoal() {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FaceplantGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FaceplantGoal.canContinueToUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FaceplantGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FaceplantGoal.stop:()V");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FaceplantGoal.tick:()V");
        }
    }

    public class FoxAlertableEntitiesSelector implements TargetingConditions.Selector {

        public boolean test(LivingEntity target, ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxAlertableEntitiesSelector.test:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/server/level/ServerLevel;)Z");
        }

        public FoxAlertableEntitiesSelector() {
        }
    }

    private abstract class FoxBehaviorGoal extends Goal {

        protected FoxBehaviorGoal() {
        }
    }

    private class FoxBreedGoal extends BreedGoal {

        public FoxBreedGoal(double speedModifier) {
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxBreedGoal.start:()V");
        }

        protected void breed() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxBreedGoal.breed:()V");
        }

        protected FoxBreedGoal() {
        }
    }

    public class FoxEatBerriesGoal extends MoveToBlockGoal {

        public FoxEatBerriesGoal(double speedModifier, int searchRange, int verticalSearchRange) {
        }

        public double acceptedDistance() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxEatBerriesGoal.acceptedDistance:()D");
        }

        public boolean shouldRecalculatePath() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxEatBerriesGoal.shouldRecalculatePath:()Z");
        }

        protected boolean isValidTarget(LevelReader level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxEatBerriesGoal.isValidTarget:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxEatBerriesGoal.tick:()V");
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxEatBerriesGoal.canUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxEatBerriesGoal.start:()V");
        }

        public FoxEatBerriesGoal() {
        }
    }

    private class FoxFloatGoal extends FloatGoal {

        public FoxFloatGoal() {
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxFloatGoal.start:()V");
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxFloatGoal.canUse:()Z");
        }
    }

    private static class FoxFollowParentGoal extends FollowParentGoal {

        public FoxFollowParentGoal(Fox fox, double speedModifier) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxFollowParentGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxFollowParentGoal.canContinueToUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxFollowParentGoal.start:()V");
        }

        protected FoxFollowParentGoal() {
        }
    }

    public static class FoxGroupData extends AgeableMob.AgeableMobGroupData {

        public FoxGroupData(Fox.Variant variant) {
        }

        public FoxGroupData() {
        }
    }

    private class FoxLookAtPlayerGoal extends LookAtPlayerGoal {

        public FoxLookAtPlayerGoal(Mob mob, Class<? extends LivingEntity> lookAtType, float lookDistance) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxLookAtPlayerGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxLookAtPlayerGoal.canContinueToUse:()Z");
        }

        protected FoxLookAtPlayerGoal() {
        }
    }

    public class FoxLookControl extends LookControl {

        public FoxLookControl() {
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxLookControl.tick:()V");
        }

        protected boolean resetXRotOnTick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxLookControl.resetXRotOnTick:()Z");
        }
    }

    private class FoxMeleeAttackGoal extends MeleeAttackGoal {

        public FoxMeleeAttackGoal(double speedModifier, boolean trackTarget) {
        }

        protected void checkAndPerformAttack(LivingEntity target) {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxMeleeAttackGoal.checkAndPerformAttack:(Lnet/minecraft/world/entity/LivingEntity;)V");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxMeleeAttackGoal.start:()V");
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxMeleeAttackGoal.canUse:()Z");
        }

        protected FoxMeleeAttackGoal() {
        }
    }

    private static class FoxMoveControl<T extends Fox> extends MoveControl<T> {

        public FoxMoveControl(T fox) {
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxMoveControl.tick:()V");
        }

        protected FoxMoveControl() {
        }
    }

    private class FoxPanicGoal extends PanicGoal {

        public FoxPanicGoal(double speedModifier) {
        }

        public boolean shouldPanic() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxPanicGoal.shouldPanic:()Z");
        }

        protected FoxPanicGoal() {
        }
    }

    public class FoxPounceGoal extends JumpGoal {

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxPounceGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxPounceGoal.canContinueToUse:()Z");
        }

        public boolean isInterruptable() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxPounceGoal.isInterruptable:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxPounceGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxPounceGoal.stop:()V");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxPounceGoal.tick:()V");
        }

        public FoxPounceGoal() {
        }
    }

    private class FoxSearchForItemsGoal extends Goal {

        public FoxSearchForItemsGoal() {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxSearchForItemsGoal.canUse:()Z");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxSearchForItemsGoal.tick:()V");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxSearchForItemsGoal.start:()V");
        }
    }

    private class FoxStrollThroughVillageGoal extends StrollThroughVillageGoal {

        public FoxStrollThroughVillageGoal(int searchRadius, int interval) {
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxStrollThroughVillageGoal.start:()V");
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxStrollThroughVillageGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$FoxStrollThroughVillageGoal.canContinueToUse:()Z");
        }

        protected FoxStrollThroughVillageGoal() {
        }
    }

    private class PerchAndSearchGoal extends Fox.FoxBehaviorGoal {

        public PerchAndSearchGoal() {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$PerchAndSearchGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$PerchAndSearchGoal.canContinueToUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$PerchAndSearchGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$PerchAndSearchGoal.stop:()V");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$PerchAndSearchGoal.tick:()V");
        }
    }

    private class SeekShelterGoal extends FleeSunGoal {

        public SeekShelterGoal(double speedModifier) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$SeekShelterGoal.canUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$SeekShelterGoal.start:()V");
        }

        protected SeekShelterGoal() {
        }
    }

    private class SleepGoal extends Fox.FoxBehaviorGoal {

        public SleepGoal() {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$SleepGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$SleepGoal.canContinueToUse:()Z");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$SleepGoal.stop:()V");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$SleepGoal.start:()V");
        }
    }

    private class StalkPreyGoal extends Goal {

        public StalkPreyGoal() {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$StalkPreyGoal.canUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$StalkPreyGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$StalkPreyGoal.stop:()V");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$StalkPreyGoal.tick:()V");
        }
    }

    public enum Variant implements StringRepresentable {

        RED, SNOW;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$Variant.getSerializedName:()Ljava/lang/String;");
        }

        public int getId() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$Variant.getId:()I");
        }

        public static Fox.Variant byId(int id) {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/fox/Fox$Variant.byId:(I)Lnet/minecraft/world/entity/animal/fox/Fox$Variant;");
        }
    }

    public Fox() {
    }
}
