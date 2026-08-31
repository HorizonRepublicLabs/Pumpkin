package net.minecraft.world.entity.projectile.throwableitemprojectile;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public abstract class ThrowableItemProjectile extends ThrowableProjectile implements ItemSupplier {

    public ThrowableItemProjectile(EntityType<? extends ThrowableItemProjectile> type, Level level) {
    }

    public ThrowableItemProjectile(EntityType<? extends ThrowableItemProjectile> type, double x, double y, double z, Level level, ItemStack itemStack) {
    }

    public ThrowableItemProjectile(EntityType<? extends ThrowableItemProjectile> type, LivingEntity owner, Level level, ItemStack itemStack) {
    }

    protected abstract Item getDefaultItem();

    public ItemStack getItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrowableItemProjectile.getItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrowableItemProjectile.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrowableItemProjectile.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrowableItemProjectile.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public ThrowableItemProjectile() {
    }
}
