package net.minecraft.world.item.component;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record BlocksAttacks(float blockDelaySeconds, float disableCooldownScale, List<BlocksAttacks.DamageReduction> damageReductions, BlocksAttacks.ItemDamageFunction itemDamage, Optional<HolderSet<DamageType>> bypassedBy, Optional<Holder<SoundEvent>> blockSound, Optional<Holder<SoundEvent>> disableSound) {

    public void hurtBlockingItem(Level level, ItemStack item, LivingEntity user, InteractionHand hand, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlocksAttacks.hurtBlockingItem:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;F)V");
    }

    public void hurtBlockingItem(Level level, ItemStack item, LivingEntity user, InteractionHand hand, float damage, int fixedDamage) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlocksAttacks.hurtBlockingItem:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;FI)V");
    }

    public float resolveBlockedDamage(DamageSource source, float dealtDamage, double angle) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlocksAttacks.resolveBlockedDamage:(Lnet/minecraft/world/damagesource/DamageSource;FD)F");
    }

    public record DamageReduction(float horizontalBlockingAngle, Optional<HolderSet<DamageType>> type, float base, float factor) {

        public float resolve(DamageSource source, float dealtDamage, double angle) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/BlocksAttacks$DamageReduction.resolve:(Lnet/minecraft/world/damagesource/DamageSource;FD)F");
        }
    }

    public record ItemDamageFunction(float threshold, float base, float factor) {

        public int apply(float dealtDamage) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/BlocksAttacks$ItemDamageFunction.apply:(F)I");
        }
    }
}
