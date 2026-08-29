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

    static <B extends ByteBuf, V> StreamCodec.CodecOperation<B, V, List<V>> list() {
        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.list:()Lnet/minecraft/network/codec/StreamCodec$CodecOperation;");
    }

    static <B extends ByteBuf, V> StreamCodec.CodecOperation<B, V, List<V>> list(int maxSize) {
        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.list:(I)Lnet/minecraft/network/codec/StreamCodec$CodecOperation;");
    }

    static <B extends ByteBuf, K, V, M extends Map<K, V>> StreamCodec<B, M> map(IntFunction<? extends M> constructor, StreamCodec<? super B, K> keyCodec, StreamCodec<? super B, V> valueCodec, int maxSize) {
        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.map:(Ljava/util/function/IntFunction;Lnet/minecraft/network/codec/StreamCodec;Lnet/minecraft/network/codec/StreamCodec;I)Lnet/minecraft/network/codec/StreamCodec;");
    }

    private static <T, R> StreamCodec<RegistryFriendlyByteBuf, R> registry(ResourceKey<? extends Registry<T>> registryKey, Function<Registry<T>, IdMap<R>> mapExtractor) {
        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.registry:(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Function;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    static <T> StreamCodec<RegistryFriendlyByteBuf, T> registry(ResourceKey<? extends Registry<T>> registryKey) {
        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.registry:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");
    }
}
