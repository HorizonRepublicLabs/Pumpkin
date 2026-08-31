package net.minecraft.util;

import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import dev.pumpkin.shim.Unimplemented;

public class ByIdMap {

    public static <T> IntFunction<T> continuous(ToIntFunction<T> idGetter, T[] values, ByIdMap.OutOfBoundsStrategy strategy) {
        throw Unimplemented.forMember("net/minecraft/util/ByIdMap.continuous:(Ljava/util/function/ToIntFunction;[Ljava/lang/Object;Lnet/minecraft/util/ByIdMap$OutOfBoundsStrategy;)Ljava/util/function/IntFunction;");
    }

    public enum OutOfBoundsStrategy {

        ZERO, WRAP, CLAMP
    }

    public ByIdMap() {
    }
}
