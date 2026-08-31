package net.neoforged.neoforge.client.event;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterConditionalItemModelPropertyEvent extends Event implements IModBusEvent {

    public RegisterConditionalItemModelPropertyEvent(ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends ConditionalItemModelProperty>> idMapper) {
    }

    public void register(Identifier location, MapCodec<? extends ConditionalItemModelProperty> source) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterConditionalItemModelPropertyEvent.register:(Lnet/minecraft/resources/Identifier;Lcom/mojang/serialization/MapCodec;)V");
    }

    public RegisterConditionalItemModelPropertyEvent() {
    }
}
