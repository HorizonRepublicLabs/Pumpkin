package net.minecraft.client.gui.components.events;

import java.util.List;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.navigation.ScreenDirection;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.PreeditEvent;
import dev.pumpkin.shim.Unimplemented;

public interface ContainerEventHandler extends GuiEventListener {

    List<? extends GuiEventListener> children();

    default boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.mouseClicked:(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z");
    }

    default boolean mouseReleased(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.mouseReleased:(Lnet/minecraft/client/input/MouseButtonEvent;)Z");
    }

    default boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.mouseDragged:(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z");
    }

    boolean isDragging();

    void setDragging(boolean dragging);

    default boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.mouseScrolled:(DDDD)Z");
    }

    default boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    default boolean keyReleased(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.keyReleased:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    default boolean charTyped(CharacterEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.charTyped:(Lnet/minecraft/client/input/CharacterEvent;)Z");
    }

    default boolean preeditUpdated(PreeditEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.preeditUpdated:(Lnet/minecraft/client/input/PreeditEvent;)Z");
    }

    default ScreenRectangle getBorderForArrowNavigation(ScreenDirection opposite) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.getBorderForArrowNavigation:(Lnet/minecraft/client/gui/navigation/ScreenDirection;)Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
    }

    GuiEventListener getFocused();

    void setFocused(final GuiEventListener focused);

    default void setFocused(boolean focused) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.setFocused:(Z)V");
    }

    default boolean isFocused() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.isFocused:()Z");
    }

    default ComponentPath getCurrentFocusPath() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.getCurrentFocusPath:()Lnet/minecraft/client/gui/ComponentPath;");
    }

    default ComponentPath nextFocusPath(FocusNavigationEvent navigationEvent) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/ContainerEventHandler.nextFocusPath:(Lnet/minecraft/client/gui/navigation/FocusNavigationEvent;)Lnet/minecraft/client/gui/ComponentPath;");
    }
}
