package net.neoforged.neoforge.network.event;

import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import dev.pumpkin.shim.Unimplemented;

public class RegisterPayloadHandlersEvent extends Event implements IModBusEvent {

    public RegisterPayloadHandlersEvent() {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/event/RegisterPayloadHandlersEvent.<init>:()V");
    }

    public PayloadRegistrar registrar(String version) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/event/RegisterPayloadHandlersEvent.registrar:(Ljava/lang/String;)Lnet/neoforged/neoforge/network/registration/PayloadRegistrar;");
    }
}
