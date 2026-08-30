package net.minecraft.util;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.codecs.BaseMapCodec;
import java.util.Map;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ExtraCodecs {

    public static <K, V> ExtraCodecs.StrictUnboundedMapCodec<K, V> strictUnboundedMap(Codec<K> keyCodec, Codec<V> elementCodec) {
        throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs.strictUnboundedMap:(Lcom/mojang/serialization/Codec;Lcom/mojang/serialization/Codec;)Lnet/minecraft/util/ExtraCodecs$StrictUnboundedMapCodec;");
    }

    // Pumpkin divergence: real-enough body -- inert codec; encode/decode throw the key.
    public static <A> Codec<Optional<A>> optionalEmptyMap(Codec<A> codec) {
        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.optionalEmptyMap:(Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");
    }

    public static class LateBoundIdMapper<I, V> {

        public V getValue(I id) {
            throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs$LateBoundIdMapper.getValue:(Ljava/lang/Object;)Ljava/lang/Object;");
        }

        public LateBoundIdMapper() {
        }
    }

    public record StrictUnboundedMapCodec<K, V>(Codec<K> keyCodec, Codec<V> elementCodec) implements BaseMapCodec<K, V>, Codec<Map<K, V>> {

        public <T> DataResult<Map<K, V>> decode(DynamicOps<T> ops, MapLike<T> input) {
            throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs$StrictUnboundedMapCodec.decode:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/MapLike;)Lcom/mojang/serialization/DataResult;");
        }

        public <T> DataResult<Pair<Map<K, V>, T>> decode(DynamicOps<T> ops, T input) {
            throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs$StrictUnboundedMapCodec.decode:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
        }

        public <T> DataResult<T> encode(Map<K, V> input, DynamicOps<T> ops, T prefix) {
            throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs$StrictUnboundedMapCodec.encode:(Ljava/util/Map;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs$StrictUnboundedMapCodec.toString:()Ljava/lang/String;");
        }
    }

    public record TagOrElementLocation(Identifier id, boolean tag) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs$TagOrElementLocation.toString:()Ljava/lang/String;");
        }
    }

    public ExtraCodecs() {
    }
}
