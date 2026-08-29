package net.neoforged.neoforge.common.extensions;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.neoforged.neoforge.capabilities.ItemCapability;
import dev.pumpkin.shim.Unimplemented;

public interface IItemStackExtension extends ItemInstanceExtension {

    default int getBurnTime(RecipeType<?> recipeType, FuelValues fuelValues) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getBurnTime:(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/level/block/entity/FuelValues;)I");
    }

    default <T, C extends Object> T getCapability(ItemCapability<T, C> capability, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;Ljava/lang/Object;)Ljava/lang/Object;");
    }
}
