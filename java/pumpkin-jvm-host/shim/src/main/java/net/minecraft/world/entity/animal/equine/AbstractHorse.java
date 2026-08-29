package net.minecraft.world.entity.animal.equine;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractHorse extends Animal implements PlayerRideableJumping, HasCustomInventoryScreen, OwnableEntity {

    protected AbstractHorse(EntityType<? extends AbstractHorse> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.registerGoals:()V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public EntityReference<LivingEntity> getOwnerReference() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getOwnerReference:()Lnet/minecraft/world/entity/EntityReference;");
    }

    public void onElasticLeashPull() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.onElasticLeashPull:()V");
    }

    public boolean supportQuadLeash() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.supportQuadLeash:()Z");
    }

    public Vec3[] getQuadLeashOffsets() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getQuadLeashOffsets:()[Lnet/minecraft/world/phys/Vec3;");
    }

    public boolean canUseSlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.canUseSlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    protected boolean canDispenserEquipIntoSlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.canDispenserEquipIntoSlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    public boolean isPushable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.isPushable:()Z");
    }

    public boolean causeFallDamage(double fallDistance, float damageModifier, DamageSource damageSource) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.causeFallDamage:(DFLnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    protected Holder<SoundEvent> getEquipSound(EquipmentSlot slot, ItemStack stack, Equippable equippable) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getEquipSound:(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/equipment/Equippable;)Lnet/minecraft/core/Holder;");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    protected void playStepSound(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.playStepSound:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public int getMaxSpawnClusterSize() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getMaxSpawnClusterSize:()I");
    }

    protected float getSoundVolume() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getSoundVolume:()F");
    }

    public int getAmbientSoundInterval() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getAmbientSoundInterval:()I");
    }

    public void openCustomInventoryScreen(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.openCustomInventoryScreen:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public boolean isImmobile() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.isImmobile:()Z");
    }

    public boolean isFood(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.isFood:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected void dropEquipment(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.dropEquipment:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.aiStep:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.tick:()V");
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    protected void tickRidden(Player controller, Vec3 riddenInput) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.tickRidden:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/Vec3;)V");
    }

    protected void addPassenger(Entity passenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.addPassenger:(Lnet/minecraft/world/entity/Entity;)V");
    }

    protected Vec3 getRiddenInput(Player controller, Vec3 selfInput) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getRiddenInput:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;");
    }

    protected float getRiddenSpeed(Player controller) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getRiddenSpeed:(Lnet/minecraft/world/entity/player/Player;)F");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public boolean canMate(Animal partner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.canMate:(Lnet/minecraft/world/entity/animal/Animal;)Z");
    }

    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getBreedOffspring:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/AgeableMob;");
    }

    public void onPlayerJump(int jumpAmount) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.onPlayerJump:(I)V");
    }

    public boolean canJump() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.canJump:()Z");
    }

    public void handleStartJump(int jumpScale) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.handleStartJump:(I)V");
    }

    public void handleStopJump() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.handleStopJump:()V");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.handleEntityEvent:(B)V");
    }

    protected void positionRider(Entity passenger, Entity.MoveFunction moveFunction) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.positionRider:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity$MoveFunction;)V");
    }

    public boolean onClimbable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.onClimbable:()Z");
    }

    public SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    public LivingEntity getControllingPassenger() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getControllingPassenger:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getDismountLocationForPassenger:(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/phys/Vec3;");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    protected Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float scale) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse.getPassengerAttachmentPoint:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/EntityDimensions;F)Lnet/minecraft/world/phys/Vec3;");
    }

    private class MountPanicGoal extends PanicGoal {

        public MountPanicGoal(double speedModifier) {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse$MountPanicGoal.<init>:(D)V");
        }

        public boolean shouldPanic() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/equine/AbstractHorse$MountPanicGoal.shouldPanic:()Z");
        }

        protected MountPanicGoal() {
        }
    }

    public AbstractHorse() {
    }
}
