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

    // Pumpkin divergence: real body -- an enum codec is name in, constant out, and
    // datapack registries decode through it for real.
    static <E extends Enum<E> & StringRepresentable> StringRepresentable.EnumCodec<E> fromEnum(Supplier<E[]> values) {
        return new StringRepresentable.EnumCodec<>(values.get(), name -> {
            for (E value : values.get()) {
                if (value.getSerializedName().equals(name)) {
                    return value;
                }
            }
            return null;
        });
    }

    static Keyable keys(StringRepresentable[] values) {
        throw Unimplemented.forMember("net/minecraft/util/StringRepresentable.keys:([Lnet/minecraft/util/StringRepresentable;)Lcom/mojang/serialization/Keyable;");
    }

    class EnumCodec<E extends Enum<E> & StringRepresentable> extends StringRepresentable.StringRepresentableCodec<E> {

        public EnumCodec(E[] valueArray, Function<String, E> nameResolver) {
            super(valueArray, nameResolver, value -> value.ordinal());
        }

        protected EnumCodec() {
        }
    }

    // Pumpkin divergence: real bodies -- serialized-name round trip; unknown names
    // are a decode error, exactly vanilla's contract.
    class StringRepresentableCodec<S extends StringRepresentable> implements Codec<S> {

        private final S[] pumpkinValues;
        private final Function<String, S> pumpkinByName;

        public StringRepresentableCodec(S[] valueArray, Function<String, S> nameResolver, ToIntFunction<S> idResolver) {
            this.pumpkinValues = valueArray;
            this.pumpkinByName = nameResolver;
        }

        public <T> DataResult<Pair<S, T>> decode(DynamicOps<T> ops, T input) {
            if (pumpkinByName == null) {
                throw Unimplemented.forMember("net/minecraft/util/StringRepresentable$StringRepresentableCodec.decode:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
            }
            return ops.getStringValue(input).flatMap(name -> {
                S value = pumpkinByName.apply(name);
                return value == null
                        ? DataResult.error(() -> "Unknown serialized name: " + name)
                        : DataResult.success(Pair.of(value, ops.empty()));
            });
        }

        public <T> DataResult<T> encode(S input, DynamicOps<T> ops, T prefix) {
            return DataResult.success(ops.createString(input.getSerializedName()));
        }

        protected StringRepresentableCodec() {
            this.pumpkinValues = null;
            this.pumpkinByName = null;
        }
    }
}
