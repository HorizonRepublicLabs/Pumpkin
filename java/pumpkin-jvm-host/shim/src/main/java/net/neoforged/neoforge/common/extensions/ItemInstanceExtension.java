package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.ItemAbility;
import dev.pumpkin.shim.Unimplemented;

public interface ItemInstanceExtension {

    // Pumpkin divergence: NeoForge's own default -- the stack asks its item, so a
    // mod tool that overrides Item.canPerformAction still answers for itself. Only
    // ItemStack carries an item here; any other carrier fails loudly.
    default boolean canPerformAction(ItemAbility itemAbility) {
        if (this instanceof net.minecraft.world.item.ItemStack self) {
            return self.getItem().canPerformAction(self, itemAbility);
        }
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ItemInstanceExtension.canPerformAction:(Lnet/neoforged/neoforge/common/ItemAbility;)Z");
    }

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
