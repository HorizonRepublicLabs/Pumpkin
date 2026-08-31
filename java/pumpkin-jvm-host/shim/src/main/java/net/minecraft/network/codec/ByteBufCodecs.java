package net.minecraft.network.codec;

import com.mojang.datafixers.util.Either;
import io.netty.buffer.ByteBuf;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.IdMap;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface ByteBufCodecs {

    StreamCodec<ByteBuf, Boolean> BOOL = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Byte> BYTE = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Short> SHORT = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Integer> INT = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Integer> VAR_INT = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Long> VAR_LONG = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Float> FLOAT = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Double> DOUBLE = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, byte[]> BYTE_ARRAY = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, String> STRING_UTF8 = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, CompoundTag> TRUSTED_COMPOUND_TAG = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    StreamCodec<ByteBuf, Vector3fc> VECTOR3F = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static StreamCodec<ByteBuf, byte[]> byteArray(int maxSize) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.byteArray:(I)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static StreamCodec<ByteBuf, String> stringUtf8(int maxStringLength) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.stringUtf8:(I)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <B extends ByteBuf, V> StreamCodec<B, Optional<V>> optional(StreamCodec<? super B, V> original) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.optional:(Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <B extends ByteBuf, V, C extends Collection<V>> StreamCodec<B, C> collection(IntFunction<C> constructor, StreamCodec<? super B, V> elementCodec) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.collection:(Ljava/util/function/IntFunction;Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <B extends ByteBuf, V, C extends Collection<V>> StreamCodec<B, C> collection(IntFunction<C> constructor, StreamCodec<? super B, V> elementCodec, int maxSize) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.collection:(Ljava/util/function/IntFunction;Lnet/minecraft/network/codec/StreamCodec;I)Lnet/minecraft/network/codec/StreamCodec;");
    }

    static <B extends ByteBuf, V, C extends Collection<V>> StreamCodec.CodecOperation<B, V, C> collection(IntFunction<C> constructor) {
        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.collection:(Ljava/util/function/IntFunction;)Lnet/minecraft/network/codec/StreamCodec$CodecOperation;");
    }

    static <B extends ByteBuf, V, C extends Collection<V>> StreamCodec.CodecOperation<B, V, C> collection(IntFunction<C> constructor, int maxSize) {
        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.collection:(Ljava/util/function/IntFunction;I)Lnet/minecraft/network/codec/StreamCodec$CodecOperation;");
    }
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

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <B extends ByteBuf, K, V, M extends Map<K, V>> StreamCodec<B, M> map(IntFunction<? extends M> constructor, StreamCodec<? super B, K> keyCodec, StreamCodec<? super B, V> valueCodec) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.map:(Ljava/util/function/IntFunction;Lnet/minecraft/network/codec/StreamCodec;Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin
    // never invokes -- nothing serialises yet -- so composition survives and the first
    // actual encode/decode throws with the interface's name.
    static <B extends ByteBuf, K, V, M extends Map<K, V>> StreamCodec<B, M> map(IntFunction<? extends M> constructor, StreamCodec<? super B, K> keyCodec, StreamCodec<? super B, V> valueCodec, int maxSize) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <B extends ByteBuf, L, R> StreamCodec<B, Either<L, R>> either(StreamCodec<? super B, L> leftCodec, StreamCodec<? super B, R> rightCodec) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.either:(Lnet/minecraft/network/codec/StreamCodec;Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <T> StreamCodec<ByteBuf, T> idMapper(IntFunction<T> byId, ToIntFunction<T> toId) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.idMapper:(Ljava/util/function/IntFunction;Ljava/util/function/ToIntFunction;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <T> StreamCodec<ByteBuf, T> idMapper(IdMap<T> mapper) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.idMapper:(Lnet/minecraft/core/IdMap;)Lnet/minecraft/network/codec/StreamCodec;");
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

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <T> StreamCodec<RegistryFriendlyByteBuf, Holder<T>> holderRegistry(ResourceKey<? extends Registry<T>> registryKey) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.holderRegistry:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    static <T> StreamCodec<RegistryFriendlyByteBuf, HolderSet<T>> holderSet(ResourceKey<? extends Registry<T>> registryKey) {
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/ByteBufCodecs.holderSet:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");
    }
}
