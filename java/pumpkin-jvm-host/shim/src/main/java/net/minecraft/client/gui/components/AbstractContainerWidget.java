package net.minecraft.client.gui.components;

import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractContainerWidget extends AbstractScrollArea implements ContainerEventHandler {

    public AbstractContainerWidget(int x, int y, int width, int height, Component message) {
    }

    public AbstractContainerWidget(int x, int y, int width, int height, Component message, AbstractScrollArea.ScrollbarSettings scrollbarSettings) {
    }

    public final boolean isDragging() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.isDragging:()Z");
    }

    public final void setDragging(boolean dragging) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.setDragging:(Z)V");
    }

    public GuiEventListener getFocused() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.getFocused:()Lnet/minecraft/client/gui/components/events/GuiEventListener;");
    }

    public void setFocused(GuiEventListener focused) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.setFocused:(Lnet/minecraft/client/gui/components/events/GuiEventListener;)V");
    }

    public ComponentPath nextFocusPath(FocusNavigationEvent navigationEvent) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.nextFocusPath:(Lnet/minecraft/client/gui/navigation/FocusNavigationEvent;)Lnet/minecraft/client/gui/ComponentPath;");
    }

    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.mouseClicked:(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z");
    }

    public boolean mouseReleased(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.mouseReleased:(Lnet/minecraft/client/input/MouseButtonEvent;)Z");
    }

    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.mouseDragged:(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z");
    }

    public boolean isFocused() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.isFocused:()Z");
    }

    public void setFocused(boolean focused) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractContainerWidget.setFocused:(Z)V");
    }

    public AbstractContainerWidget() {
    }
}
