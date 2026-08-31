package net.neoforged.neoforge.common;

import net.neoforged.bus.api.IEventBus;
import dev.pumpkin.shim.Unimplemented;

public class NeoForge {

    // Pumpkin divergence: a real bus. Subscriptions land and are kept; the game events
    // NeoForge would post here fire only as Pumpkin grows senders for them, so a
    // listener may wait forever -- but the mod's registration itself succeeds and is
    // inspectable, where a throwing holder stopped construction cold.
    public static final IEventBus EVENT_BUS = new dev.pumpkin.shim.PumpkinEventBus();

    public NeoForge() {
    }
}
