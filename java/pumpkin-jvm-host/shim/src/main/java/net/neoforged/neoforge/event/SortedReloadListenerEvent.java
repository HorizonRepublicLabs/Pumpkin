package net.neoforged.neoforge.event;

import java.util.List;
import java.util.function.Function;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class SortedReloadListenerEvent extends Event {

    protected SortedReloadListenerEvent(List<PreparableReloadListener> vanillaListeners, NameLookup lookup) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/SortedReloadListenerEvent.<init>:(Ljava/util/List;Lnet/neoforged/neoforge/event/SortedReloadListenerEvent$NameLookup;)V");
    }

    public interface NameLookup extends Function<PreparableReloadListener, Identifier> {

        Identifier apply(PreparableReloadListener t);
    }

    protected SortedReloadListenerEvent() {
    }
}
