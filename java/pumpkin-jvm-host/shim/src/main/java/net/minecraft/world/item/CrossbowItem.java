package net.minecraft.world.item;

import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class CrossbowItem extends ProjectileWeaponItem {

    public CrossbowItem(Item.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.<init>:(Lnet/minecraft/world/item/Item$Properties;)V");
    }

    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.getSupportedHeldProjectiles:()Ljava/util/function/Predicate;");
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.getAllSupportedProjectiles:()Ljava/util/function/Predicate;");
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.use:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int remainingTime) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.releaseUsing:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)Z");
    }

    protected void shootProjectile(LivingEntity livingEntity, Projectile projectileEntity, int index, float power, float uncertainty, float angle, LivingEntity targetOverride) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.shootProjectile:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/projectile/Projectile;IFFFLnet/minecraft/world/entity/LivingEntity;)V");
    }

    protected Projectile createProjectile(Level level, LivingEntity shooter, ItemStack heldItem, ItemStack projectile, boolean isCrit) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.createProjectile:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/projectile/Projectile;");
    }

    protected int getDurabilityUse(ItemStack projectile) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.getDurabilityUse:(Lnet/minecraft/world/item/ItemStack;)I");
    }

    public void onUseTick(Level level, LivingEntity entity, ItemStack itemStack, int ticksRemaining) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.onUseTick:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;I)V");
    }

    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.getUseDuration:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)I");
    }

    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.getUseAnimation:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemUseAnimation;");
    }

    public boolean useOnRelease(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.useOnRelease:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public int getDefaultProjectileRange() {
        throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem.getDefaultProjectileRange:()I");
    }

    public enum ChargeType implements StringRepresentable {

        NONE, ARROW, ROCKET;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/item/CrossbowItem$ChargeType.getSerializedName:()Ljava/lang/String;");
        }
    }

    public record ChargingSounds(Optional<Holder<SoundEvent>> start, Optional<Holder<SoundEvent>> mid, Optional<Holder<SoundEvent>> end) {
    }

    protected CrossbowItem() {
    }
}
