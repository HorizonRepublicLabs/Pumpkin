package net.minecraft.core;

import java.util.Optional;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import dev.pumpkin.shim.Unimplemented;

public interface HolderGetter<T> {

    Optional<Holder.Reference<T>> get(final ResourceKey<T> id);

    default Holder.Reference<T> getOrThrow(ResourceKey<T> id) {
        throw Unimplemented.forMember("net/minecraft/core/HolderGetter.getOrThrow:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/core/Holder$Reference;");
    }

    Optional<HolderSet.Named<T>> get(final TagKey<T> id);

    default HolderSet.Named<T> getOrThrow(TagKey<T> id) {
        throw Unimplemented.forMember("net/minecraft/core/HolderGetter.getOrThrow:(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/core/HolderSet$Named;");
    }

    interface Provider {

        <T> Optional<? extends HolderGetter<T>> lookup(final ResourceKey<? extends Registry<? extends T>> key);

        default <T> HolderGetter<T> lookupOrThrow(ResourceKey<? extends Registry<? extends T>> key) {
            throw Unimplemented.forMember("net/minecraft/core/HolderGetter$Provider.lookupOrThrow:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/core/HolderGetter;");
        }

        default <T> Optional<Holder.Reference<T>> get(ResourceKey<T> id) {
            throw Unimplemented.forMember("net/minecraft/core/HolderGetter$Provider.get:(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;");
        }

        default <T> Holder.Reference<T> getOrThrow(ResourceKey<T> id) {
            throw Unimplemented.forMember("net/minecraft/core/HolderGetter$Provider.getOrThrow:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/core/Holder$Reference;");
        }

        default <T> Optional<HolderSet.Named<T>> get(TagKey<T> id) {
            throw Unimplemented.forMember("net/minecraft/core/HolderGetter$Provider.get:(Lnet/minecraft/tags/TagKey;)Ljava/util/Optional;");
        }

        default <T> HolderSet.Named<T> getOrThrow(TagKey<T> id) {
            throw Unimplemented.forMember("net/minecraft/core/HolderGetter$Provider.getOrThrow:(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/core/HolderSet$Named;");
        }
    }
}
