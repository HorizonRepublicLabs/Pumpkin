package net.neoforged.neoforge.common.util;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
import java.util.stream.Stream;
import dev.pumpkin.shim.Unimplemented;

public class NeoForgeExtraCodecs {

    public static <T> MapCodec<T> aliasedFieldOf(final Codec<T> codec, final String... names) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs.aliasedFieldOf:(Lcom/mojang/serialization/Codec;[Ljava/lang/String;)Lcom/mojang/serialization/MapCodec;");
    }

    public static <T> MapCodec<T> optionalFieldAlwaysWrite(Codec<T> codec, String name, T defaultValue) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs.optionalFieldAlwaysWrite:(Lcom/mojang/serialization/Codec;Ljava/lang/String;Ljava/lang/Object;)Lcom/mojang/serialization/MapCodec;");
    }

    public static <T> Codec<T> withAlternative(final Codec<T> codec, final Codec<T> alternative) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs.withAlternative:(Lcom/mojang/serialization/Codec;Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");
    }

    public static <T> MapCodec<T> withAlternative(final MapCodec<T> codec, final MapCodec<T> alternative) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs.withAlternative:(Lcom/mojang/serialization/MapCodec;Lcom/mojang/serialization/MapCodec;)Lcom/mojang/serialization/MapCodec;");
    }

    private record AlternativeCodec<T>(Codec<T> codec, Codec<T> alternative) implements Codec<T> {

        public <T1> DataResult<Pair<T, T1>> decode(final DynamicOps<T1> ops, final T1 input) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$AlternativeCodec.decode:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
        }

        public <T1> DataResult<T1> encode(final T input, final DynamicOps<T1> ops, final T1 prefix) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$AlternativeCodec.encode:(Ljava/lang/Object;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$AlternativeCodec.toString:()Ljava/lang/String;");
        }
    }

    private static class AlternativeMapCodec<T> extends MapCodec<T> {

        private AlternativeMapCodec(MapCodec<T> codec, MapCodec<T> alternative) {
        }

        public <T> Stream<T> keys(DynamicOps<T> ops) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$AlternativeMapCodec.keys:(Lcom/mojang/serialization/DynamicOps;)Ljava/util/stream/Stream;");
        }

        public <T1> DataResult<T> decode(DynamicOps<T1> ops, MapLike<T1> input) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$AlternativeMapCodec.decode:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/MapLike;)Lcom/mojang/serialization/DataResult;");
        }

        public <T1> RecordBuilder<T1> encode(T input, DynamicOps<T1> ops, RecordBuilder<T1> prefix) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$AlternativeMapCodec.encode:(Ljava/lang/Object;Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/RecordBuilder;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$AlternativeMapCodec.toString:()Ljava/lang/String;");
        }

        protected AlternativeMapCodec() {
        }
    }

    private static final class XorMapCodec<F, S> extends MapCodec<Either<F, S>> {

        private XorMapCodec(MapCodec<F> first, MapCodec<S> second) {
        }

        public <T> Stream<T> keys(DynamicOps<T> ops) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$XorMapCodec.keys:(Lcom/mojang/serialization/DynamicOps;)Ljava/util/stream/Stream;");
        }

        public <T> DataResult<Either<F, S>> decode(DynamicOps<T> ops, MapLike<T> input) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$XorMapCodec.decode:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/MapLike;)Lcom/mojang/serialization/DataResult;");
        }

        public <T> RecordBuilder<T> encode(Either<F, S> input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$XorMapCodec.encode:(Lcom/mojang/datafixers/util/Either;Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/RecordBuilder;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/NeoForgeExtraCodecs$XorMapCodec.toString:()Ljava/lang/String;");
        }

        protected XorMapCodec() {
        }
    }

    public NeoForgeExtraCodecs() {
    }
}
