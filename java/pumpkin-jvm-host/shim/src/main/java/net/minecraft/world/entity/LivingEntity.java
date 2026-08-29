package net.minecraft.world.entity;

import java.util.Optional;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.BlockUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.waypoints.Waypoint;
import net.minecraft.world.waypoints.WaypointTransmitter;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import dev.pumpkin.shim.Unimplemented;

public abstract class LivingEntity extends Entity implements Attackable, WaypointTransmitter, ILivingEntityExtension {

    protected LivingEntity(EntityType<? extends LivingEntity> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    public LivingEntity asLivingEntity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.asLivingEntity:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public void kill(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.kill:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void checkFallDamage(double ya, boolean onGround, BlockState onState, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.checkFallDamage:(DZLnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)V");
    }

    public void baseTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.baseTick:()V");
    }

    protected float getBlockSpeedFactor() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getBlockSpeedFactor:()F");
    }

    public LivingEntity getLastAttacker() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getLastAttacker:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public void remove(Entity.RemovalReason reason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.remove:(Lnet/minecraft/world/entity/Entity$RemovalReason;)V");
    }

    public void onRemoval(Entity.RemovalReason reason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.onRemoval:(Lnet/minecraft/world/entity/Entity$RemovalReason;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void updateDataBeforeSync() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.updateDataBeforeSync:()V");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public long getLootTableSeed() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getLootTableSeed:()J");
    }

    public void knockback(double power, double xd, double zd, DamageSource source, float damage, boolean comesFromEffect) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.knockback:(DDDLnet/minecraft/world/damagesource/DamageSource;FZ)V");
    }

    public void knockback(double power, double xd, double zd, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.knockback:(DDDLnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    public boolean isAlive() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.isAlive:()Z");
    }

    public int getMaxFallDistance() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getMaxFallDistance:()I");
    }

    public boolean causeFallDamage(double fallDistance, float damageModifier, DamageSource damageSource) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.causeFallDamage:(DFLnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public void animateHurt(float yaw) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.animateHurt:(F)V");
    }

    public void handleDamageEvent(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.handleDamageEvent:(Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.handleEntityEvent:(B)V");
    }

    protected void onBelowWorld() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.onBelowWorld:()V");
    }

    protected double getEntityBounciness() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getEntityBounciness:()D");
    }

    public ItemStack getWeaponItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getWeaponItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack getItemBySlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getItemBySlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;");
    }

    public void setItemSlot(EquipmentSlot slot, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.setItemSlot:(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void setSprinting(boolean isSprinting) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.setSprinting:(Z)V");
    }

    public void push(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.push:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public boolean shouldShowName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.shouldShowName:()Z");
    }

    protected double getDefaultGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getDefaultGravity:()D");
    }

    protected double getEffectiveGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getEffectiveGravity:()D");
    }

    protected float getAirDrag() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getAirDrag:()F");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.tick:()V");
    }

    public void stopRiding() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.stopRiding:()V");
    }

    public void rideTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.rideTick:()V");
    }

    public InterpolationHandler getInterpolation() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getInterpolation:()Lnet/minecraft/world/entity/InterpolationHandler;");
    }

    public void lerpHeadTo(float yRot, int steps) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.lerpHeadTo:(FI)V");
    }

    public float getViewYRot(float a) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getViewYRot:(F)F");
    }

    public boolean isPickable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.isPickable:()Z");
    }

    public boolean isPushable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.isPushable:()Z");
    }

    public float getYHeadRot() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getYHeadRot:()F");
    }

    public void setYHeadRot(float yHeadRot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.setYHeadRot:(F)V");
    }

    public void setYBodyRot(float yBodyRot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.setYBodyRot:(F)V");
    }

    public Vec3 getRelativePortalPosition(Direction.Axis axis, BlockUtil.FoundRectangle portalArea) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getRelativePortalPosition:(Lnet/minecraft/core/Direction$Axis;Lnet/minecraft/util/BlockUtil$FoundRectangle;)Lnet/minecraft/world/phys/Vec3;");
    }

    public abstract HumanoidArm getMainArm();

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public void lookAt(EntityAnchorArgument.Anchor anchor, Vec3 pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.lookAt:(Lnet/minecraft/commands/arguments/EntityAnchorArgument$Anchor;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public float getPreciseBodyRotation(float partial) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getPreciseBodyRotation:(F)F");
    }

    public ItemStack getUseItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getUseItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    public int getUseItemRemainingTicks() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getUseItemRemainingTicks:()I");
    }

    public void releaseUsingItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.releaseUsingItem:()V");
    }

    public boolean isVisuallySwimming() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.isVisuallySwimming:()Z");
    }

    public final EntityDimensions getDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public boolean canUsePortal(boolean ignorePassenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.canUsePortal:(Z)Z");
    }

    public boolean isInWall() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.isInWall:()Z");
    }

    public SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    public boolean canFreeze() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.canFreeze:()Z");
    }

    public boolean isCurrentlyGlowing() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.isCurrentlyGlowing:()Z");
    }

    public float getVisualRotationYInDegrees() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getVisualRotationYInDegrees:()F");
    }

    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.recreateFromPacket:(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V");
    }

    public float maxUpStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.maxUpStep:()F");
    }

    public Vec3 getPassengerRidingPosition(Entity passenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.getPassengerRidingPosition:(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/phys/Vec3;");
    }

    public void igniteForTicks(int numberOfTicks) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.igniteForTicks:(I)V");
    }

    public boolean hasInfiniteMaterials() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.hasInfiniteMaterials:()Z");
    }

    public boolean isTransmittingWaypoint() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.isTransmittingWaypoint:()Z");
    }

    public Optional<WaypointTransmitter.Connection> makeWaypointConnectionWith(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.makeWaypointConnectionWith:(Lnet/minecraft/server/level/ServerPlayer;)Ljava/util/Optional;");
    }

    public Waypoint.Icon waypointIcon() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LivingEntity.waypointIcon:()Lnet/minecraft/world/waypoints/Waypoint$Icon;");
    }

    public record Fallsounds(SoundEvent small, SoundEvent big) {
    }

    protected LivingEntity() {
    }
}
