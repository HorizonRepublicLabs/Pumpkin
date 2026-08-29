package net.minecraft.client.gui.screens;

import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import dev.pumpkin.shim.Unimplemented;

public class MenuScreens {

    public interface ScreenConstructor<T extends AbstractContainerMenu, U extends Screen & MenuAccess<T>> {

        U create(T menu, Inventory inventory, final Component title);
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/client/gui/screens/MenuScreens");
        }
    }
}
