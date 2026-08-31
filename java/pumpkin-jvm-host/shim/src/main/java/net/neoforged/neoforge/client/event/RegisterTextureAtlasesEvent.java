package net.neoforged.neoforge.client.event;

import java.util.SequencedMap;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterTextureAtlasesEvent extends Event implements IModBusEvent {

    public RegisterTextureAtlasesEvent(SequencedMap<Identifier, AtlasManager.AtlasConfig> atlases) {
    }

    public void register(AtlasManager.AtlasConfig atlasConfig) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterTextureAtlasesEvent.register:(Lnet/minecraft/client/resources/model/sprite/AtlasManager$AtlasConfig;)V");
    }

    public RegisterTextureAtlasesEvent() {
    }
}
