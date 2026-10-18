package net.neoforged.neoforge.network.event;

import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

// Pumpkin divergence: registrar() hands out a working PayloadRegistrar instead of throwing.
// Pinned (generator/pinned/), so edit that copy too. Bootstrap posts this once per mod.
public class RegisterPayloadHandlersEvent extends Event implements IModBusEvent {

    public RegisterPayloadHandlersEvent() {
    }

    public PayloadRegistrar registrar(String version) {
        return new PayloadRegistrar(version);
    }
}
