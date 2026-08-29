package net.neoforged.neoforge.event;

import java.util.Map;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.neoforged.neoforge.resource.ListenerKey;

public class AddServerReloadListenersEvent extends SortedReloadListenerEvent {

    public AddServerReloadListenersEvent(ReloadableServerResources serverResources, RegistryAccess registryAccess, Map<ListenerKey<?>, PreparableReloadListener> retainedListeners) {
    }

    public AddServerReloadListenersEvent() {
    }
}
