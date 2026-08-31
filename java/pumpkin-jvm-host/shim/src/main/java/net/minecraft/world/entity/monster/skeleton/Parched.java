package net.minecraft.world.entity.monster.skeleton;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class Parched extends AbstractSkeleton {

    public Parched(EntityType<? extends AbstractSkeleton> type, Level level) {
    }

    protected AbstractArrow getArrow(ItemStack projectile, float power, ItemStack firingWeapon) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.getArrow:(Lnet/minecraft/world/item/ItemStack;FLnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;");
    }

    public static AttributeSupplier.Builder createAttributes() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.createAttributes:()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getStepSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.getStepSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected int getHardAttackInterval() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.getHardAttackInterval:()I");
    }

    protected int getAttackInterval() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.getAttackInterval:()I");
    }

    public boolean canBeAffected(MobEffectInstance newEffect) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Parched.canBeAffected:(Lnet/minecraft/world/effect/MobEffectInstance;)Z");
    }

    public Parched() {
    }
}
