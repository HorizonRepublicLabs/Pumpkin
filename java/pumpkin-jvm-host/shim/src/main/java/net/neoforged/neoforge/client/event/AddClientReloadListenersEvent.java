package net.neoforged.neoforge.client.event;

import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.event.SortedReloadListenerEvent;

public class AddClientReloadListenersEvent extends SortedReloadListenerEvent implements IModBusEvent {

    public AddClientReloadListenersEvent(ReloadableResourceManager resourceManager) {
    }

    public AddClientReloadListenersEvent() {
    }
}
