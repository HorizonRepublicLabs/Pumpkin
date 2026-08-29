package net.neoforged.neoforge.common.extensions;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.network.IContainerFactory;
import dev.pumpkin.shim.Unimplemented;

public interface IMenuTypeExtension<T> {

    static <T extends AbstractContainerMenu> MenuType<T> create(IContainerFactory<T> factory) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IMenuTypeExtension.create:(Lnet/neoforged/neoforge/network/IContainerFactory;)Lnet/minecraft/world/inventory/MenuType;");
    }

    T create(int windowId, Inventory playerInv, RegistryFriendlyByteBuf extraData);
}
