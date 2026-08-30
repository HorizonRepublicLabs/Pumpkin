package net.minecraft.world.entity.item;

import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class ItemEntity extends Entity implements TraceableEntity {

    public ItemEntity(EntityType<? extends ItemEntity> type, Level level) {
    }

    // Pumpkin divergence: the stack is kept so Level.addFreshEntity can hand the drop
    // back to the server instead of losing it.
    private ItemStack pumpkinStack;

    public ItemStack pumpkinStack() {
        return pumpkinStack;
    }

    public ItemEntity(Level level, double x, double y, double z, ItemStack itemStack) {
        this.pumpkinStack = itemStack;
    }

    public ItemEntity(Level level, double x, double y, double z, ItemStack itemStack, double deltaX, double deltaY, double deltaZ) {
    }

    public boolean dampensVibrations() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.dampensVibrations:()Z");
    }

    public Entity getOwner() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getOwner:()Lnet/minecraft/world/entity/Entity;");
    }

    public void restoreFrom(Entity oldEntity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.restoreFrom:(Lnet/minecraft/world/entity/Entity;)V");
    }

    protected Entity.MovementEmission getMovementEmission() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getMovementEmission:()Lnet/minecraft/world/entity/Entity$MovementEmission;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected double getDefaultGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getDefaultGravity:()D");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.tick:()V");
    }

    public BlockPos getBlockPosBelowThatAffectsMyMovement() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getBlockPosBelowThatAffectsMyMovement:()Lnet/minecraft/core/BlockPos;");
    }

    public boolean fireImmune() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.fireImmune:()Z");
    }

    protected boolean shouldPlayLavaHurtSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.shouldPlayLavaHurtSound:()Z");
    }

    public final boolean hurtClient(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.hurtClient:(Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public final boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public boolean ignoreExplosion(Explosion explosion) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.ignoreExplosion:(Lnet/minecraft/world/level/Explosion;)Z");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void playerTouch(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.playerTouch:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public Component getName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getName:()Lnet/minecraft/network/chat/Component;");
    }

    public boolean isAttackable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.isAttackable:()Z");
    }

    public Entity teleport(TeleportTransition transition) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.teleport:(Lnet/minecraft/world/level/portal/TeleportTransition;)Lnet/minecraft/world/entity/Entity;");
    }

    public UUID getTarget() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getTarget:()Ljava/util/UUID;");
    }

    // Pumpkin divergence: accepted and dropped. The bridge captures the entity's stack
    // and hands it to the real world, which applies its own pickup rules; this entity is
    // never spawned, so it has no delay to clear.
    public void setNoPickUpDelay() {
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public float getVisualRotationYInDegrees() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getVisualRotationYInDegrees:()F");
    }

    public SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    public ItemEntity() {
    }
}
