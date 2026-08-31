package net.minecraft.world.entity.decoration;

import net.minecraft.core.Rotations;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ArmorStand extends LivingEntity {

    public ArmorStand(EntityType<? extends ArmorStand> type, Level level) {
    }

    public ArmorStand(Level level, double x, double y, double z) {
    }

    public void refreshDimensions() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.refreshDimensions:()V");
    }

    public boolean isEffectiveAi() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.isEffectiveAi:()Z");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public boolean canUseSlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.canUseSlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public boolean isPushable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.isPushable:()Z");
    }

    protected void doPush(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.doPush:(Lnet/minecraft/world/entity/Entity;)V");
    }

    protected void pushEntities() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.pushEntities:()V");
    }

    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.interact:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/InteractionResult;");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.handleEntityEvent:(B)V");
    }

    public boolean shouldRenderAtSqrDistance(double distance) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.shouldRenderAtSqrDistance:(D)Z");
    }

    protected void tickHeadTurn(float yBodyRotT) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.tickHeadTurn:(F)V");
    }

    public void travel(Vec3 input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.travel:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    public void setYBodyRot(float yBodyRot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.setYBodyRot:(F)V");
    }

    public void setYHeadRot(float yHeadRot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.setYHeadRot:(F)V");
    }

    protected void updateInvisibilityStatus() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.updateInvisibilityStatus:()V");
    }

    public void setInvisible(boolean invisible) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.setInvisible:(Z)V");
    }

    public boolean isBaby() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.isBaby:()Z");
    }

    public void kill(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.kill:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public boolean ignoreExplosion(Explosion explosion) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.ignoreExplosion:(Lnet/minecraft/world/level/Explosion;)Z");
    }

    public PushReaction getPistonPushReaction() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.getPistonPushReaction:()Lnet/minecraft/world/level/material/PushReaction;");
    }

    public boolean isIgnoringBlockTriggers() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.isIgnoringBlockTriggers:()Z");
    }

    public boolean isPickable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.isPickable:()Z");
    }

    public boolean skipAttackInteraction(Entity source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.skipAttackInteraction:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public HumanoidArm getMainArm() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.getMainArm:()Lnet/minecraft/world/entity/HumanoidArm;");
    }

    public LivingEntity.Fallsounds getFallSounds() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.getFallSounds:()Lnet/minecraft/world/entity/LivingEntity$Fallsounds;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public void thunderHit(ServerLevel level, LightningBolt lightningBolt) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.thunderHit:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LightningBolt;)V");
    }

    public boolean isAffectedByPotions() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.isAffectedByPotions:()Z");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public boolean attackable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.attackable:()Z");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public Vec3 getLightProbePosition(float partialTickTime) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.getLightProbePosition:(F)Lnet/minecraft/world/phys/Vec3;");
    }

    public ItemStack getPickResult() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.getPickResult:()Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean canBeSeenByAnyone() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/ArmorStand.canBeSeenByAnyone:()Z");
    }

    public record ArmorStandPose(Rotations head, Rotations body, Rotations leftArm, Rotations rightArm, Rotations leftLeg, Rotations rightLeg) {
    }

    public ArmorStand() {
    }
}
