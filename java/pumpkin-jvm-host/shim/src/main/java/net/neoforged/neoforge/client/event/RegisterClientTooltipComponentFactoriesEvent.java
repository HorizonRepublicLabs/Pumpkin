package net.neoforged.neoforge.client.event;

import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterClientTooltipComponentFactoriesEvent extends Event implements IModBusEvent {

    public RegisterClientTooltipComponentFactoriesEvent(Map<Class<? extends TooltipComponent>, Function<TooltipComponent, ClientTooltipComponent>> factories) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterClientTooltipComponentFactoriesEvent.<init>:(Ljava/util/Map;)V");
    }

    public <T extends TooltipComponent> void register(Class<T> type, Function<? super T, ? extends ClientTooltipComponent> factory) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterClientTooltipComponentFactoriesEvent.register:(Ljava/lang/Class;Ljava/util/function/Function;)V");
    }

    public RegisterClientTooltipComponentFactoriesEvent() {
    }
}
