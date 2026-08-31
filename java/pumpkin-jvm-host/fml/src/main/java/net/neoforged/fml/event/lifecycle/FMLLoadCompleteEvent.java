package net.neoforged.fml.event.lifecycle;

import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;

/** Lifecycle marker, same contract as FMLCommonSetupEvent: mods name it as a listener
 * parameter and nothing more, so the type is the whole surface. */
public class FMLLoadCompleteEvent extends Event implements IModBusEvent {
    public FMLLoadCompleteEvent() {
    }
}
