package net.minecraft.world.item;

import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import dev.pumpkin.shim.Unimplemented;

public abstract class ProjectileWeaponItem extends Item {

    public ProjectileWeaponItem(Item.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/item/ProjectileWeaponItem.<init>:(Lnet/minecraft/world/item/Item$Properties;)V");
    }

    public abstract Predicate<ItemStack> getAllSupportedProjectiles();

    public abstract int getDefaultProjectileRange();

    protected abstract void shootProjectile(final LivingEntity shooter, final Projectile projectileEntity, final int index, final float power, final float uncertainty, final float angle, final LivingEntity targetOverrride);

    protected ProjectileWeaponItem() {
    }
}
