package net.minecraft.world.entity.animal.nautilus;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractNautilus extends TamableAnimal implements PlayerRideableJumping, HasCustomInventoryScreen {

    protected AbstractNautilus(EntityType<? extends AbstractNautilus> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    public boolean isFood(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.isFood:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected void usePlayerItem(Player player, InteractionHand hand, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.usePlayerItem:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public boolean isPushedByFluid() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.isPushedByFluid:()Z");
    }

    protected PathNavigation createNavigation(Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.createNavigation:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/ai/navigation/PathNavigation;");
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.getWalkTargetValue:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/LevelReader;)F");
    }

    public boolean checkSpawnObstruction(LevelReader level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.checkSpawnObstruction:(Lnet/minecraft/world/level/LevelReader;)Z");
    }

    public boolean canUseSlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.canUseSlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    protected boolean canDispenserEquipIntoSlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.canDispenserEquipIntoSlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    protected boolean canAddPassenger(Entity passenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.canAddPassenger:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public LivingEntity getControllingPassenger() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.getControllingPassenger:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    protected Vec3 getRiddenInput(Player controller, Vec3 selfInput) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.getRiddenInput:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;");
    }

    protected void tickRidden(Player controller, Vec3 riddenInput) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.tickRidden:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/Vec3;)V");
    }

    protected void travelInWater(Vec3 input, double baseGravity, boolean isFalling, double oldY) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.travelInWater:(Lnet/minecraft/world/phys/Vec3;DZD)V");
    }

    protected float getRiddenSpeed(Player controller) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.getRiddenSpeed:(Lnet/minecraft/world/entity/player/Player;)F");
    }

    protected void customServerAiStep(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.customServerAiStep:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.tick:()V");
    }

    public boolean canJump() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.canJump:()Z");
    }

    public void onPlayerJump(int jumpAmount) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.onPlayerJump:(I)V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public void handleStartJump(int jumpScale) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.handleStartJump:(I)V");
    }

    public int getJumpCooldown() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.getJumpCooldown:()I");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public void handleStopJump() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.handleStopJump:()V");
    }

    protected void playStepSound(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.playStepSound:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.interact:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/InteractionResult;");
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public boolean removeWhenFarAway(double distSqr) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.removeWhenFarAway:(D)Z");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public boolean canBeAffected(MobEffectInstance newEffect) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.canBeAffected:(Lnet/minecraft/world/effect/MobEffectInstance;)Z");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    protected Holder<SoundEvent> getEquipSound(EquipmentSlot slot, ItemStack stack, Equippable equippable) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.getEquipSound:(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/equipment/Equippable;)Lnet/minecraft/core/Holder;");
    }

    public void openCustomInventoryScreen(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.openCustomInventoryScreen:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    public boolean requiresCustomPersistence() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/nautilus/AbstractNautilus.requiresCustomPersistence:()Z");
    }

    protected AbstractNautilus() {
    }
}
