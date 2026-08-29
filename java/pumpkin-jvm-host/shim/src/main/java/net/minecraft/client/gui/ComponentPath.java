package net.minecraft.client.gui;

import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import dev.pumpkin.shim.Unimplemented;

public interface ComponentPath {

    GuiEventListener component();

    void applyFocus(boolean focused);

    GuiEventListener leafComponent();

    record Leaf(GuiEventListener component) implements ComponentPath {

        public void applyFocus(boolean focused) {
            throw Unimplemented.forMember("net/minecraft/client/gui/ComponentPath$Leaf.applyFocus:(Z)V");
        }

        public GuiEventListener leafComponent() {
            throw Unimplemented.forMember("net/minecraft/client/gui/ComponentPath$Leaf.leafComponent:()Lnet/minecraft/client/gui/components/events/GuiEventListener;");
        }
    }

    record Path(ContainerEventHandler component, ComponentPath childPath) implements ComponentPath {

        public void applyFocus(boolean focused) {
            throw Unimplemented.forMember("net/minecraft/client/gui/ComponentPath$Path.applyFocus:(Z)V");
        }

        public GuiEventListener leafComponent() {
            throw Unimplemented.forMember("net/minecraft/client/gui/ComponentPath$Path.leafComponent:()Lnet/minecraft/client/gui/components/events/GuiEventListener;");
        }
    }
}
