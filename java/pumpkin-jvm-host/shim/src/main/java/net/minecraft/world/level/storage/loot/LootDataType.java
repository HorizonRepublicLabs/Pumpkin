package net.minecraft.world.level.storage.loot;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextKeySet;

public record LootDataType<T extends Validatable>(ResourceKey<Registry<T>> registryKey, Codec<T> codec, LootDataType.ContextGetter<T> contextGetter, T defaultValue, Codec<java.util.Optional<T>> conditionalCodec, java.util.function.BiConsumer<T, net.minecraft.resources.Identifier> idSetter) {

    private LootDataType(ResourceKey<Registry<T>> registryKey, Codec<T> codec, LootDataType.ContextGetter<T> contextGetter) {
        this((ResourceKey<Registry<T>>) null, (Codec<T>) null, (LootDataType.ContextGetter<T>) null, (T) null, (Codec<java.util.Optional<T>>) null, (java.util.function.BiConsumer<T, net.minecraft.resources.Identifier>) null);
    }

    private LootDataType(ResourceKey<Registry<T>> registryKey, Codec<T> codec, LootDataType.ContextGetter<T> contextGetter, T defaultValue, java.util.function.BiConsumer<T, net.minecraft.resources.Identifier> idSetter) {
        this((ResourceKey<Registry<T>>) null, (Codec<T>) null, (LootDataType.ContextGetter<T>) null, (T) null, (Codec<java.util.Optional<T>>) null, (java.util.function.BiConsumer<T, net.minecraft.resources.Identifier>) null);
    }

    public interface ContextGetter<T> {

        ContextKeySet context(T value);
    }
}
