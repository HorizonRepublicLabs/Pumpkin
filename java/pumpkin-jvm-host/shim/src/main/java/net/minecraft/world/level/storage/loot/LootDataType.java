package net.minecraft.world.level.storage.loot;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextKeySet;
import dev.pumpkin.shim.Unimplemented;

public record LootDataType<T extends Validatable>(ResourceKey<Registry<T>> registryKey, Codec<T> codec, LootDataType.ContextGetter<T> contextGetter, T defaultValue, Codec<java.util.Optional<T>> conditionalCodec, java.util.function.BiConsumer<T, net.minecraft.resources.Identifier> idSetter) {

    private LootDataType(ResourceKey<Registry<T>> registryKey, Codec<T> codec, LootDataType.ContextGetter<T> contextGetter) {
        this((ResourceKey<Registry<T>>) null, (Codec<T>) null, (LootDataType.ContextGetter<T>) null, (T) null, (Codec<java.util.Optional<T>>) null, (java.util.function.BiConsumer<T, net.minecraft.resources.Identifier>) null);
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootDataType.<init>:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Lnet/minecraft/world/level/storage/loot/LootDataType$ContextGetter;)V");
    }

    private LootDataType(ResourceKey<Registry<T>> registryKey, Codec<T> codec, LootDataType.ContextGetter<T> contextGetter, T defaultValue, java.util.function.BiConsumer<T, net.minecraft.resources.Identifier> idSetter) {
        this((ResourceKey<Registry<T>>) null, (Codec<T>) null, (LootDataType.ContextGetter<T>) null, (T) null, (Codec<java.util.Optional<T>>) null, (java.util.function.BiConsumer<T, net.minecraft.resources.Identifier>) null);
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootDataType.<init>:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Lnet/minecraft/world/level/storage/loot/LootDataType$ContextGetter;Lnet/minecraft/world/level/storage/loot/Validatable;Ljava/util/function/BiConsumer;)V");
    }

    public interface ContextGetter<T> {

        ContextKeySet context(T value);
    }
}
