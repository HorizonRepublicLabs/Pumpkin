package net.minecraft.core;

import java.util.Optional;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import dev.pumpkin.shim.Unimplemented;

public interface HolderGetter<T> {

    Optional<Holder.Reference<T>> get(final ResourceKey<T> id);

    Optional<HolderSet.Named<T>> get(final TagKey<T> id);

    interface Provider {

        <T> Optional<? extends HolderGetter<T>> lookup(final ResourceKey<? extends Registry<? extends T>> key);

        default <T> Optional<Holder.Reference<T>> get(ResourceKey<T> id) {
            throw Unimplemented.forMember("net/minecraft/core/HolderGetter$Provider.get:(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;");
        }

        default <T> Optional<HolderSet.Named<T>> get(TagKey<T> id) {
            throw Unimplemented.forMember("net/minecraft/core/HolderGetter$Provider.get:(Lnet/minecraft/tags/TagKey;)Ljava/util/Optional;");
        }
    }
}
