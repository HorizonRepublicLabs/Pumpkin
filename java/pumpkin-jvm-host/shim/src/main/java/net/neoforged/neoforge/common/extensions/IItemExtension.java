package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.common.ItemAbility;
import dev.pumpkin.shim.Unimplemented;

public interface IItemExtension {

    boolean isCombineRepairable(ItemStack stack);

    default boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.onLeftClickEntity:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)Z");
    }

    default ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.getCraftingRemainder:(Lnet/minecraft/world/item/ItemInstance;)Lnet/minecraft/world/item/ItemStackTemplate;");
    }

    // Pumpkin divergence: NeoForge's own default -- a plain item performs no action.
    default boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {
        return false;
    }

    default boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.isPrimaryItemFor:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/Holder;)Z");
    }

    default boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.supportsEnchantment:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/Holder;)Z");
    }

    default int getEnchantmentLevel(ItemInstance stack, Holder<Enchantment> enchantment) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.getEnchantmentLevel:(Lnet/minecraft/world/item/ItemInstance;Lnet/minecraft/core/Holder;)I");
    }

    default ItemEnchantments getAllEnchantments(ItemStack stack, RegistryLookup<Enchantment> lookup) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.getAllEnchantments:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/HolderLookup$RegistryLookup;)Lnet/minecraft/world/item/enchantment/ItemEnchantments;");
    }

    default boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.shouldCauseReequipAnimation:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Z)Z");
    }

    default boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.shouldCauseBlockBreakReset:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    default String getCreatorModId(HolderLookup.Provider registries, ItemStack itemStack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.getCreatorModId:(Lnet/minecraft/core/HolderLookup$Provider;Lnet/minecraft/world/item/ItemStack;)Ljava/lang/String;");
    }

    default void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.onDestroyed:(Lnet/minecraft/world/entity/item/ItemEntity;Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    default boolean isDamageable(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.isDamageable:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    default boolean isNotReplaceableByPickAction(ItemStack stack, Player player, int inventorySlot) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.isNotReplaceableByPickAction:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;I)Z");
    }
}
