package net.minecraft.world.item;

import java.util.function.Predicate;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class BowItem extends ProjectileWeaponItem {

    public BowItem(Item.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.<init>:(Lnet/minecraft/world/item/Item$Properties;)V");
    }

    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int remainingTime) {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.releaseUsing:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)Z");
    }

    protected void shootProjectile(LivingEntity shooter, Projectile projectileEntity, int index, float power, float uncertainty, float angle, LivingEntity targetOverrride) {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.shootProjectile:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/projectile/Projectile;IFFFLnet/minecraft/world/entity/LivingEntity;)V");
    }

    public static float getPowerForTime(int timeHeld) {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.getPowerForTime:(I)F");
    }

    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.getUseDuration:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)I");
    }

    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.getUseAnimation:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemUseAnimation;");
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.use:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.getAllSupportedProjectiles:()Ljava/util/function/Predicate;");
    }

    public int getDefaultProjectileRange() {
        throw Unimplemented.forMember("net/minecraft/world/item/BowItem.getDefaultProjectileRange:()I");
    }

    protected BowItem() {
    }
}
