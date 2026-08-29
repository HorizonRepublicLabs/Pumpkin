package net.minecraft.world.entity;

import com.google.common.collect.ImmutableList.Builder;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.TypedInstance;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SyncedDataHolder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.RandomSource;
import net.minecraft.util.debug.DebugValueSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.level.entity.EntityInLevelCallback;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Team;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import dev.pumpkin.shim.Unimplemented;

public abstract class Entity extends net.neoforged.neoforge.attachment.AttachmentHolder implements Nameable, EntityAccess, ScoreHolder, SyncedDataHolder, DataComponentGetter, ItemOwner, SlotProvider, DebugValueSource, TypedInstance<EntityType<?>>, IEntityExtension {

    private Level level;

    private Vec3 position;

    private boolean onGround;

    public Entity(EntityType<?> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    public boolean isSpectator() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isSpectator:()Z");
    }

    public EntityType<?> getType() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getType:()Lnet/minecraft/world/entity/EntityType;");
    }

    public Holder<EntityType<?>> typeHolder() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getId:()I");
    }

    public boolean addTag(String tag) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.addTag:(Ljava/lang/String;)Z");
    }

    public final void discard() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.discard:()V");
    }

    protected abstract void defineSynchedData(SynchedEntityData.Builder entityData);

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.hashCode:()I");
    }

    public void remove(Entity.RemovalReason reason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.remove:(Lnet/minecraft/world/entity/Entity$RemovalReason;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.tick:()V");
    }

    public void clearFire() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.clearFire:()V");
    }

    public boolean onGround() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.onGround:()Z");
    }

    public BlockPos getOnPos() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getOnPos:()Lnet/minecraft/core/BlockPos;");
    }

    protected BlockPos getOnPos(float offset) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getOnPos:(F)Lnet/minecraft/core/BlockPos;");
    }

    public void playSound(SoundEvent sound, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.playSound:(Lnet/minecraft/sounds/SoundEvent;FF)V");
    }

    public void playSound(SoundEvent sound) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.playSound:(Lnet/minecraft/sounds/SoundEvent;)V");
    }

    public boolean isSilent() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isSilent:()Z");
    }

    public boolean causeFallDamage(double fallDistance, float damageModifier, DamageSource damageSource) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.causeFallDamage:(DFLnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public boolean isInWater() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isInWater:()Z");
    }

    public void moveRelative(float speed, Vec3 input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.moveRelative:(FLnet/minecraft/world/phys/Vec3;)V");
    }

    public double distanceToSqr(double x2, double y2, double z2) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.distanceToSqr:(DDD)D");
    }

    public double distanceToSqr(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.distanceToSqr:(Lnet/minecraft/world/entity/Entity;)D");
    }

    public double distanceToSqr(Vec3 pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.distanceToSqr:(Lnet/minecraft/world/phys/Vec3;)D");
    }

    public void push(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.push:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void push(Vec3 impulse) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.push:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    public final void hurt(DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.hurt:(Lnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    public abstract boolean hurtServer(ServerLevel level, DamageSource source, float damage);

    public float getXRot(float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getXRot:(F)F");
    }

    public float getYRot(float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getYRot:(F)F");
    }

    public final Vec3 getEyePosition() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getEyePosition:()Lnet/minecraft/world/phys/Vec3;");
    }

    public final Vec3 getEyePosition(float partialTickTime) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getEyePosition:(F)Lnet/minecraft/world/phys/Vec3;");
    }

    public final Vec3 getPosition(float partialTickTime) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getPosition:(F)Lnet/minecraft/world/phys/Vec3;");
    }

    public void load(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.load:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected abstract void readAdditionalSaveData(ValueInput input);

    protected abstract void addAdditionalSaveData(ValueOutput output);

    public boolean isAlive() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isAlive:()Z");
    }

    public void handleDamageEvent(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.handleDamageEvent:(Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.handleEntityEvent:(B)V");
    }

    public boolean isOnFire() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isOnFire:()Z");
    }

    public boolean isShiftKeyDown() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isShiftKeyDown:()Z");
    }

    public boolean isDescending() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isDescending:()Z");
    }

    public boolean isCrouching() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isCrouching:()Z");
    }

    public boolean isSprinting() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isSprinting:()Z");
    }

    public boolean isSwimming() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isSwimming:()Z");
    }

    public final boolean isAlliedTo(Entity other) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isAlliedTo:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean isAlliedTo(Team other) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isAlliedTo:(Lnet/minecraft/world/scores/Team;)Z");
    }

    public Component getName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getName:()Lnet/minecraft/network/chat/Component;");
    }

    public boolean is(Entity other) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.is:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.toString:()Ljava/lang/String;");
    }

    public void forceSetRotation(float yRot, boolean relativeY, float xRot, boolean relativeX) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.forceSetRotation:(FZFZ)V");
    }

    public void setUUID(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.setUUID:(Ljava/util/UUID;)V");
    }

    public UUID getUUID() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getUUID:()Ljava/util/UUID;");
    }

    public String getScoreboardName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getScoreboardName:()Ljava/lang/String;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public Component getCustomName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getCustomName:()Lnet/minecraft/network/chat/Component;");
    }

    public boolean hasCustomName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.hasCustomName:()Z");
    }

    public boolean teleportTo(ServerLevel level, double x, double y, double z, Set<Relative> relatives, float newYRot, float newXRot, boolean resetCamera) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.teleportTo:(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FFZ)Z");
    }

    public void teleportTo(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.teleportTo:(DDD)V");
    }

    public void onSyncedDataUpdated(List<SynchedEntityData.DataValue<?>> updatedItems) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.onSyncedDataUpdated:(Ljava/util/List;)V");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public Direction getDirection() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getDirection:()Lnet/minecraft/core/Direction;");
    }

    public final AABB getBoundingBox() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getBoundingBox:()Lnet/minecraft/world/phys/AABB;");
    }

    public SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    public Stream<Entity> getSelfAndPassengers() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getSelfAndPassengers:()Ljava/util/stream/Stream;");
    }

    public Stream<Entity> getPassengersAndSelf() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getPassengersAndSelf:()Ljava/util/stream/Stream;");
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public final float getBbWidth() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getBbWidth:()F");
    }

    public final float getBbHeight() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getBbHeight:()F");
    }

    public Vec3 position() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.position:()Lnet/minecraft/world/phys/Vec3;");
    }

    public BlockPos blockPosition() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.blockPosition:()Lnet/minecraft/core/BlockPos;");
    }

    public final double getX() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getX:()D");
    }

    public double getX(double progress) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getX:(D)D");
    }

    public final double getY() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getY:()D");
    }

    public double getY(double progress) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getY:(D)D");
    }

    public final double getZ() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getZ:()D");
    }

    public double getZ(double progress) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getZ:(D)D");
    }

    public ItemStack getPickResult() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getPickResult:()Lnet/minecraft/world/item/ItemStack;");
    }

    public float getYRot() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getYRot:()F");
    }

    public float getVisualRotationYInDegrees() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getVisualRotationYInDegrees:()F");
    }

    public float getXRot() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getXRot:()F");
    }

    public final boolean isRemoved() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isRemoved:()Z");
    }

    public final void setRemoved(Entity.RemovalReason reason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.setRemoved:(Lnet/minecraft/world/entity/Entity$RemovalReason;)V");
    }

    public void setLevelCallback(EntityInLevelCallback levelCallback) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.setLevelCallback:(Lnet/minecraft/world/level/entity/EntityInLevelCallback;)V");
    }

    public boolean shouldBeSaved() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.shouldBeSaved:()Z");
    }

    public boolean isAlwaysTicking() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isAlwaysTicking:()Z");
    }

    public java.util.Collection<ItemEntity> captureDrops() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.captureDrops:()Ljava/util/Collection;");
    }

    public java.util.Collection<ItemEntity> captureDrops(java.util.Collection<ItemEntity> value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.captureDrops:(Ljava/util/Collection;)Ljava/util/Collection;");
    }

    public net.minecraft.nbt.CompoundTag getPersistentData() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getPersistentData:()Lnet/minecraft/nbt/CompoundTag;");
    }

    public boolean canTrample(ServerLevel level, BlockState state, BlockPos pos, double fallDistance) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.canTrample:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;D)Z");
    }

    public final boolean isAddedToLevel() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.isAddedToLevel:()Z");
    }

    public void onAddedToLevel() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.onAddedToLevel:()V");
    }

    public void onRemovedFromLevel() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.onRemovedFromLevel:()V");
    }

    public void revive() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.revive:()V");
    }

    public final <T> T setData(net.neoforged.neoforge.attachment.AttachmentType<T> type, T data) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.setData:(Lnet/neoforged/neoforge/attachment/AttachmentType;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public final void syncData(net.neoforged.neoforge.attachment.AttachmentType<?> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.syncData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)V");
    }

    public final <T, C extends Object> T getCapability(net.neoforged.neoforge.capabilities.EntityCapability<T, C> capability, C context) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getCapability:(Lnet/neoforged/neoforge/capabilities/EntityCapability;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public Level level() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.level:()Lnet/minecraft/world/level/Level;");
    }

    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
    }

    public RandomSource getRandom() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getRandom:()Lnet/minecraft/util/RandomSource;");
    }

    public Optional<ResourceKey<LootTable>> getLootTable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getLootTable:()Ljava/util/Optional;");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    public void registerDebugValues(ServerLevel level, DebugValueSource.Registration registration) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.registerDebugValues:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/debug/DebugValueSource$Registration;)V");
    }

    private record EntityPathElement(Entity entity) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/world/entity/Entity$EntityPathElement.get:()Ljava/lang/String;");
        }
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface Flags {
    }

    public interface MoveFunction {

        void accept(Entity target, double x, double y, double z);
    }

    private record Movement(Vec3 from, Vec3 to, Optional<Vec3> axisDependentOriginalMovement) {

        public Movement(Vec3 from, Vec3 to, Vec3 axisDependentOriginalMovement) {
            this((Vec3) null, (Vec3) null, (Optional<Vec3>) null);
            throw Unimplemented.forMember("net/minecraft/world/entity/Entity$Movement.<init>:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;)V");
        }

        public Movement(Vec3 from, Vec3 to) {
            this((Vec3) null, (Vec3) null, (Optional<Vec3>) null);
            throw Unimplemented.forMember("net/minecraft/world/entity/Entity$Movement.<init>:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;)V");
        }
    }

    public enum MovementEmission {

        NONE, SOUNDS, EVENTS, ALL
    }

    public enum RemovalReason {

        KILLED, DISCARDED, UNLOADED_TO_CHUNK, UNLOADED_WITH_PLAYER, CHANGED_DIMENSION
    }

    public Entity() {
    }
}
