package net.neoforged.neoforge.client.event;

import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterSelectItemModelPropertyEvent extends Event implements IModBusEvent {

    public RegisterSelectItemModelPropertyEvent(ExtraCodecs.LateBoundIdMapper<Identifier, SelectItemModelProperty.Type<?, ?>> idMapper) {
    }

    public void register(Identifier location, SelectItemModelProperty.Type<?, ?> source) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterSelectItemModelPropertyEvent.register:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/renderer/item/properties/select/SelectItemModelProperty$Type;)V");
    }

    public RegisterSelectItemModelPropertyEvent() {
    }
}
