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

        public NewRegistry() {
        }

        public <T> void dataPackRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DataPackRegistryEvent$NewRegistry.dataPackRegistry:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)V");
        }

        public <T> void dataPackRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networkCodec) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DataPackRegistryEvent$NewRegistry.dataPackRegistry:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Lcom/mojang/serialization/Codec;)V");
        }

        public <T> void dataPackRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networkCodec, Consumer<RegistryBuilder<T>> consumer) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DataPackRegistryEvent$NewRegistry.dataPackRegistry:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Lcom/mojang/serialization/Codec;Ljava/util/function/Consumer;)V");
        }
    }

    record DataPackRegistryData<T>(RegistryDataLoader.RegistryData<T> loaderData, Codec<T> networkCodec) {
    }
}
