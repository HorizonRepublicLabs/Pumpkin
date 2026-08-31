package net.neoforged.neoforge.registries.datamaps;

import java.util.function.Consumer;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public class DataMapsUpdatedEvent extends Event {

    public DataMapsUpdatedEvent(RegistryAccess registryAccess, Registry<?> registry, UpdateCause cause) {
    }

    public <T> void ifRegistry(ResourceKey<Registry<T>> type, Consumer<Registry<T>> consumer) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapsUpdatedEvent.ifRegistry:(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Consumer;)V");
    }

    public enum UpdateCause {

        CLIENT_SYNC, SERVER_RELOAD
    }

    public DataMapsUpdatedEvent() {
    }
}
