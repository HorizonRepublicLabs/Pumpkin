package net.minecraft.client.gui.components.events;

import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.TabOrderedElement;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import dev.pumpkin.shim.Unimplemented;

public interface GuiEventListener extends TabOrderedElement {

    default void mouseMoved(double x, double y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.mouseMoved:(DD)V");
    }

    default boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.mouseClicked:(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z");
    }

    default boolean mouseReleased(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.mouseReleased:(Lnet/minecraft/client/input/MouseButtonEvent;)Z");
    }

    default boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.mouseDragged:(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z");
    }

    default boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.mouseScrolled:(DDDD)Z");
    }

    default boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    default boolean keyReleased(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.keyReleased:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    default boolean charTyped(CharacterEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.charTyped:(Lnet/minecraft/client/input/CharacterEvent;)Z");
    }

    default ComponentPath nextFocusPath(FocusNavigationEvent navigationEvent) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.nextFocusPath:(Lnet/minecraft/client/gui/navigation/FocusNavigationEvent;)Lnet/minecraft/client/gui/ComponentPath;");
    }

    default boolean isMouseOver(double mouseX, double mouseY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.isMouseOver:(DD)Z");
    }

    void setFocused(final boolean focused);

    boolean isFocused();

    default ComponentPath getCurrentFocusPath() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.getCurrentFocusPath:()Lnet/minecraft/client/gui/ComponentPath;");
    }

    default ScreenRectangle getRectangle() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/GuiEventListener.getRectangle:()Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
    }
}
