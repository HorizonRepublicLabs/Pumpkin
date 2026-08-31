package net.neoforged.neoforge.common.world.chunk;

import java.util.function.Consumer;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterTicketControllersEvent extends Event implements IModBusEvent {

    RegisterTicketControllersEvent(Consumer<TicketController> registrar) {
    }

    public void register(TicketController controller) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/RegisterTicketControllersEvent.register:(Lnet/neoforged/neoforge/common/world/chunk/TicketController;)V");
    }

    public RegisterTicketControllersEvent() {
    }
}
