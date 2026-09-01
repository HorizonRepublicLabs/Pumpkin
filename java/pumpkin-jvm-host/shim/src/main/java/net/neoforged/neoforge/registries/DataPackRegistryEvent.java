package net.neoforged.neoforge.registries;

import com.mojang.serialization.Codec;
import java.util.function.Consumer;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class DataPackRegistryEvent extends Event implements IModBusEvent {

    public DataPackRegistryEvent() {
    }

    public static final class NewRegistry extends DataPackRegistryEvent {

        // Pumpkin divergence: real collection. A datapack registry's entries live in
        // JSON under data/<ns>/<registry-path>/, decoded by the codec the mod hands
        // over here; the loader reads them out of the mod jar after this event.
        public record PumpkinRegistration<T>(ResourceKey<Registry<T>> registryKey,
                Codec<T> codec, Consumer<RegistryBuilder<T>> consumer) {
        }

        private final java.util.List<PumpkinRegistration<?>> pumpkinRegistrations =
                new java.util.ArrayList<>();

        public java.util.List<PumpkinRegistration<?>> pumpkinRegistrations() {
            return pumpkinRegistrations;
        }

        public NewRegistry() {
        }

        public <T> void dataPackRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec) {
            dataPackRegistry(registryKey, codec, codec, builder -> { });
        }

        public <T> void dataPackRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networkCodec) {
            dataPackRegistry(registryKey, codec, networkCodec, builder -> { });
        }

        public <T> void dataPackRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networkCodec, Consumer<RegistryBuilder<T>> consumer) {
            pumpkinRegistrations.add(new PumpkinRegistration<>(registryKey, codec, consumer));
        }
    }

    record DataPackRegistryData<T>(RegistryDataLoader.RegistryData<T> loaderData, Codec<T> networkCodec) {
    }
}
