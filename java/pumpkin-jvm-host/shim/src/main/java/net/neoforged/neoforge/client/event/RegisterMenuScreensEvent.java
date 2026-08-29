package net.neoforged.neoforge.client.event;

import java.util.Map;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterMenuScreensEvent extends Event implements IModBusEvent {

    public RegisterMenuScreensEvent(Map<MenuType<?>, MenuScreens.ScreenConstructor<?, ?>> registeredScreens) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterMenuScreensEvent.<init>:(Ljava/util/Map;)V");
    }

    public <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void register(MenuType<? extends M> menuType, MenuScreens.ScreenConstructor<M, U> screenConstructor) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterMenuScreensEvent.register:(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/client/gui/screens/MenuScreens$ScreenConstructor;)V");
    }

    protected RegisterMenuScreensEvent() {
    }
}
