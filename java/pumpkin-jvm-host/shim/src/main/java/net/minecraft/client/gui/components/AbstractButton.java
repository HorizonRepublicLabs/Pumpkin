package net.minecraft.client.gui.components;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractButton extends AbstractWidget.WithInactiveMessage {

    public AbstractButton(int x, int y, int width, int height, Component message) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractButton.<init>:(IIIILnet/minecraft/network/chat/Component;)V");
    }

    public abstract void onPress(InputWithModifiers input);

    protected final void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractButton.extractWidgetRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    protected abstract void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a);

    public void onClick(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractButton.onClick:(Lnet/minecraft/client/input/MouseButtonEvent;Z)V");
    }

    public boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractButton.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    protected AbstractButton() {
    }
}
