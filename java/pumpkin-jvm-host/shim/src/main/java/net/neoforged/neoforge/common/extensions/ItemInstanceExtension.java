package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.enchantment.Enchantment;
import dev.pumpkin.shim.Unimplemented;

public interface ItemInstanceExtension {

    default int getEnchantmentLevel(Holder<Enchantment> enchantment) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ItemInstanceExtension.getEnchantmentLevel:(Lnet/minecraft/core/Holder;)I");
    }

    default ItemStackTemplate getCraftingRemainder() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ItemInstanceExtension.getCraftingRemainder:()Lnet/minecraft/world/item/ItemStackTemplate;");
    }
}
