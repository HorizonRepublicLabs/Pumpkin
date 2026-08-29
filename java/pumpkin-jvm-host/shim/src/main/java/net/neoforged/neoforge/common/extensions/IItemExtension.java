package net.neoforged.neoforge.common.extensions;

import net.minecraft.world.item.ItemStack;

public interface IItemExtension {

    boolean isCombineRepairable(ItemStack stack);
}
