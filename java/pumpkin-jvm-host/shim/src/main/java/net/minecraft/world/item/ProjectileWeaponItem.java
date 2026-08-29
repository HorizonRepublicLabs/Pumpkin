package net.minecraft.world.item;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import dev.pumpkin.shim.Unimplemented;

public abstract class ProjectileWeaponItem extends Item {

    public ProjectileWeaponItem(Item.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/item/ProjectileWeaponItem.<init>:(Lnet/minecraft/world/item/Item$Properties;)V");
    }

    public abstract Predicate<ItemStack> getAllSupportedProjectiles();

    public abstract int getDefaultProjectileRange();

    protected void shoot(ServerLevel level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, List<ItemStack> projectiles, float power, float uncertainty, boolean isCrit, LivingEntity targetOverride) {
        throw Unimplemented.forMember("net/minecraft/world/item/ProjectileWeaponItem.shoot:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Ljava/util/List;FFZLnet/minecraft/world/entity/LivingEntity;)V");
    }

    protected abstract void shootProjectile(final LivingEntity shooter, final Projectile projectileEntity, final int index, final float power, final float uncertainty, final float angle, final LivingEntity targetOverrride);

    protected static List<ItemStack> draw(ItemStack weapon, ItemStack projectile, LivingEntity shooter) {
        throw Unimplemented.forMember("net/minecraft/world/item/ProjectileWeaponItem.draw:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)Ljava/util/List;");
    }

    protected ProjectileWeaponItem() {
    }
}
