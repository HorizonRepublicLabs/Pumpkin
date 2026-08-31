package net.minecraft.world.entity;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.debug.DebugValueSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class Mob extends LivingEntity implements Targeting, EquipmentUser, Leashable {

    protected int xpReward;

    public final GoalSelector goalSelector = null;

    protected Mob(EntityType<? extends Mob> type, Level level) {
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.registerGoals:()V");
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.createMobAttributes:()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;");
    }

    public float getPathfindingMalus(PathType pathType) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getPathfindingMalus:(Lnet/minecraft/world/level/pathfinder/PathType;)F");
    }

    public void setPathfindingMalus(PathType pathType, float cost) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.setPathfindingMalus:(Lnet/minecraft/world/level/pathfinder/PathType;F)V");
    }

    public LookControl getLookControl() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getLookControl:()Lnet/minecraft/world/entity/ai/control/LookControl;");
    }

    public PathNavigation getNavigation() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getNavigation:()Lnet/minecraft/world/entity/ai/navigation/PathNavigation;");
    }

    public LivingEntity getControllingPassenger() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getControllingPassenger:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public LivingEntity getTarget() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getTarget:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public LivingEntity getTargetUnchecked() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getTargetUnchecked:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public void setTarget(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.setTarget:(Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    public boolean canAttack(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.canAttack:(Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public void baseTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.baseTick:()V");
    }

    protected void playHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.playHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    protected int getBaseExperienceReward(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getBaseExperienceReward:(Lnet/minecraft/server/level/ServerLevel;)I");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.handleEntityEvent:(B)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.tick:()V");
    }

    protected void tickHeadTurn(float yBodyRotT) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.tickHeadTurn:(F)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void dropFromLootTable(ServerLevel level, DamageSource source, boolean playerKilled) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.dropFromLootTable:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;Z)V");
    }

    public final Optional<ResourceKey<LootTable>> getLootTable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getLootTable:()Ljava/util/Optional;");
    }

    public long getLootTableSeed() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getLootTableSeed:()J");
    }

    public void setSpeed(float speed) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.setSpeed:(F)V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.aiStep:()V");
    }

    public void checkDespawn() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.checkDespawn:()V");
    }

    protected final void serverAiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.serverAiStep:()V");
    }

    public int getMaxHeadXRot() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getMaxHeadXRot:()I");
    }

    public int getMaxFallDistance() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getMaxFallDistance:()I");
    }

    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean killedByPlayer) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.dropCustomDeathLoot:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;Z)V");
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.populateDefaultEquipmentSlots:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    public void setDropChance(EquipmentSlot slot, float percent) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.setDropChance:(Lnet/minecraft/world/entity/EquipmentSlot;F)V");
    }

    public boolean canPickUpLoot() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.canPickUpLoot:()Z");
    }

    protected boolean canDispenserEquipIntoSlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.canDispenserEquipIntoSlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.interact:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/InteractionResult;");
    }

    public <T extends Mob> T convertTo(EntityType<T> entityType, ConversionParams params, EntitySpawnReason spawnReason, ConversionParams.AfterConversion<T> afterConversion) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.convertTo:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/ConversionParams;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/ConversionParams$AfterConversion;)Lnet/minecraft/world/entity/Mob;");
    }

    public <T extends Mob> T convertTo(EntityType<T> entityType, ConversionParams params, ConversionParams.AfterConversion<T> afterConversion) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.convertTo:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/ConversionParams;Lnet/minecraft/world/entity/ConversionParams$AfterConversion;)Lnet/minecraft/world/entity/Mob;");
    }

    public Leashable.LeashData getLeashData() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getLeashData:()Lnet/minecraft/world/entity/Leashable$LeashData;");
    }

    public void setLeashData(Leashable.LeashData leashData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.setLeashData:(Lnet/minecraft/world/entity/Leashable$LeashData;)V");
    }

    public void onLeashRemoved() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.onLeashRemoved:()V");
    }

    public void leashTooFarBehaviour() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.leashTooFarBehaviour:()V");
    }

    public boolean canBeLeashed() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.canBeLeashed:()Z");
    }

    public boolean startRiding(Entity entity, boolean force, boolean sendEventAndTriggers) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.startRiding:(Lnet/minecraft/world/entity/Entity;ZZ)Z");
    }

    public boolean isEffectiveAi() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.isEffectiveAi:()Z");
    }

    public HumanoidArm getMainArm() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getMainArm:()Lnet/minecraft/world/entity/HumanoidArm;");
    }

    public boolean doHurtTarget(ServerLevel level, Entity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.doHurtTarget:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;)Z");
    }

    protected void jumpInLiquid(TagKey<Fluid> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.jumpInLiquid:(Lnet/minecraft/tags/TagKey;)V");
    }

    public void jumpInFluid(net.neoforged.neoforge.fluids.FluidType type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.jumpInFluid:(Lnet/neoforged/neoforge/fluids/FluidType;)V");
    }

    protected void removeAfterChangingDimensions() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.removeAfterChangingDimensions:()V");
    }

    public ItemStack getPickResult() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.getPickResult:()Lnet/minecraft/world/item/ItemStack;");
    }

    protected void onAttributeUpdated(Holder<Attribute> attribute) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.onAttributeUpdated:(Lnet/minecraft/core/Holder;)V");
    }

    public void registerDebugValues(ServerLevel level, DebugValueSource.Registration registration) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Mob.registerDebugValues:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/debug/DebugValueSource$Registration;)V");
    }

    public Mob() {
    }
}
