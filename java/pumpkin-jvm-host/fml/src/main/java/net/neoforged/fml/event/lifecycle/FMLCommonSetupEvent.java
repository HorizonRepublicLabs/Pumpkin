package net.neoforged.fml.event.lifecycle;

import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;

/**
 * Hand-written, not generated: FML is published as a separate NeoForge artifact whose
 * sources are not in the decompiled tree. On the generator's "no source found" list; do
 * not delete it as un-regenerable.
 *
 * <p>Fired once, on both sides, after registration. The manifest records no member on it:
 * the mods name the type as a listener parameter and nothing more, so the type is the
 * whole contract. In NeoForge it extends {@code ParallelDispatchEvent}, which exists only
 * to carry {@code enqueueWork}; nothing here calls that, and inventing the intermediate
 * class would add a name no mod references.
 */
public class FMLCommonSetupEvent extends Event implements IModBusEvent {
    public FMLCommonSetupEvent() {
    }
}
