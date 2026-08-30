package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.enchantment.Enchantment;
import dev.pumpkin.shim.Unimplemented;

public interface ItemInstanceExtension {

    default int getEnchantmentLevel(Holder<Enchantment> enchantment) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ItemInstanceExtension.getEnchantmentLevel:(Lnet/minecraft/core/Holder;)I");
    }

    // Pumpkin divergence: no item this host builds declares a crafting remainder (the
    // recorder for Properties.craftRemainder does not exist yet), so the truthful answer
    // is always "nothing stays behind" -- an empty template, which is what vanilla
    // returns for a remainder-less item.
    default ItemStackTemplate getCraftingRemainder() {
        return new ItemStackTemplate((net.minecraft.world.item.Item) null, 0,
                (net.minecraft.core.component.DataComponentPatch) null);
    }
}
