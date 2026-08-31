package net.neoforged.neoforge.client.gui.modlist;

import net.neoforged.fml.ModContainer;

// The base a mod's display info extends. Only the identity accessors have real bodies:
// they are what mod subclasses call from their own overrides.
public class DefaultModDisplayInfo implements ModDisplayInfo {
    private final ModContainer container;

    public DefaultModDisplayInfo(ModContainer container) {
        this.container = container;
    }

    public ModContainer container() {
        return container;
    }

    public String id() {
        return container.getModId();
    }
}
