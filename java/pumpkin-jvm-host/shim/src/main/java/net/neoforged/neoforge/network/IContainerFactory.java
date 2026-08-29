package net.neoforged.neoforge.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import dev.pumpkin.shim.Unimplemented;

public interface IContainerFactory<T extends AbstractContainerMenu> extends MenuType.MenuSupplier<T> {

    T create(int windowId, Inventory inv, RegistryFriendlyByteBuf data);

    default T create(int p_create_1_, Inventory p_create_2_) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/IContainerFactory.create:(ILnet/minecraft/world/entity/player/Inventory;)Lnet/minecraft/world/inventory/AbstractContainerMenu;");
    }
}
