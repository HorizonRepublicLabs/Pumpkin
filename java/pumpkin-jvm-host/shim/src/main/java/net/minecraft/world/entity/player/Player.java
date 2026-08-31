package net.minecraft.world.entity.player;

import com.google.common.collect.ImmutableList;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.server.players.NameAndId;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stat;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEquipment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.IPlayerExtension;
import dev.pumpkin.shim.Unimplemented;

public abstract class Player extends Avatar implements ContainerUser, IPlayerExtension {

    public AbstractContainerMenu containerMenu;

    public int experienceLevel;

    public int totalExperience;

    public float experienceProgress;

    public Player(Level level, GameProfile gameProfile) {
    }

    protected EntityEquipment createEquipment() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.createEquipment:()Lnet/minecraft/world/entity/EntityEquipment;");
    }

    public boolean blockActionRestricted(Level level, BlockPos pos, GameType gameType) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.blockActionRestricted:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/GameType;)Z");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.tick:()V");
    }

    protected float getMaxHeadRotationRelativeToBody() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getMaxHeadRotationRelativeToBody:()F");
    }

    public void onAboveBubbleColumn(boolean dragDown, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.onAboveBubbleColumn:(ZLnet/minecraft/core/BlockPos;)V");
    }

    public void onInsideBubbleColumn(boolean dragDown) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.onInsideBubbleColumn:(Z)V");
    }

    protected SoundEvent getSwimSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getSwimSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getSwimSplashSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getSwimSplashSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getSwimHighSpeedSplashSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getSwimHighSpeedSplashSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public int getDimensionChangingDelay() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getDimensionChangingDelay:()I");
    }

    public void playSound(SoundEvent sound, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.playSound:(Lnet/minecraft/sounds/SoundEvent;FF)V");
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    protected int getFireImmuneTicks() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getFireImmuneTicks:()I");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.handleEntityEvent:(B)V");
    }

    public void closeContainer() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.closeContainer:()V");
    }

    public void rideTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.rideTick:()V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.aiStep:()V");
    }

    private void touch(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.touch:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public ItemStack getWeaponItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getWeaponItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    public void die(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.die:(Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    protected void dropEquipment(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.dropEquipment:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public ItemEntity drop(ItemStack itemStack, boolean thrownFromHand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.drop:(Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/item/ItemEntity;");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public boolean isInvulnerableTo(ServerLevel level, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isInvulnerableTo:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    protected void blockUsingItem(ServerLevel level, LivingEntity attacker, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.blockUsingItem:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    public boolean canBeSeenAsEnemy() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.canBeSeenAsEnemy:()Z");
    }

    public boolean canHarmPlayer(Player target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.canHarmPlayer:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    protected void hurtArmor(DamageSource damageSource, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.hurtArmor:(Lnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    protected void hurtHelmet(DamageSource damageSource, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.hurtHelmet:(Lnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    protected void actuallyHurt(ServerLevel level, DamageSource source, float dmg) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.actuallyHurt:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    public OptionalInt openMenu(MenuProvider provider) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.openMenu:(Lnet/minecraft/world/MenuProvider;)Ljava/util/OptionalInt;");
    }

    public void removeVehicle() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.removeVehicle:()V");
    }

    protected boolean isImmobile() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isImmobile:()Z");
    }

    public boolean isAffectedByFluids() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isAffectedByFluids:()Z");
    }

    protected Vec3 maybeBackOffFromEdge(Vec3 delta, MoverType moverType) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.maybeBackOffFromEdge:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/MoverType;)Lnet/minecraft/world/phys/Vec3;");
    }

    public void causeExtraKnockback(Entity entity, float knockbackAmount, Vec3 oldMovement, DamageSource damageSource, float damage, boolean comesFromEffect) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.causeExtraKnockback:(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/damagesource/DamageSource;FZ)V");
    }

    public float getVoicePitch() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getVoicePitch:()F");
    }

    protected void doAutoAttackOnTouch(LivingEntity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.doAutoAttackOnTouch:(Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    public boolean stabAttack(EquipmentSlot slot, Entity target, float baseDamage, boolean dealsDamage, boolean dealsKnockback, boolean dismounts) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.stabAttack:(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/entity/Entity;FZZZ)Z");
    }

    public void remove(Entity.RemovalReason reason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.remove:(Lnet/minecraft/world/entity/Entity$RemovalReason;)V");
    }

    public boolean isClientAuthoritative() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isClientAuthoritative:()Z");
    }

    protected boolean isLocalClientAuthoritative() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isLocalClientAuthoritative:()Z");
    }

    public boolean canSimulateMovement() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.canSimulateMovement:()Z");
    }

    public boolean isEffectiveAi() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isEffectiveAi:()Z");
    }

    public GameProfile getGameProfile() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getGameProfile:()Lcom/mojang/authlib/GameProfile;");
    }

    public NameAndId nameAndId() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.nameAndId:()Lnet/minecraft/server/players/NameAndId;");
    }

    public Inventory getInventory() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getInventory:()Lnet/minecraft/world/entity/player/Inventory;");
    }

    public Abilities getAbilities() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getAbilities:()Lnet/minecraft/world/entity/player/Abilities;");
    }

    public boolean hasInfiniteMaterials() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.hasInfiniteMaterials:()Z");
    }

    public void stopSleeping() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.stopSleeping:()V");
    }

    public void sendSystemMessage(Component message) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.sendSystemMessage:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void sendOverlayMessage(Component message) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.sendOverlayMessage:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void awardStat(Identifier location) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.awardStat:(Lnet/minecraft/resources/Identifier;)V");
    }

    public void awardStat(Identifier location, int count) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.awardStat:(Lnet/minecraft/resources/Identifier;I)V");
    }

    public void awardStat(Stat<?> stat) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.awardStat:(Lnet/minecraft/stats/Stat;)V");
    }

    public void awardStat(Stat<?> stat, int count) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.awardStat:(Lnet/minecraft/stats/Stat;I)V");
    }

    public int awardRecipes(Collection<RecipeHolder<?>> recipes) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.awardRecipes:(Ljava/util/Collection;)I");
    }

    public void triggerRecipeCrafted(RecipeHolder<?> recipe, List<ItemStack> itemStacks) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.triggerRecipeCrafted:(Lnet/minecraft/world/item/crafting/RecipeHolder;Ljava/util/List;)V");
    }

    public void travel(Vec3 input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.travel:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    protected boolean canGlide() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.canGlide:()Z");
    }

    public void updateSwimming() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.updateSwimming:()V");
    }

    public float getSpeed() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getSpeed:()F");
    }

    public boolean causeFallDamage(double fallDistance, float damageModifier, DamageSource damageSource) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.causeFallDamage:(DFLnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    protected void doWaterSplashEffect() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.doWaterSplashEffect:()V");
    }

    protected void playStepSound(BlockPos onPos, BlockState onState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.playStepSound:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public LivingEntity.Fallsounds getFallSounds() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getFallSounds:()Lnet/minecraft/world/entity/LivingEntity$Fallsounds;");
    }

    public boolean killedEntity(ServerLevel level, LivingEntity entity, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.killedEntity:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public void makeStuckInBlock(BlockState blockState, Vec3 speedMultiplier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.makeStuckInBlock:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public int getXpNeededForNextLevel() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getXpNeededForNextLevel:()I");
    }

    public FoodData getFoodData() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getFoodData:()Lnet/minecraft/world/food/FoodData;");
    }

    public boolean canEat(boolean canAlwaysEat) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.canEat:(Z)Z");
    }

    public boolean mayUseItemAt(BlockPos pos, Direction direction, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.mayUseItemAt:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected int getBaseExperienceReward(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getBaseExperienceReward:(Lnet/minecraft/server/level/ServerLevel;)I");
    }

    protected boolean isAlwaysExperienceDropper() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isAlwaysExperienceDropper:()Z");
    }

    public boolean shouldShowName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.shouldShowName:()Z");
    }

    protected Entity.MovementEmission getMovementEmission() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getMovementEmission:()Lnet/minecraft/world/entity/Entity$MovementEmission;");
    }

    public void onUpdateAbilities() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.onUpdateAbilities:()V");
    }

    public Component getName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getName:()Lnet/minecraft/network/chat/Component;");
    }

    public String getPlainTextName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getPlainTextName:()Ljava/lang/String;");
    }

    protected boolean doesEmitEquipEvent(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.doesEmitEquipEvent:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    public boolean addItem(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.addItem:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public abstract GameType gameMode();

    public boolean isSpectator() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isSpectator:()Z");
    }

    public boolean isPickable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isPickable:()Z");
    }

    public boolean isSwimming() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isSwimming:()Z");
    }

    public boolean isCreative() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isCreative:()Z");
    }

    public boolean isPushedByFluid() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isPushedByFluid:()Z");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public String getScoreboardName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getScoreboardName:()Ljava/lang/String;");
    }

    protected void internalSetAbsorptionAmount(float absorptionAmount) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.internalSetAbsorptionAmount:(F)V");
    }

    public float getAbsorptionAmount() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getAbsorptionAmount:()F");
    }

    public SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    public void setRemainingFireTicks(int remainingTicks) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.setRemainingFireTicks:(I)V");
    }

    public float getAttackStrengthScale(float a) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getAttackStrengthScale:(F)F");
    }

    public void onAttack() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.onAttack:()V");
    }

    public ItemCooldowns getCooldowns() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getCooldowns:()Lnet/minecraft/world/item/ItemCooldowns;");
    }

    protected float getBlockSpeedFactor() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getBlockSpeedFactor:()F");
    }

    public float getLuck() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getLuck:()F");
    }

    public PermissionSet permissions() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.permissions:()Lnet/minecraft/server/permissions/PermissionSet;");
    }

    public ImmutableList<Pose> getDismountPoses() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getDismountPoses:()Lcom/google/common/collect/ImmutableList;");
    }

    public ItemStack getProjectile(ItemStack heldWeapon) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getProjectile:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public Vec3 getRopeHoldPosition(float partialTickTime) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getRopeHoldPosition:(F)Lnet/minecraft/world/phys/Vec3;");
    }

    public boolean isAlwaysTicking() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.isAlwaysTicking:()Z");
    }

    public boolean shouldBeSaved() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.shouldBeSaved:()Z");
    }

    public float getHurtDir() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getHurtDir:()F");
    }

    public void animateHurt(float yaw) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.animateHurt:(F)V");
    }

    public boolean canSprint() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.canSprint:()Z");
    }

    protected float getFlyingSpeed() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getFlyingSpeed:()F");
    }

    public boolean hasContainerOpen(ContainerOpenersCounter container, BlockPos blockPos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.hasContainerOpen:(Lnet/minecraft/world/level/block/entity/ContainerOpenersCounter;Lnet/minecraft/core/BlockPos;)Z");
    }

    public double getContainerInteractionRange() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getContainerInteractionRange:()D");
    }

    public double blockInteractionRange() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.blockInteractionRange:()D");
    }

    public boolean onClimbable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.onClimbable:()Z");
    }

    public ResolvableProfile getProfile() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.getProfile:()Lnet/minecraft/world/item/component/ResolvableProfile;");
    }

    public DamageSource createDamageSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.createDamageSource:()Lnet/minecraft/world/damagesource/DamageSource;");
    }

    public record BedSleepingProblem(Component message) {
    }

    public Player() {
    }
}
