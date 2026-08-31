package net.minecraft.world.item.enchantment.providers;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public interface EnchantmentProvider {

    void enchant(ItemStack item, ItemEnchantments.Mutable itemEnchantments, RandomSource random, final DifficultyInstance difficulty);

    MapCodec<? extends EnchantmentProvider> codec();
}
