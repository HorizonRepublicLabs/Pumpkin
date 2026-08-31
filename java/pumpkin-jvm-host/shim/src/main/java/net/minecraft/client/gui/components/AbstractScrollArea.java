package net.minecraft.client.gui.components;

import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractScrollArea extends AbstractWidget {

    public AbstractScrollArea(int x, int y, int width, int height, Component message, AbstractScrollArea.ScrollbarSettings scrollbarSettings) {
    }

    public boolean mouseScrolled(double mx, double my, double scrollX, double scrollY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractScrollArea.mouseScrolled:(DDDD)Z");
    }

    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractScrollArea.mouseDragged:(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z");
    }

    public void onRelease(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractScrollArea.onRelease:(Lnet/minecraft/client/input/MouseButtonEvent;)V");
    }

    protected abstract int contentHeight();

    public record ScrollbarSettings(Identifier scrollerSprite, Identifier disabledScrollerSprite, Identifier backgroundSprite, int scrollbarWidth, int scrollbarMinHeight, int scrollRate, boolean resizingScrollbar) {
    }

    public AbstractScrollArea() {
    }
}
