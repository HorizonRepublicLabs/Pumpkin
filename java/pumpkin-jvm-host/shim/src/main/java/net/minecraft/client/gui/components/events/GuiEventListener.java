package net.minecraft.client.gui.components.events;

import net.minecraft.client.gui.components.TabOrderedElement;

public interface GuiEventListener extends TabOrderedElement {

    void setFocused(final boolean focused);

    boolean isFocused();
}
