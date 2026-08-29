package net.neoforged.neoforge.common.extensions;

import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface ContainerExtension {

    default void setItem(int slot, ItemStack stack, boolean insideTransaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ContainerExtension.setItem:(ILnet/minecraft/world/item/ItemStack;Z)V");
    }
}
