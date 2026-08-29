package net.minecraft.core.dispenser;

import net.minecraft.world.item.ItemStack;

public interface DispenseItemBehavior {

    ItemStack dispense(BlockSource source, ItemStack dispensed);
}
