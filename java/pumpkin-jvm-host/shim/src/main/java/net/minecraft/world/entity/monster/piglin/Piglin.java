package net.minecraft.world.entity.monster.piglin;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class Piglin extends AbstractPiglin implements CrossbowAttackMob, InventoryCarrier {

    public Piglin(EntityType<? extends AbstractPiglin> type, Level level) {
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public SimpleContainer getInventory() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getInventory:()Lnet/minecraft/world/SimpleContainer;");
    }

    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean killedByPlayer) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.dropCustomDeathLoot:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;Z)V");
    }

    public SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    public boolean removeWhenFarAway(double distSqr) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.removeWhenFarAway:(D)Z");
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.populateDefaultEquipmentSlots:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    protected Brain<Piglin> makeBrain(Brain.Packed packedBrain) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.makeBrain:(Lnet/minecraft/world/entity/ai/Brain$Packed;)Lnet/minecraft/world/entity/ai/Brain;");
    }

    public Brain<Piglin> getBrain() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getBrain:()Lnet/minecraft/world/entity/ai/Brain;");
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public void setBaby(boolean baby) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.setBaby:(Z)V");
    }

    public boolean isBaby() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.isBaby:()Z");
    }

    protected boolean canHunt() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.canHunt:()Z");
    }

    protected void customServerAiStep(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.customServerAiStep:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    protected int getBaseExperienceReward(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getBaseExperienceReward:(Lnet/minecraft/server/level/ServerLevel;)I");
    }

    protected void finishConversion(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.finishConversion:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public TagKey<Item> getPreferredWeaponType() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getPreferredWeaponType:()Lnet/minecraft/tags/TagKey;");
    }

    public void setChargingCrossbow(boolean isCharging) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.setChargingCrossbow:(Z)V");
    }

    public void onCrossbowAttackPerformed() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.onCrossbowAttackPerformed:()V");
    }

    public PiglinArmPose getArmPose() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getArmPose:()Lnet/minecraft/world/entity/monster/piglin/PiglinArmPose;");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public void performRangedAttack(LivingEntity target, float power) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.performRangedAttack:(Lnet/minecraft/world/entity/LivingEntity;F)V");
    }

    public boolean canUseNonMeleeWeapon(ItemStack item) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.canUseNonMeleeWeapon:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean wantsToPickUp(ServerLevel level, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.wantsToPickUp:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected boolean canReplaceCurrentItem(ItemStack newItemStack, ItemStack currentItemStack, EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.canReplaceCurrentItem:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    protected void pickUpItem(ServerLevel level, ItemEntity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.pickUpItem:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/item/ItemEntity;)V");
    }

    public boolean startRiding(Entity entityToRide, boolean force, boolean sendEventAndTriggers) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.startRiding:(Lnet/minecraft/world/entity/Entity;ZZ)Z");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected void playStepSound(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.playStepSound:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    protected void playConvertedSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/Piglin.playConvertedSound:()V");
    }

    public Piglin() {
    }
}
