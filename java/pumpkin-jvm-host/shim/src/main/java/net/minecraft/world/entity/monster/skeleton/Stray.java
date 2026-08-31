package net.minecraft.world.entity.monster.skeleton;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import dev.pumpkin.shim.Unimplemented;

public class Stray extends AbstractSkeleton {

    public Stray(EntityType<? extends Stray> type, Level level) {
    }

    public static boolean checkStraySpawnRules(EntityType<? extends Stray> type, ServerLevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Stray.checkStraySpawnRules:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Stray.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Stray.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Stray.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getStepSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Stray.getStepSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected AbstractArrow getArrow(ItemStack projectile, float power, ItemStack firingWeapon) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Stray.getArrow:(Lnet/minecraft/world/item/ItemStack;FLnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;");
    }

    public Stray() {
    }
}
