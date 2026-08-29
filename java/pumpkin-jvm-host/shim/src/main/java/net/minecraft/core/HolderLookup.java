package net.minecraft.core;

import com.mojang.serialization.Lifecycle;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
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

        static HolderLookup.Provider create(Stream<HolderLookup.RegistryLookup<?>> lookups) {
            throw Unimplemented.forMember("net/minecraft/core/HolderLookup$Provider.create:(Ljava/util/stream/Stream;)Lnet/minecraft/core/HolderLookup$Provider;");
        }
    }

    interface RegistryLookup<T> extends HolderLookup<T>, HolderOwner<T> {

        ResourceKey<? extends Registry<? extends T>> key();

        Lifecycle registryLifecycle();

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
