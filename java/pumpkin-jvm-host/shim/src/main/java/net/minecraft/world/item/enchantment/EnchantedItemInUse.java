package net.minecraft.world.item.enchantment;

import java.util.function.Consumer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record EnchantedItemInUse(ItemStack itemStack, EquipmentSlot inSlot, LivingEntity owner, Consumer<Item> onBreak) {

    public EnchantedItemInUse(ItemStack itemStack, EquipmentSlot inSlot, LivingEntity owner) {
        this((ItemStack) null, (EquipmentSlot) null, (LivingEntity) null, (Consumer<Item>) null);
    }
}
