package net.neoforged.neoforge.client.event;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterRangeSelectItemModelPropertyEvent extends Event implements IModBusEvent {

    public RegisterRangeSelectItemModelPropertyEvent(ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends RangeSelectItemModelProperty>> idMapper) {
    }

    public void register(Identifier location, MapCodec<? extends RangeSelectItemModelProperty> source) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterRangeSelectItemModelPropertyEvent.register:(Lnet/minecraft/resources/Identifier;Lcom/mojang/serialization/MapCodec;)V");
    }

    public RegisterRangeSelectItemModelPropertyEvent() {
    }
}
