package net.minecraft.world.entity.projectile.arrow;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractArrow extends Projectile {

    private double baseDamage;

    protected AbstractArrow(EntityType<? extends AbstractArrow> type, Level level) {
    }

    protected AbstractArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level level, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
    }

    protected AbstractArrow(EntityType<? extends AbstractArrow> type, LivingEntity mob, Level level, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
    }

    public boolean shouldRenderAtSqrDistance(double distance) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.shouldRenderAtSqrDistance:(D)Z");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public void shoot(double xd, double yd, double zd, float pow, float uncertainty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.shoot:(DDDFF)V");
    }

    public void lerpMotion(Vec3 movement) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.lerpMotion:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.tick:()V");
    }

    protected float getAirDrag() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.getAirDrag:()F");
    }

    protected double getDefaultGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.getDefaultGravity:()D");
    }

    public boolean isPushedByFluid() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.isPushedByFluid:()Z");
    }

    public void move(MoverType moverType, Vec3 delta) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.move:(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public void onItemBreak(Item item) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.onItemBreak:(Lnet/minecraft/world/item/Item;)V");
    }

    public void onAboveBubbleColumn(boolean dragDown, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.onAboveBubbleColumn:(ZLnet/minecraft/core/BlockPos;)V");
    }

    public void onInsideBubbleColumn(boolean dragDown) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.onInsideBubbleColumn:(Z)V");
    }

    public void push(double xa, double ya, double za) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.push:(DDD)V");
    }

    protected void onHitEntity(EntityHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.onHitEntity:(Lnet/minecraft/world/phys/EntityHitResult;)V");
    }

    protected void onHitBlock(BlockHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.onHitBlock:(Lnet/minecraft/world/phys/BlockHitResult;)V");
    }

    public ItemStack getWeaponItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.getWeaponItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    protected boolean canHitEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.canHitEntity:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void setOwner(Entity owner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.setOwner:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void playerTouch(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.playerTouch:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    protected abstract ItemStack getDefaultPickupItem();

    protected Entity.MovementEmission getMovementEmission() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.getMovementEmission:()Lnet/minecraft/world/entity/Entity$MovementEmission;");
    }

    public void setBaseDamage(double baseDamage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.setBaseDamage:(D)V");
    }

    public boolean isAttackable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.isAttackable:()Z");
    }

    public boolean isPickable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.isPickable:()Z");
    }

    public SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    protected boolean shouldBounceOnWorldBorder() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/arrow/AbstractArrow.shouldBounceOnWorldBorder:()Z");
    }

    public enum Pickup {

        DISALLOWED, ALLOWED, CREATIVE_ONLY
    }

    public AbstractArrow() {
    }
}
