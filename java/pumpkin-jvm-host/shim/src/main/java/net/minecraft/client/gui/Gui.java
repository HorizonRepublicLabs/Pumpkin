package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import dev.pumpkin.shim.Unimplemented;

public class Gui {

    public final Hud hud = null;

    private Screen screen;

    public Gui(Minecraft minecraft, Hud hud, GuiRenderState guiRenderState) {
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/gui/Gui.tick:()V");
    }

    public void update() {
        throw Unimplemented.forMember("net/minecraft/client/gui/Gui.update:()V");
    }

    public Screen screen() {
        throw Unimplemented.forMember("net/minecraft/client/gui/Gui.screen:()Lnet/minecraft/client/gui/screens/Screen;");
    }

    public void setScreen(Screen screen) {
        throw Unimplemented.forMember("net/minecraft/client/gui/Gui.setScreen:(Lnet/minecraft/client/gui/screens/Screen;)V");
    }

    public Gui() {
    }
}
