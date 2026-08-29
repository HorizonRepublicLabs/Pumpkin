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

    static <E extends Enum<E> & StringRepresentable> StringRepresentable.EnumCodec<E> fromEnum(Supplier<E[]> values) {
        throw Unimplemented.forMember("net/minecraft/util/StringRepresentable.fromEnum:(Ljava/util/function/Supplier;)Lnet/minecraft/util/StringRepresentable$EnumCodec;");
    }

    static Keyable keys(StringRepresentable[] values) {
        throw Unimplemented.forMember("net/minecraft/util/StringRepresentable.keys:([Lnet/minecraft/util/StringRepresentable;)Lcom/mojang/serialization/Keyable;");
    }

    class EnumCodec<E extends Enum<E> & StringRepresentable> extends StringRepresentable.StringRepresentableCodec<E> {

        public EnumCodec(E[] valueArray, Function<String, E> nameResolver) {
            throw Unimplemented.forMember("net/minecraft/util/StringRepresentable$EnumCodec.<init>:([Ljava/lang/Enum;Ljava/util/function/Function;)V");
        }

        protected EnumCodec() {
        }
    }

    class StringRepresentableCodec<S extends StringRepresentable> implements Codec<S> {

        public StringRepresentableCodec(S[] valueArray, Function<String, S> nameResolver, ToIntFunction<S> idResolver) {
            throw Unimplemented.forMember("net/minecraft/util/StringRepresentable$StringRepresentableCodec.<init>:([Lnet/minecraft/util/StringRepresentable;Ljava/util/function/Function;Ljava/util/function/ToIntFunction;)V");
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
