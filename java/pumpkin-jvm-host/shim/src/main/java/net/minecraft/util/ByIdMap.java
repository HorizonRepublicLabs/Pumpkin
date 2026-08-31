package net.minecraft.util;

import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import dev.pumpkin.shim.Unimplemented;

public class ByIdMap {

    // Pumpkin divergence: vanilla logic, helpers inlined -- sort the values by their
    // declared ids, then answer lookups per the out-of-bounds strategy.
    public static <T> IntFunction<T> continuous(ToIntFunction<T> idGetter, T[] values, ByIdMap.OutOfBoundsStrategy strategy) {
        T[] sorted = values.clone();
        for (T value : values) {
            int id = idGetter.applyAsInt(value);
            if (id < 0 || id >= sorted.length) {
                throw new IllegalArgumentException("id " + id + " out of a continuous range of " + sorted.length);
            }
            sorted[id] = value;
        }
        final int length = sorted.length;
        return switch (strategy) {
            case ZERO -> id -> id >= 0 && id < length ? sorted[id] : sorted[0];
            case WRAP -> id -> sorted[((id % length) + length) % length];
            case CLAMP -> id -> sorted[Math.clamp(id, 0, length - 1)];
        };
    }

    public enum OutOfBoundsStrategy {

        ZERO, WRAP, CLAMP
    }

    public ByIdMap() {
    }
}
