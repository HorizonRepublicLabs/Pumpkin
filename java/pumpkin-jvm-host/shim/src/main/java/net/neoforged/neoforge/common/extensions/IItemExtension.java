package net.neoforged.neoforge.common.extensions;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import dev.pumpkin.shim.Unimplemented;

public interface IItemExtension {

    boolean isCombineRepairable(ItemStack stack);

    default boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.onLeftClickEntity:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)Z");
    }

    default ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.getCraftingRemainder:(Lnet/minecraft/world/item/ItemInstance;)Lnet/minecraft/world/item/ItemStackTemplate;");
    }
}
