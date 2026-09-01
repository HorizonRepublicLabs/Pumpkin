package net.minecraft.core;

import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.extensions.IHolderLookupProviderExtension;
import dev.pumpkin.shim.Unimplemented;

public interface HolderLookup<T> extends HolderGetter<T> {

    Stream<Holder.Reference<T>> listElements();

    Stream<HolderSet.Named<T>> listTags();

    interface Provider extends HolderGetter.Provider, IHolderLookupProviderExtension {

        Stream<ResourceKey<? extends Registry<?>>> listRegistryKeys();

        <T> Optional<? extends HolderLookup.RegistryLookup<T>> lookup(final ResourceKey<? extends Registry<? extends T>> key);

        // Pumpkin divergence: answers key() and listElements() from what actually
        // registered under that registry; every other member throws by name on use.
        @SuppressWarnings({"unchecked", "rawtypes"})
        default <T> HolderLookup.RegistryLookup<T> lookupOrThrow(ResourceKey<? extends Registry<? extends T>> key) {
            return dev.pumpkin.shim.Stubs.of(HolderLookup.RegistryLookup.class,
                "net/minecraft/core/HolderLookup$RegistryLookup(" + key.identifier() + ") via HolderLookup$Provider.lookupOrThrow",
                java.util.Map.of(
                    "key", key,
                    "listElements", (dev.pumpkin.shim.Stubs.Dynamic) args ->
                        net.neoforged.neoforge.registries.DeferredHolder.pumpkinAllFor(key.identifier().toString())
                            .stream()
                            .map(holder -> Holder.Reference.pumpkinOf((ResourceKey) holder.getKey(), holder.get())),
                    // get(TagKey) answers empty -- no tag files target these registries;
                    // get(ResourceKey) answers the registered holder when there is one.
                    "get", (dev.pumpkin.shim.Stubs.Dynamic) args -> {
                        if (args != null && args.length == 1
                                && args[0] instanceof net.minecraft.resources.ResourceKey<?> valueKey) {
                            for (net.neoforged.neoforge.registries.DeferredHolder<?, ?> holder
                                    : net.neoforged.neoforge.registries.DeferredHolder
                                            .pumpkinAllFor(key.identifier().toString())) {
                                if (holder.getKey().equals(valueKey)) {
                                    return java.util.Optional.of(Holder.Reference.pumpkinOf(
                                            (ResourceKey) holder.getKey(), holder.get()));
                                }
                            }
                        }
                        return java.util.Optional.empty();
                    }));
        }

        default <V> RegistryOps<V> createSerializationContext(DynamicOps<V> parent) {
            throw Unimplemented.forMember("net/minecraft/core/HolderLookup$Provider.createSerializationContext:(Lcom/mojang/serialization/DynamicOps;)Lnet/minecraft/resources/RegistryOps;");
        }

        static HolderLookup.Provider create(Stream<HolderLookup.RegistryLookup<?>> lookups) {
            throw Unimplemented.forMember("net/minecraft/core/HolderLookup$Provider.create:(Ljava/util/stream/Stream;)Lnet/minecraft/core/HolderLookup$Provider;");
        }
    }

    interface RegistryLookup<T> extends HolderLookup<T>, HolderOwner<T> {

        ResourceKey<? extends Registry<? extends T>> key();

        Lifecycle registryLifecycle();

        default HolderLookup.RegistryLookup<T> filterElements(Predicate<T> filter) {
            throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup.filterElements:(Ljava/util/function/Predicate;)Lnet/minecraft/core/HolderLookup$RegistryLookup;");
        }

        default <A> Map<ResourceKey<T>, A> getDataMap(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A> type) {
            throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup.getDataMap:(Lnet/neoforged/neoforge/registries/datamaps/DataMapType;)Ljava/util/Map;");
        }

        interface Delegate<T> extends HolderLookup.RegistryLookup<T> {

            HolderLookup.RegistryLookup<T> parent();

            default ResourceKey<? extends Registry<? extends T>> key() {
                throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup$Delegate.key:()Lnet/minecraft/resources/ResourceKey;");
            }

            default Lifecycle registryLifecycle() {
                throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup$Delegate.registryLifecycle:()Lcom/mojang/serialization/Lifecycle;");
            }

            default Optional<Holder.Reference<T>> get(ResourceKey<T> id) {
                throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup$Delegate.get:(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;");
            }

            default Stream<Holder.Reference<T>> listElements() {
                throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup$Delegate.listElements:()Ljava/util/stream/Stream;");
            }

            default Optional<HolderSet.Named<T>> get(TagKey<T> id) {
                throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup$Delegate.get:(Lnet/minecraft/tags/TagKey;)Ljava/util/Optional;");
            }

            default Stream<HolderSet.Named<T>> listTags() {
                throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup$Delegate.listTags:()Ljava/util/stream/Stream;");
            }

            default <A> A getData(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A> type, ResourceKey<T> key) {
                throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup$Delegate.getData:(Lnet/neoforged/neoforge/registries/datamaps/DataMapType;Lnet/minecraft/resources/ResourceKey;)Ljava/lang/Object;");
            }

            default <A> Map<ResourceKey<T>, A> getDataMap(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A> type) {
                throw Unimplemented.forMember("net/minecraft/core/HolderLookup$RegistryLookup$Delegate.getDataMap:(Lnet/neoforged/neoforge/registries/datamaps/DataMapType;)Ljava/util/Map;");
            }
        }
    }
}
