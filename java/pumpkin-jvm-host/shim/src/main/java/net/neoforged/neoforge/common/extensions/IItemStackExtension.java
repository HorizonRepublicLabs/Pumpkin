package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.entity.FuelValues;
import net.neoforged.neoforge.capabilities.ItemCapability;
import dev.pumpkin.shim.Unimplemented;

public interface IItemStackExtension extends ItemInstanceExtension {

    // Pumpkin divergence: NeoForge's own default -- the fuel table answers; a mod
    // item overriding Item burn behaviour is a wider surface, surfaced when a mod
    // actually does it.
    default int getBurnTime(RecipeType<?> recipeType, FuelValues fuelValues) {
        return fuelValues.burnDuration((net.minecraft.world.item.ItemStack) this);
    }

    default ItemEnchantments getAllEnchantments(RegistryLookup<Enchantment> lookup) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getAllEnchantments:(Lnet/minecraft/core/HolderLookup$RegistryLookup;)Lnet/minecraft/world/item/enchantment/ItemEnchantments;");
    }

    // Pumpkin divergence: vanilla answers this from the stack's equippable component,
    // which the bridge's stand-in stacks never carry -- so no stack this host builds
    // is armor, and the armor-slot check in a menu's shift-click routing says no.
    default boolean canEquip(EquipmentSlot armorType, LivingEntity entity) {
        return false;
    }

    default <T, C extends Object> T getCapability(ItemCapability<T, C> capability, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    default <T> T getCapability(ItemCapability<T, Void> capability) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;)Ljava/lang/Object;");
    }
}
