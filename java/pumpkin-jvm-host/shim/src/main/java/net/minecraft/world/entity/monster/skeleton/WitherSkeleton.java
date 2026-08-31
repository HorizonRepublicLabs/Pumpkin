package net.minecraft.world.entity.monster.skeleton;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import dev.pumpkin.shim.Unimplemented;

public class WitherSkeleton extends AbstractSkeleton {

    public WitherSkeleton(EntityType<? extends WitherSkeleton> type, Level level) {
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.registerGoals:()V");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getStepSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.getStepSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public TagKey<Item> getPreferredWeaponType() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.getPreferredWeaponType:()Lnet/minecraft/tags/TagKey;");
    }

    public boolean canHoldItem(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.canHoldItem:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.populateDefaultEquipmentSlots:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    protected void populateDefaultEquipmentEnchantments(ServerLevelAccessor level, RandomSource random, DifficultyInstance localDifficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.populateDefaultEquipmentEnchantments:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    public boolean doHurtTarget(ServerLevel level, Entity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.doHurtTarget:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;)Z");
    }

    protected AbstractArrow getArrow(ItemStack projectile, float power, ItemStack firingWeapon) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.getArrow:(Lnet/minecraft/world/item/ItemStack;FLnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;");
    }

    public boolean canBeAffected(MobEffectInstance newEffect) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/WitherSkeleton.canBeAffected:(Lnet/minecraft/world/effect/MobEffectInstance;)Z");
    }

    public WitherSkeleton() {
    }
}
