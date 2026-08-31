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

    default int getBurnTime(RecipeType<?> recipeType, FuelValues fuelValues) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getBurnTime:(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/level/block/entity/FuelValues;)I");
    }

    default ItemEnchantments getAllEnchantments(RegistryLookup<Enchantment> lookup) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getAllEnchantments:(Lnet/minecraft/core/HolderLookup$RegistryLookup;)Lnet/minecraft/world/item/enchantment/ItemEnchantments;");
    }

    default boolean canEquip(EquipmentSlot armorType, LivingEntity entity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.canEquip:(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    default <T, C extends Object> T getCapability(ItemCapability<T, C> capability, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    default <T> T getCapability(ItemCapability<T, Void> capability) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;)Ljava/lang/Object;");
    }
}
