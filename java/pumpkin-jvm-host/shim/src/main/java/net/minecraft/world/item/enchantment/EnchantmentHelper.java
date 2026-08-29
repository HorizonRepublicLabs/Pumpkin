package net.minecraft.world.item.enchantment;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
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

    public interface EnchantmentInSlotVisitor {

        void accept(Holder<Enchantment> enchantment, int level, EnchantedItemInUse item);
    }

    public interface EnchantmentVisitor {

        void accept(Holder<Enchantment> enchantment, int level);
    }

    public EnchantmentHelper() {
    }
}
