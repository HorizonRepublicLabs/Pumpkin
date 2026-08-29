package net.neoforged.fml.event.lifecycle;

import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;

/**
 * Hand-written, not generated: FML is published as a separate NeoForge artifact whose
 * sources are not in the decompiled tree. On the generator's "no source found" list; do
 * not delete it as un-regenerable.
 *
 * <p>The client-side twin of {@link FMLCommonSetupEvent}. Pumpkin is a server and will
 * never fire it, but both mods declare a listener method taking it, so the type has to
 * exist for their classes to resolve at all -- a parameter type is part of a method's
 * descriptor whether or not the method is ever called.
 */
public class FMLClientSetupEvent extends Event implements IModBusEvent {
    public FMLClientSetupEvent() {
    }
}
