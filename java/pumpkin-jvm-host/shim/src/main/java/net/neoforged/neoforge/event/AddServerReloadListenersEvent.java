package net.neoforged.neoforge.event;

import java.util.Map;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.neoforged.neoforge.resource.ListenerKey;
import dev.pumpkin.shim.Unimplemented;

public class AddServerReloadListenersEvent extends SortedReloadListenerEvent {

    public AddServerReloadListenersEvent(ReloadableServerResources serverResources, RegistryAccess registryAccess, Map<ListenerKey<?>, PreparableReloadListener> retainedListeners) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/AddServerReloadListenersEvent.<init>:(Lnet/minecraft/server/ReloadableServerResources;Lnet/minecraft/core/RegistryAccess;Ljava/util/Map;)V");
    }

    public AddServerReloadListenersEvent() {
    }
}
