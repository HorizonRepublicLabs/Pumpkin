package net.minecraft.world.entity.monster;

import java.util.Optional;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class Shulker extends AbstractGolem implements Enemy {

    public Shulker(EntityType<? extends Shulker> type, Level level) {
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.registerGoals:()V");
    }

    protected Entity.MovementEmission getMovementEmission() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getMovementEmission:()Lnet/minecraft/world/entity/Entity$MovementEmission;");
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public void playAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.playAmbientSound:()V");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected BodyRotationControl createBodyControl() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.createBodyControl:()Lnet/minecraft/world/entity/ai/control/BodyRotationControl;");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.tick:()V");
    }

    protected AABB makeBoundingBox(Vec3 position) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.makeBoundingBox:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/AABB;");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public boolean startRiding(Entity entity, boolean force, boolean sendEventAndTriggers) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.startRiding:(Lnet/minecraft/world/entity/Entity;ZZ)Z");
    }

    public void stopRiding() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.stopRiding:()V");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    public void move(MoverType moverType, Vec3 delta) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.move:(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public Vec3 getDeltaMovement() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getDeltaMovement:()Lnet/minecraft/world/phys/Vec3;");
    }

    public void setDeltaMovement(Vec3 deltaMovement) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.setDeltaMovement:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    public void setPos(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.setPos:(DDD)V");
    }

    public InterpolationHandler getInterpolation() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getInterpolation:()Lnet/minecraft/world/entity/InterpolationHandler;");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    private boolean isClosed() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.isClosed:()Z");
    }

    public boolean canBeCollidedWith(Entity other) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.canBeCollidedWith:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.recreateFromPacket:(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V");
    }

    public int getMaxHeadXRot() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getMaxHeadXRot:()I");
    }

    public int getMaxHeadYRot() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getMaxHeadYRot:()I");
    }

    public void push(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.push:(Lnet/minecraft/world/entity/Entity;)V");
    }

    protected float sanitizeScale(float scale) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.sanitizeScale:(F)F");
    }

    public DyeColor getColor() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.getColor:()Lnet/minecraft/world/item/DyeColor;");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker.applyImplicitComponent:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Z");
    }

    private class ShulkerAttackGoal extends Goal {

        public ShulkerAttackGoal() {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerAttackGoal.canUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerAttackGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerAttackGoal.stop:()V");
        }

        public boolean requiresUpdateEveryTick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerAttackGoal.requiresUpdateEveryTick:()Z");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerAttackGoal.tick:()V");
        }
    }

    private static class ShulkerBodyRotationControl extends BodyRotationControl {

        public ShulkerBodyRotationControl(Mob mob) {
        }

        public void clientTick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerBodyRotationControl.clientTick:()V");
        }

        protected ShulkerBodyRotationControl() {
        }
    }

    private static class ShulkerDefenseAttackGoal extends NearestAttackableTargetGoal<LivingEntity> {

        public ShulkerDefenseAttackGoal(Shulker mob) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerDefenseAttackGoal.canUse:()Z");
        }

        protected AABB getTargetSearchArea(double followDistance) {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerDefenseAttackGoal.getTargetSearchArea:(D)Lnet/minecraft/world/phys/AABB;");
        }

        protected ShulkerDefenseAttackGoal() {
        }
    }

    private class ShulkerLookControl extends LookControl {

        public ShulkerLookControl(Mob mob) {
        }

        protected void clampHeadRotationToBody() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerLookControl.clampHeadRotationToBody:()V");
        }

        protected Optional<Float> getYRotD() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerLookControl.getYRotD:()Ljava/util/Optional;");
        }

        protected Optional<Float> getXRotD() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerLookControl.getXRotD:()Ljava/util/Optional;");
        }

        protected ShulkerLookControl() {
        }
    }

    private class ShulkerNearestAttackGoal extends NearestAttackableTargetGoal<Player> {

        public ShulkerNearestAttackGoal(Shulker mob) {
        }

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerNearestAttackGoal.canUse:()Z");
        }

        protected AABB getTargetSearchArea(double followDistance) {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerNearestAttackGoal.getTargetSearchArea:(D)Lnet/minecraft/world/phys/AABB;");
        }

        protected ShulkerNearestAttackGoal() {
        }
    }

    private class ShulkerPeekGoal extends Goal {

        public boolean canUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerPeekGoal.canUse:()Z");
        }

        public boolean canContinueToUse() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerPeekGoal.canContinueToUse:()Z");
        }

        public void start() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerPeekGoal.start:()V");
        }

        public void stop() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerPeekGoal.stop:()V");
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/monster/Shulker$ShulkerPeekGoal.tick:()V");
        }

        protected ShulkerPeekGoal() {
        }
    }

    public Shulker() {
    }
}
