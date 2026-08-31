package net.neoforged.neoforge.client.event;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterSpecialModelRendererEvent extends Event implements IModBusEvent {

    public RegisterSpecialModelRendererEvent(ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked<?>>> idMapper) {
    }

    public void register(Identifier location, MapCodec<? extends SpecialModelRenderer.Unbaked<?>> source) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterSpecialModelRendererEvent.register:(Lnet/minecraft/resources/Identifier;Lcom/mojang/serialization/MapCodec;)V");
    }

    public RegisterSpecialModelRendererEvent() {
    }
}
