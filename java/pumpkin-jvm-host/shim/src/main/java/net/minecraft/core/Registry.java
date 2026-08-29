package net.minecraft.core;

import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Stream;
import net.minecraft.core.component.DataComponentLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagLoader;
import net.minecraft.util.RandomSource;
import net.neoforged.neoforge.common.extensions.PendingTagsExtension;
import net.neoforged.neoforge.registries.IRegistryExtension;
import dev.pumpkin.shim.Unimplemented;

public interface Registry<T> extends IdMap<T>, Keyable, HolderLookup.RegistryLookup<T>, IRegistryExtension<T> {

    ResourceKey<? extends Registry<T>> key();

    <A> java.util.Map<ResourceKey<T>, A> getDataMap(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A> type);

    default <U> Stream<U> keys(DynamicOps<U> ops) {
        throw Unimplemented.forMember("net/minecraft/core/Registry.keys:(Lcom/mojang/serialization/DynamicOps;)Ljava/util/stream/Stream;");
    }

    Identifier getKey(T thing);

    Optional<ResourceKey<T>> getResourceKey(T thing);

    int getId(T thing);

    T getValue(ResourceKey<T> key);

    T getValue(Identifier key);

    Optional<RegistrationInfo> registrationInfo(ResourceKey<T> element);

    Optional<Holder.Reference<T>> getAny();

    Set<Identifier> keySet();

    Set<Entry<ResourceKey<T>, T>> entrySet();

    Set<ResourceKey<T>> registryKeySet();

    Optional<Holder.Reference<T>> getRandom(RandomSource random);

    default Stream<T> stream() {
        throw Unimplemented.forMember("net/minecraft/core/Registry.stream:()Ljava/util/stream/Stream;");
    }

    boolean containsKey(Identifier key);

    boolean containsKey(ResourceKey<T> key);

    Registry<T> freeze();

    Holder.Reference<T> createIntrusiveHolder(T value);

    Optional<Holder.Reference<T>> get(int id);

    Optional<Holder.Reference<T>> get(Identifier id);

    Holder<T> wrapAsHolder(T value);

    Stream<HolderSet.Named<T>> getTags();

    Registry.PendingTags<T> prepareTagReload(TagLoader.LoadResult<T> tags);

    DataComponentLookup<T> componentLookup();

    interface PendingTags<T> extends PendingTagsExtension<T> {

        ResourceKey<? extends Registry<? extends T>> key();

        HolderLookup.RegistryLookup<T> lookup();

        void apply();

        int size();
    }
}
