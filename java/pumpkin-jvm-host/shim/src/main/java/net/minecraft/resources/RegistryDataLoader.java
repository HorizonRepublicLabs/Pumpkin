package net.minecraft.resources;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySynchronization;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagNetworkSerialization;
import dev.pumpkin.shim.Unimplemented;

public class RegistryDataLoader {

    public static CompletableFuture<RegistryAccess.Frozen> load(ResourceManager resourceManager, List<HolderLookup.RegistryLookup<?>> contextRegistries, List<RegistryDataLoader.RegistryData<?>> registriesToLoad, Executor executor) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryDataLoader.load:(Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/List;Ljava/util/List;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    private interface LoaderFactory {

        <T> RegistryLoadTask<T> create(RegistryDataLoader.RegistryData<T> data, Map<ResourceKey<?>, Exception> loadingErrors);
    }

    public record NetworkedRegistryData(List<RegistrySynchronization.PackedRegistryEntry> elements, TagNetworkSerialization.NetworkPayload tags) {
    }

    public record RegistryData<T>(ResourceKey<? extends Registry<T>> key, Codec<T> elementCodec, RegistryValidator<T> validator, java.util.function.Consumer<net.neoforged.neoforge.registries.RegistryBuilder<T>> registryBuilderConsumer) {

        public RegistryData(ResourceKey<? extends Registry<T>> key, Codec<T> elementCodec, RegistryValidator<T> validator) {
            this((ResourceKey<? extends Registry<T>>) null, (Codec<T>) null, (RegistryValidator<T>) null, (java.util.function.Consumer<net.neoforged.neoforge.registries.RegistryBuilder<T>>) null);
        }

        private RegistryData(ResourceKey<? extends Registry<T>> key, Codec<T> elementCodec) {
            this((ResourceKey<? extends Registry<T>>) null, (Codec<T>) null, (RegistryValidator<T>) null, (java.util.function.Consumer<net.neoforged.neoforge.registries.RegistryBuilder<T>>) null);
        }
    }

    public RegistryDataLoader() {
    }
}
