package net.minecraft.world.entity;

import net.minecraft.world.item.ItemStack;

public interface SlotAccess {

    ItemStack get();

    boolean set(ItemStack itemStack);
}
