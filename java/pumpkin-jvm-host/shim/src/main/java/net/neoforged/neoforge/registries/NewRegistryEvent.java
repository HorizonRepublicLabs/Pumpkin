package net.neoforged.neoforge.registries;

import net.minecraft.core.Registry;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class NewRegistryEvent extends Event implements IModBusEvent {

    NewRegistryEvent() {
    }

    public <T> Registry<T> create(RegistryBuilder<T> builder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/NewRegistryEvent.create:(Lnet/neoforged/neoforge/registries/RegistryBuilder;)Lnet/minecraft/core/Registry;");
    }

    public <T> void register(Registry<T> registry) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/NewRegistryEvent.register:(Lnet/minecraft/core/Registry;)V");
    }
}
