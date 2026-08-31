package net.neoforged.neoforge.client.event;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterItemModelsEvent extends Event implements IModBusEvent {

    public RegisterItemModelsEvent(ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends ItemModel.Unbaked>> idMapper) {
    }

    public void register(Identifier location, MapCodec<? extends ItemModel.Unbaked> source) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterItemModelsEvent.register:(Lnet/minecraft/resources/Identifier;Lcom/mojang/serialization/MapCodec;)V");
    }

    public RegisterItemModelsEvent() {
    }
}
