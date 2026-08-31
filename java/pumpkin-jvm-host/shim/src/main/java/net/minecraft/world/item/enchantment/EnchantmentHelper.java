package net.minecraft.world.item.enchantment;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import dev.pumpkin.shim.Unimplemented;

public class EnchantmentHelper {

    public static int getTagEnchantmentLevel(Holder<Enchantment> enchantment, ItemInstance piece) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/EnchantmentHelper.getTagEnchantmentLevel:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/item/ItemInstance;)I");
    }

    public static void setEnchantments(ItemStack itemStack, ItemEnchantments enchantments) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/EnchantmentHelper.setEnchantments:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/enchantment/ItemEnchantments;)V");
    }

    public static ItemEnchantments getEnchantmentsForCrafting(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/EnchantmentHelper.getEnchantmentsForCrafting:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/enchantment/ItemEnchantments;");
    }

    public static ItemStack createBook(EnchantmentInstance enchant) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/EnchantmentHelper.createBook:(Lnet/minecraft/world/item/enchantment/EnchantmentInstance;)Lnet/minecraft/world/item/ItemStack;");
    }

    public static boolean has(ItemStack item, DataComponentType<?> effectType) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/EnchantmentHelper.has:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/component/DataComponentType;)Z");
    }

    public static void enchantItemFromProvider(ItemStack itemStack, RegistryAccess registryAccess, ResourceKey<EnchantmentProvider> providerKey, DifficultyInstance difficulty, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/EnchantmentHelper.enchantItemFromProvider:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/RegistryAccess;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/util/RandomSource;)V");
    }

    public interface EnchantmentInSlotVisitor {

        void accept(Holder<Enchantment> enchantment, int level, EnchantedItemInUse item);
    }

    public interface EnchantmentVisitor {

        void accept(Holder<Enchantment> enchantment, int level);
    }

    public EnchantmentHelper() {
    }
}
