package net.neoforged.neoforge.client.event;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterKeyMappingsEvent extends Event implements IModBusEvent {

    public RegisterKeyMappingsEvent(Options options) {
    }

    public void register(KeyMapping key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterKeyMappingsEvent.register:(Lnet/minecraft/client/KeyMapping;)V");
    }

    public void registerCategory(KeyMapping.Category category) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterKeyMappingsEvent.registerCategory:(Lnet/minecraft/client/KeyMapping$Category;)V");
    }

    public RegisterKeyMappingsEvent() {
    }
}
