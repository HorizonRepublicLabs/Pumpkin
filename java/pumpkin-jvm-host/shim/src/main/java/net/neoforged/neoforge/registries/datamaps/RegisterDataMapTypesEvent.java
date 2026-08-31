package net.neoforged.neoforge.registries.datamaps;

import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterDataMapTypesEvent extends Event implements IModBusEvent {

    public RegisterDataMapTypesEvent(Map<ResourceKey<Registry<?>>, Map<Identifier, DataMapType<?, ?>>> attachments) {
    }

    public <T, R> void register(DataMapType<R, T> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/RegisterDataMapTypesEvent.register:(Lnet/neoforged/neoforge/registries/datamaps/DataMapType;)V");
    }

    public RegisterDataMapTypesEvent() {
    }
}
