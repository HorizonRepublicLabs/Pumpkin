package net.minecraft.client.gui.screens.options;

import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public abstract class OptionsSubScreen extends Screen {

    public OptionsSubScreen(Screen lastScreen, Options options, Component title) {
    }

    protected void init() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/options/OptionsSubScreen.init:()V");
    }

    protected abstract void addOptions();

    protected void repositionElements() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/options/OptionsSubScreen.repositionElements:()V");
    }

    public void removed() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/options/OptionsSubScreen.removed:()V");
    }

    public void onClose() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/options/OptionsSubScreen.onClose:()V");
    }

    public OptionsSubScreen() {
    }
}
