package net.minecraft.world.entity.monster.zombie;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class Drowned extends Zombie implements RangedAttackMob {

    public Drowned(EntityType<? extends Drowned> type, Level level) {
    }

    protected PathNavigation createNavigation(Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.createNavigation:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/ai/navigation/PathNavigation;");
    }

    protected void addBehaviourGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.addBehaviourGoals:()V");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getStepSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.getStepSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getSwimSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.getSwimSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected boolean canSpawnInLiquids() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.canSpawnInLiquids:()Z");
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.populateDefaultEquipmentSlots:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    protected boolean canReplaceCurrentItem(ItemStack newItemStack, ItemStack currentItemStack, EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.canReplaceCurrentItem:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    protected boolean convertsInWater() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.convertsInWater:()Z");
    }

    public boolean checkSpawnObstruction(LevelReader level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.checkSpawnObstruction:(Lnet/minecraft/world/level/LevelReader;)Z");
    }

    public boolean isPushedByFluid() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.isPushedByFluid:()Z");
    }

    protected void travelInWater(Vec3 input, double baseGravity, boolean isFalling, double oldY) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.travelInWater:(Lnet/minecraft/world/phys/Vec3;DZD)V");
    }

    public void updateSwimming() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.updateSwimming:()V");
    }

    public boolean isVisuallySwimming() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.isVisuallySwimming:()Z");
    }

    public void performRangedAttack(LivingEntity target, float power) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.performRangedAttack:(Lnet/minecraft/world/entity/LivingEntity;F)V");
    }

    public TagKey<Item> getPreferredWeaponType() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.getPreferredWeaponType:()Lnet/minecraft/tags/TagKey;");
    }

    public void rideTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.rideTick:()V");
    }

    public boolean wantsToPickUp(ServerLevel level, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned.wantsToPickUp:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    private static class DrownedAttackGoal extends ZombieAttackGoal {

        public DrownedAttackGoal(Drowned drowned, double speedModifier, boolean trackTarget) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedAttackGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedAttackGoal.canContinueToUse:()Z");
        }

        protected DrownedAttackGoal() {
        }
    }

    private static class DrownedGoToBeachGoal extends MoveToBlockGoal {

        public DrownedGoToBeachGoal(Drowned drowned, double speedModifier) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedGoToBeachGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedGoToBeachGoal.canContinueToUse:()Z");
        }

        protected boolean isValidTarget(LevelReader level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedGoToBeachGoal.isValidTarget:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedGoToBeachGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedGoToBeachGoal.stop:()V");
        }

        protected DrownedGoToBeachGoal() {
        }
    }

    private static class DrownedGoToWaterGoal extends Goal {

        public DrownedGoToWaterGoal(PathfinderMob mob, double speedModifier) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedGoToWaterGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedGoToWaterGoal.canContinueToUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedGoToWaterGoal.start:()V");
        }

        protected DrownedGoToWaterGoal() {
        }
    }

    private static class DrownedMoveControl<T extends Drowned> extends MoveControl<T> {

        public DrownedMoveControl(T drowned) {
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedMoveControl.tick:()V");
        }

        protected DrownedMoveControl() {
        }
    }

    private static class DrownedSwimUpGoal extends Goal {

        public DrownedSwimUpGoal(Drowned drowned, double speedModifier, int seaLevel) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedSwimUpGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedSwimUpGoal.canContinueToUse:()Z");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedSwimUpGoal.tick:()V");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedSwimUpGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedSwimUpGoal.stop:()V");
        }

        protected DrownedSwimUpGoal() {
        }
    }

    private static class DrownedTridentAttackGoal extends RangedAttackGoal {

        public DrownedTridentAttackGoal(RangedAttackMob mob, double speedModifier, int attackInterval, float attackRadius) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedTridentAttackGoal.canUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedTridentAttackGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/zombie/Drowned$DrownedTridentAttackGoal.stop:()V");
        }

        protected DrownedTridentAttackGoal() {
        }
    }

    public Drowned() {
    }
}
