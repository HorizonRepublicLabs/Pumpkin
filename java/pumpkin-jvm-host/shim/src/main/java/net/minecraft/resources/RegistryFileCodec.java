package net.minecraft.resources;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import dev.pumpkin.shim.Unimplemented;

public final class RegistryFileCodec<E> implements Codec<Holder<E>> {

    // Pumpkin divergence: real construction, inert behaviour -- the codec exists and
    // composes; encode/decode below still throw their member keys on first use.
    public static <E> RegistryFileCodec<E> create(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec) {
        return new RegistryFileCodec<>(registryKey, elementCodec, true);
    }

    public static <E> RegistryFileCodec<E> create(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec, boolean allowInline) {
        return new RegistryFileCodec<>(registryKey, elementCodec, allowInline);
    }

    private RegistryFileCodec(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec, boolean allowInline) {
    }

    public <T> DataResult<T> encode(Holder<E> input, DynamicOps<T> ops, T prefix) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.encode:(Lnet/minecraft/core/Holder;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public <T> DataResult<Pair<Holder<E>, T>> decode(DynamicOps<T> ops, T input) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.decode:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    // Pumpkin divergence: real body -- DFU prints codecs while composing them.
    public String toString() {
        return "RegistryFileCodec[pumpkin inert]";
    }

    public RegistryFileCodec() {
    }
}
