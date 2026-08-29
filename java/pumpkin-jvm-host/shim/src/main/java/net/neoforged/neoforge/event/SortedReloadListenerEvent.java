package net.neoforged.neoforge.event;

import java.util.List;
import java.util.function.Function;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class SortedReloadListenerEvent extends Event {

    protected SortedReloadListenerEvent(List<PreparableReloadListener> vanillaListeners, NameLookup lookup) {
    }

    public void addListener(Identifier key, PreparableReloadListener listener) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/SortedReloadListenerEvent.addListener:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/server/packs/resources/PreparableReloadListener;)V");
    }

    public interface NameLookup extends Function<PreparableReloadListener, Identifier> {

        Identifier apply(PreparableReloadListener t);
    }

    public SortedReloadListenerEvent() {
    }
}
