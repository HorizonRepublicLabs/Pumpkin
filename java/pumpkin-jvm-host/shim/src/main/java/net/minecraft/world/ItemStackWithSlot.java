package net.minecraft.world;

import net.minecraft.world.item.ItemStack;

public record ItemStackWithSlot(int slot, ItemStack stack) {
}
