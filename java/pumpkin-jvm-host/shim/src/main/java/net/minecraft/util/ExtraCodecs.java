package net.minecraft.util;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.codecs.BaseMapCodec;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ExtraCodecs {

    // Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.
    public static final Codec<Integer> RGB_COLOR_CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.RGB_COLOR_CODEC");

    // Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.
    public static final Codec<Integer> ARGB_COLOR_CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.ARGB_COLOR_CODEC");

    // Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.
    // Pumpkin divergence: real body -- "#AARRGGBB" (or "#RRGGBB", alpha assumed
    // opaque) to a packed ARGB int, vanilla's format.
    public static final Codec<Integer> STRING_ARGB_COLOR =
            Codec.STRING.comapFlatMap(text -> {
                try {
                    if (!text.startsWith("#")) {
                        return com.mojang.serialization.DataResult.error(
                                () -> "Not a color: " + text);
                    }
                    long parsed = Long.parseLong(text.substring(1), 16);
                    if (text.length() == 7) {
                        parsed |= 0xFF000000L;
                    }
                    return com.mojang.serialization.DataResult.success((int) parsed);
                } catch (NumberFormatException e) {
                    return com.mojang.serialization.DataResult.error(
                            () -> "Not a color: " + text);
                }
            }, color -> String.format("#%08X", color));

    // Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.
    // Pumpkin divergence: real bodies -- vanilla's range checks over the int codec,
    // the same shape as intRange below.
    public static final Codec<Integer> NON_NEGATIVE_INT = intRangeWithMessage(
            0, Integer.MAX_VALUE, value -> "Value must be non-negative: " + value);

    // Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.
    public static final Codec<Integer> POSITIVE_INT = intRangeWithMessage(
            1, Integer.MAX_VALUE, value -> "Value must be positive: " + value);

    private static Codec<Integer> intRangeWithMessage(int minInclusive, int maxInclusive,
            java.util.function.Function<Integer, String> message) {
        return Codec.INT.validate(value -> value >= minInclusive && value <= maxInclusive
                ? com.mojang.serialization.DataResult.success(value)
                : com.mojang.serialization.DataResult.error(() -> message.apply(value)));
    }

    // Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.
    public static final Codec<Long> NON_NEGATIVE_LONG =
            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.NON_NEGATIVE_LONG");

    // Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.
    public static final Codec<String> NON_EMPTY_STRING =
            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.NON_EMPTY_STRING");

    public static <K, V> ExtraCodecs.StrictUnboundedMapCodec<K, V> strictUnboundedMap(Codec<K> keyCodec, Codec<V> elementCodec) {
        throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs.strictUnboundedMap:(Lcom/mojang/serialization/Codec;Lcom/mojang/serialization/Codec;)Lnet/minecraft/util/ExtraCodecs$StrictUnboundedMapCodec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first use.
    // Pumpkin divergence: real body -- vanilla's bounds check over the int codec.
    public static Codec<Integer> intRange(int minInclusive, int maxInclusive) {
        return Codec.INT.validate(value -> value >= minInclusive && value <= maxInclusive
                ? com.mojang.serialization.DataResult.success(value)
                : com.mojang.serialization.DataResult.error(() -> "Value " + value
                        + " outside of range [" + minInclusive + ":" + maxInclusive + "]"));
    }

    // Pumpkin divergence: real body -- vanilla's own emptiness check over the list.
    public static <T> Codec<List<T>> nonEmptyList(Codec<List<T>> listCodec) {
        return listCodec.validate(list -> list.isEmpty()
                ? com.mojang.serialization.DataResult.error(() -> "List must have contents")
                : com.mojang.serialization.DataResult.success(list));
    }

    // Pumpkin divergence: inert codec -- throws its key on first use.
    public static <T> Codec<HolderSet<T>> nonEmptyHolderSet(Codec<HolderSet<T>> listCodec) {
        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.nonEmptyHolderSet:(Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");
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

    // Pumpkin divergence: no throwing initializer -- every field answers inertly.
}
