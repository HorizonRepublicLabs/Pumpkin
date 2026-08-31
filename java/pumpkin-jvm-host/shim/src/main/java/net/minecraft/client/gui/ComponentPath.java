package net.minecraft.client.gui;

import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import dev.pumpkin.shim.Unimplemented;

public interface ComponentPath {

    static ComponentPath leaf(GuiEventListener component) {
        throw Unimplemented.forMember("net/minecraft/client/gui/ComponentPath.leaf:(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/ComponentPath;");
    }

    static ComponentPath path(ContainerEventHandler container, ComponentPath childPath) {
        throw Unimplemented.forMember("net/minecraft/client/gui/ComponentPath.path:(Lnet/minecraft/client/gui/components/events/ContainerEventHandler;Lnet/minecraft/client/gui/ComponentPath;)Lnet/minecraft/client/gui/ComponentPath;");
    }

    static ComponentPath path(GuiEventListener target, ContainerEventHandler... containerPath) {
        throw Unimplemented.forMember("net/minecraft/client/gui/ComponentPath.path:(Lnet/minecraft/client/gui/components/events/GuiEventListener;[Lnet/minecraft/client/gui/components/events/ContainerEventHandler;)Lnet/minecraft/client/gui/ComponentPath;");
    }

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
