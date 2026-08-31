package net.minecraft.resources;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import dev.pumpkin.shim.Unimplemented;

public final class RegistryFileCodec<E> implements Codec<Holder<E>> {

    public static <E> RegistryFileCodec<E> create(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.create:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lnet/minecraft/resources/RegistryFileCodec;");
    }

    public static <E> RegistryFileCodec<E> create(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec, boolean allowInline) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.create:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lnet/minecraft/resources/RegistryFileCodec;");
    }

    private RegistryFileCodec(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec, boolean allowInline) {
    }

    public <T> DataResult<T> encode(Holder<E> input, DynamicOps<T> ops, T prefix) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.encode:(Lnet/minecraft/core/Holder;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public <T> DataResult<Pair<Holder<E>, T>> decode(DynamicOps<T> ops, T input) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.decode:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.toString:()Ljava/lang/String;");
    }

    public RegistryFileCodec() {
    }
}
