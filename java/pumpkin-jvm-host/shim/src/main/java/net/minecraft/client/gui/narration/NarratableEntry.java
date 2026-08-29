package net.minecraft.client.gui.narration;

import net.minecraft.client.gui.components.TabOrderedElement;
import dev.pumpkin.shim.Unimplemented;

public interface NarratableEntry extends NarrationSupplier, TabOrderedElement {

    NarratableEntry.NarrationPriority narrationPriority();

    default boolean isActive() {
        throw Unimplemented.forMember("net/minecraft/client/gui/narration/NarratableEntry.isActive:()Z");
    }

    enum NarrationPriority {

        NONE, HOVERED, FOCUSED
    }
}
