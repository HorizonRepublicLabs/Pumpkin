package net.neoforged.neoforge.client.extensions.common;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface IClientItemExtensions {

    static IClientItemExtensions of(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/IClientItemExtensions.of:(Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/client/extensions/common/IClientItemExtensions;");
    }

    static IClientItemExtensions of(Item item) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/IClientItemExtensions.of:(Lnet/minecraft/world/item/Item;)Lnet/neoforged/neoforge/client/extensions/common/IClientItemExtensions;");
    }

    enum FontContext {

        ITEM_COUNT, TOOLTIP, SELECTED_ITEM_NAME
    }
}
