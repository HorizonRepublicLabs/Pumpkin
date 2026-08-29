package net.minecraft.world.entity;

import net.minecraft.world.item.ItemStack;

public interface EquipmentUser {

    void setItemSlot(final EquipmentSlot slot, final ItemStack stack);

    ItemStack getItemBySlot(final EquipmentSlot slot);

    void setDropChance(final EquipmentSlot slot, final float dropChance);
}
