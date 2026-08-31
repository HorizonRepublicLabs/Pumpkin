package net.minecraft.resources;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import dev.pumpkin.shim.Unimplemented;

public final class RegistryFixedCodec<E> implements Codec<Holder<E>> {

    public static <E> RegistryFixedCodec<E> create(ResourceKey<? extends Registry<E>> registryKey) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFixedCodec.create:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/resources/RegistryFixedCodec;");
    }

    private RegistryFixedCodec(ResourceKey<? extends Registry<E>> registryKey) {
    }

    public <T> DataResult<T> encode(Holder<E> input, DynamicOps<T> ops, T prefix) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFixedCodec.encode:(Lnet/minecraft/core/Holder;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public <T> DataResult<Pair<Holder<E>, T>> decode(DynamicOps<T> ops, T input) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFixedCodec.decode:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFixedCodec.toString:()Ljava/lang/String;");
    }

    public RegistryFixedCodec() {
    }
}
