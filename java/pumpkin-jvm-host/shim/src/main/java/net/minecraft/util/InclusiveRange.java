package net.minecraft.util;

import com.mojang.serialization.DataResult;
import java.util.function.Function;
import dev.pumpkin.shim.Unimplemented;

public record InclusiveRange<T extends Comparable<T>>(T minInclusive, T maxInclusive) {

    public InclusiveRange(T value) {
        this((T) null, (T) null);
        throw Unimplemented.forMember("net/minecraft/util/InclusiveRange.<init>:(Ljava/lang/Comparable;)V");
    }

    public static <T extends Comparable<T>> DataResult<InclusiveRange<T>> create(T minInclusive, T maxInclusive) {
        throw Unimplemented.forMember("net/minecraft/util/InclusiveRange.create:(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lcom/mojang/serialization/DataResult;");
    }

    public <S extends Comparable<S>> InclusiveRange<S> map(Function<? super T, ? extends S> mapper) {
        throw Unimplemented.forMember("net/minecraft/util/InclusiveRange.map:(Ljava/util/function/Function;)Lnet/minecraft/util/InclusiveRange;");
    }

    public boolean contains(InclusiveRange<T> subRange) {
        throw Unimplemented.forMember("net/minecraft/util/InclusiveRange.contains:(Lnet/minecraft/util/InclusiveRange;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/InclusiveRange.toString:()Ljava/lang/String;");
    }
}
