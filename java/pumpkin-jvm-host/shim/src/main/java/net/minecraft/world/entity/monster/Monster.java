package net.minecraft.world.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import dev.pumpkin.shim.Unimplemented;

public abstract class Monster extends PathfinderMob implements Enemy {

    protected Monster(EntityType<? extends Monster> type, Level level) {
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.aiStep:()V");
    }

    protected SoundEvent getSwimSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.getSwimSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getSwimSplashSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.getSwimSplashSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public LivingEntity.Fallsounds getFallSounds() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.getFallSounds:()Lnet/minecraft/world/entity/LivingEntity$Fallsounds;");
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.getWalkTargetValue:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/LevelReader;)F");
    }

    public static boolean checkMonsterSpawnRules(EntityType<? extends Mob> type, ServerLevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.checkMonsterSpawnRules:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z");
    }

    public boolean shouldDropExperience() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.shouldDropExperience:()Z");
    }

    protected boolean shouldDropLoot(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.shouldDropLoot:(Lnet/minecraft/server/level/ServerLevel;)Z");
    }

    public ItemStack getProjectile(ItemStack heldWeapon) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Monster.getProjectile:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public Monster() {
    }
}
