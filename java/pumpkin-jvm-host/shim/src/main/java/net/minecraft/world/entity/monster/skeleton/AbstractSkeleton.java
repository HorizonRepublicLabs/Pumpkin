package net.minecraft.world.entity.monster.skeleton;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractSkeleton extends Monster implements RangedAttackMob {

    protected AbstractSkeleton(EntityType<? extends AbstractSkeleton> type, Level level) {
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.registerGoals:()V");
    }

    public static AttributeSupplier.Builder createAttributes() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.createAttributes:()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;");
    }

    protected void playStepSound(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.playStepSound:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    protected abstract SoundEvent getStepSound();

    public void rideTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.rideTick:()V");
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.populateDefaultEquipmentSlots:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    public void performRangedAttack(LivingEntity target, float power) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.performRangedAttack:(Lnet/minecraft/world/entity/LivingEntity;F)V");
    }

    protected AbstractArrow getArrow(ItemStack projectile, float power, ItemStack firingWeapon) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.getArrow:(Lnet/minecraft/world/item/ItemStack;FLnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;");
    }

    public boolean canUseNonMeleeWeapon(ItemStack item) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.canUseNonMeleeWeapon:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public TagKey<Item> getPreferredWeaponType() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.getPreferredWeaponType:()Lnet/minecraft/tags/TagKey;");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void onEquipItem(EquipmentSlot slot, ItemStack oldStack, ItemStack stack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.onEquipItem:(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public boolean wantsToPickUp(ServerLevel level, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/AbstractSkeleton.wantsToPickUp:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public AbstractSkeleton() {
    }
}
