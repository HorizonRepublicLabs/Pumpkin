package net.minecraft.client.gui.screens;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public class ConfirmScreen extends Screen {

    public ConfirmScreen(BooleanConsumer callback, Component title, Component message) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ConfirmScreen.<init>:(Lit/unimi/dsi/fastutil/booleans/BooleanConsumer;Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;)V");
    }

    public ConfirmScreen(BooleanConsumer callback, Component title, Component message, Component yesButtonComponent, Component noButtonComponent) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ConfirmScreen.<init>:(Lit/unimi/dsi/fastutil/booleans/BooleanConsumer;Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;)V");
    }

    public Component getNarrationMessage() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ConfirmScreen.getNarrationMessage:()Lnet/minecraft/network/chat/Component;");
    }

    protected void init() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ConfirmScreen.init:()V");
    }

    protected void repositionElements() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ConfirmScreen.repositionElements:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ConfirmScreen.tick:()V");
    }

    public boolean shouldCloseOnEsc() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ConfirmScreen.shouldCloseOnEsc:()Z");
    }

    public boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ConfirmScreen.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    protected ConfirmScreen() {
    }
}
