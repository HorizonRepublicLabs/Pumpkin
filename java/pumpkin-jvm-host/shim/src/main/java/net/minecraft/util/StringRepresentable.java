package net.minecraft.util;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import dev.pumpkin.shim.Unimplemented;

public interface StringRepresentable {

    String getSerializedName();

    // Pumpkin divergence: real-enough body. The codec carries serialisation logic Pumpkin
    // never invokes -- decode/encode still throw with their own member keys -- so building
    // one at class-initialisation survives.
    static <E extends Enum<E> & StringRepresentable> StringRepresentable.EnumCodec<E> fromEnum(Supplier<E[]> values) {
        return new StringRepresentable.EnumCodec<>();
    }

    static Keyable keys(StringRepresentable[] values) {
        throw Unimplemented.forMember("net/minecraft/util/StringRepresentable.keys:([Lnet/minecraft/util/StringRepresentable;)Lcom/mojang/serialization/Keyable;");
    }

    class EnumCodec<E extends Enum<E> & StringRepresentable> extends StringRepresentable.StringRepresentableCodec<E> {

        public EnumCodec(E[] valueArray, Function<String, E> nameResolver) {
        }

        protected EnumCodec() {
        }
    }

    class StringRepresentableCodec<S extends StringRepresentable> implements Codec<S> {

        public StringRepresentableCodec(S[] valueArray, Function<String, S> nameResolver, ToIntFunction<S> idResolver) {
        }

        public <T> DataResult<Pair<S, T>> decode(DynamicOps<T> ops, T input) {
            throw Unimplemented.forMember("net/minecraft/util/StringRepresentable$StringRepresentableCodec.decode:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
        }

        public <T> DataResult<T> encode(S input, DynamicOps<T> ops, T prefix) {
            throw Unimplemented.forMember("net/minecraft/util/StringRepresentable$StringRepresentableCodec.encode:(Lnet/minecraft/util/StringRepresentable;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
        }

        protected StringRepresentableCodec() {
        }
    }
}
