package net.neoforged.neoforge.common;

import net.neoforged.bus.api.IEventBus;
import dev.pumpkin.shim.Unimplemented;

public class NeoForge {

    public static final IEventBus EVENT_BUS = null;

    protected NeoForge() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/NeoForge");
        }
    }
}
