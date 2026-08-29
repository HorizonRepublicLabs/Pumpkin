package net.minecraft.client.gui.components.events;

import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractContainerEventHandler implements ContainerEventHandler {

    public final boolean isDragging() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/AbstractContainerEventHandler.isDragging:()Z");
    }

    public final void setDragging(boolean dragging) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/AbstractContainerEventHandler.setDragging:(Z)V");
    }

    public GuiEventListener getFocused() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/AbstractContainerEventHandler.getFocused:()Lnet/minecraft/client/gui/components/events/GuiEventListener;");
    }

    public void setFocused(GuiEventListener focused) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/events/AbstractContainerEventHandler.setFocused:(Lnet/minecraft/client/gui/components/events/GuiEventListener;)V");
    }

    public AbstractContainerEventHandler() {
    }
}
