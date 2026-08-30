package net.minecraft.network.codec;

import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import net.minecraft.core.IdMap;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface ByteBufCodecs {

    StreamCodec<ByteBuf, Boolean> BOOL = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Integer> INT = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Double> DOUBLE = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    // Pumpkin divergence: real-enough body. The operation composes a list codec Pumpkin
    // never invokes -- nothing serialises yet -- so composition survives and the first
    // actual encode/decode throws with the interface's name.

    static <B extends ByteBuf, V> StreamCodec.CodecOperation<B, V, List<V>> list() {
        return original -> dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    }
    // Pumpkin divergence: real-enough body. The operation composes a list codec Pumpkin
    // never invokes -- nothing serialises yet -- so composition survives and the first
    // actual encode/decode throws with the interface's name.

    static <B extends ByteBuf, V> StreamCodec.CodecOperation<B, V, List<V>> list(int maxSize) {
        return original -> dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin
    // never invokes -- nothing serialises yet -- so composition survives and the first
    // actual encode/decode throws with the interface's name.
    static <B extends ByteBuf, K, V, M extends Map<K, V>> StreamCodec<B, M> map(IntFunction<? extends M> constructor, StreamCodec<? super B, K> keyCodec, StreamCodec<? super B, V> valueCodec, int maxSize) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin
    // never invokes -- nothing serialises yet -- so composition survives and the first
    // actual encode/decode throws with the interface's name.
    private static <T, R> StreamCodec<RegistryFriendlyByteBuf, R> registry(ResourceKey<? extends Registry<T>> registryKey, Function<Registry<T>, IdMap<R>> mapExtractor) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin
    // never invokes -- nothing serialises yet -- so composition survives and the first
    // actual encode/decode throws with the interface's name.
    static <T> StreamCodec<RegistryFriendlyByteBuf, T> registry(ResourceKey<? extends Registry<T>> registryKey) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    }
}
