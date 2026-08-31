package net.neoforged.neoforge.client.event;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class TextureAtlasStitchedEvent extends Event implements IModBusEvent {

    public TextureAtlasStitchedEvent(TextureAtlas atlas) {
    }

    public TextureAtlas getAtlas() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/TextureAtlasStitchedEvent.getAtlas:()Lnet/minecraft/client/renderer/texture/TextureAtlas;");
    }

    public TextureAtlasStitchedEvent() {
    }
}
