package net.minecraft.network.chat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapEncoder;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class ComponentSerialization {

    public static final Codec<Component> CODEC = null;

    public static final StreamCodec<RegistryFriendlyByteBuf, Component> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public static final StreamCodec<RegistryFriendlyByteBuf, Component> TRUSTED_STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    private static class FuzzyCodec<T> extends MapCodec<T> {

        public FuzzyCodec(Collection<MapCodec<? extends T>> codecs, Function<T, ? extends MapEncoder<? extends T>> encoderGetter) {
        }

        public <S> DataResult<T> decode(DynamicOps<S> ops, MapLike<S> input) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization$FuzzyCodec.decode:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/MapLike;)Lcom/mojang/serialization/DataResult;");
        }

        public <S> RecordBuilder<S> encode(T input, DynamicOps<S> ops, RecordBuilder<S> prefix) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization$FuzzyCodec.encode:(Ljava/lang/Object;Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/RecordBuilder;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public <S> Stream<S> keys(DynamicOps<S> ops) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization$FuzzyCodec.keys:(Lcom/mojang/serialization/DynamicOps;)Ljava/util/stream/Stream;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization$FuzzyCodec.toString:()Ljava/lang/String;");
        }

        protected FuzzyCodec() {
        }
    }

    private static class StrictEither<T> extends MapCodec<T> {

        public StrictEither(String typeFieldName, MapCodec<T> typed, MapCodec<T> fuzzy) {
        }

        public <O> DataResult<T> decode(DynamicOps<O> ops, MapLike<O> input) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization$StrictEither.decode:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/MapLike;)Lcom/mojang/serialization/DataResult;");
        }

        public <O> RecordBuilder<O> encode(T input, DynamicOps<O> ops, RecordBuilder<O> prefix) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization$StrictEither.encode:(Ljava/lang/Object;Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/RecordBuilder;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public <T1> Stream<T1> keys(DynamicOps<T1> ops) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization$StrictEither.keys:(Lcom/mojang/serialization/DynamicOps;)Ljava/util/stream/Stream;");
        }

        protected StrictEither() {
        }
    }

    public ComponentSerialization() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization");
        }
    }
}
