package net.neoforged.neoforge.common.extensions;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import dev.pumpkin.shim.Unimplemented;

public interface IItemExtension {

    boolean isCombineRepairable(ItemStack stack);

    default ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.getCraftingRemainder:(Lnet/minecraft/world/item/ItemInstance;)Lnet/minecraft/world/item/ItemStackTemplate;");
    }
}
