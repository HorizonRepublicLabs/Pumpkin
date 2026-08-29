package net.minecraft.client.gui.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import dev.pumpkin.shim.Unimplemented;

public class MenuScreens {

    public static <T extends AbstractContainerMenu> void create(MenuType<T> type, Minecraft minecraft, int containerId, Component title) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/MenuScreens.create:(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/client/Minecraft;ILnet/minecraft/network/chat/Component;)V");
    }

    private static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void register(MenuType<? extends M> type, MenuScreens.ScreenConstructor<M, U> factory) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/MenuScreens.register:(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/client/gui/screens/MenuScreens$ScreenConstructor;)V");
    }

    public interface ScreenConstructor<T extends AbstractContainerMenu, U extends Screen & MenuAccess<T>> {

        U create(T menu, Inventory inventory, final Component title);
    }

    public MenuScreens() {
    }
}
