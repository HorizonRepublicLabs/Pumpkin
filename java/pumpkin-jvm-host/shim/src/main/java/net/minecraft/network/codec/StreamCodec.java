package net.minecraft.network.codec;

import com.mojang.datafixers.util.Function10;
import com.mojang.datafixers.util.Function11;
import com.mojang.datafixers.util.Function12;
import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Function4;
import com.mojang.datafixers.util.Function5;
import com.mojang.datafixers.util.Function6;
import com.mojang.datafixers.util.Function7;
import com.mojang.datafixers.util.Function8;
import com.mojang.datafixers.util.Function9;
import java.util.function.BiFunction;
import java.util.function.Function;
import dev.pumpkin.shim.Unimplemented;

public interface StreamCodec<B, V> extends StreamEncoder<B, V>, StreamDecoder<B, V> {

    // Pumpkin divergence: real body, copied from vanilla. Pure delegation over two shim
    // interfaces -- the ARGB rule. Static interface methods cannot ride the proxy's
    // default-method path, so this needs its own body.
    static <B, V> StreamCodec<B, V> of(StreamEncoder<B, V> encoder, StreamDecoder<B, V> decoder) {
        return new StreamCodec<B, V>() {
            @Override
            public V decode(B input) {
                return decoder.decode(input);
            }

            @Override
            public void encode(B output, V value) {
                encoder.encode(output, value);
            }
        };
    }

    // Pumpkin divergence: real body, mirroring of() -- the member-encoder spelling.
    static <B, V> StreamCodec<B, V> ofMember(StreamMemberEncoder<B, V> encoder, StreamDecoder<B, V> decoder) {
        return new StreamCodec<B, V>() {
            @Override
            public V decode(B input) {
                return decoder.decode(input);
            }

            @Override
            public void encode(B output, V value) {
                encoder.encode(value, output);
            }
        };
    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, V> StreamCodec<B, V> unit(V instance) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }
    // Pumpkin divergence: vanilla body verbatim -- pure composition, no game state.
    default <O> StreamCodec<B, O> apply(StreamCodec.CodecOperation<B, V, O> operation) {
        return operation.apply(this);
    }

    // Pumpkin divergence: real composition, as vanilla -- inertness propagates from
    // the source codec, so a mapped inert codec still throws its origin's key on use.
    default <O> StreamCodec<B, O> map(Function<? super V, ? extends O> to, Function<? super O, ? extends V> from) {
        StreamCodec<B, V> self = this;
        return new StreamCodec<B, O>() {
            @Override
            public O decode(B input) {
                return to.apply(self.decode(input));
            }

            @Override
            public void encode(B output, O value) {
                self.encode(output, from.apply(value));
            }
        };
    }

    default <U> StreamCodec<B, U> dispatch(Function<? super U, ? extends V> type, Function<? super V, ? extends StreamCodec<? super B, ? extends U>> codec) {
        // Pumpkin divergence: composes inert -- Pumpkin never encodes packets through
        // mod stream codecs, so the composed codec throws its member key on first use.
        return dev.pumpkin.shim.Stubs.of(StreamCodec.class,
            "net/minecraft/network/codec/StreamCodec.dispatch:(Ljava/util/function/Function;Ljava/util/function/Function;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, Function<T1, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, BiFunction<T1, T2, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, Function3<T1, T2, T3, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, Function4<T1, T2, T3, T4, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4, T5> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, Function5<T1, T2, T3, T4, T5, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4, T5, T6> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, Function6<T1, T2, T3, T4, T5, T6, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4, T5, T6, T7> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, Function7<T1, T2, T3, T4, T5, T6, T7, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, Function8<T1, T2, T3, T4, T5, T6, T7, T8, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, StreamCodec<? super B, T11> codec11, Function<C, T11> getter11, Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin

    // never invokes -- nothing serialises components yet -- so composition survives and

    // the first actual encode/decode throws with the interface's name.

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, StreamCodec<? super B, T11> codec11, Function<C, T11> getter11, StreamCodec<? super B, T12> codec12, Function<C, T12> getter12, Function12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, C> constructor) {

        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    }

    @SuppressWarnings("unchecked")
    default <S extends B> StreamCodec<S, V> cast() {
        return (StreamCodec<S, V>) this;
    }

    interface CodecOperation<B, S, T> {

        StreamCodec<B, T> apply(StreamCodec<B, S> original);
    }
}
