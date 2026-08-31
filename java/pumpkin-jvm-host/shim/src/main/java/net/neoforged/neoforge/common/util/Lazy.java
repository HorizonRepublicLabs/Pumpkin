package net.neoforged.neoforge.common.util;

import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public final class Lazy<T> implements Supplier<T> {

    // Pumpkin divergence: the real thing -- memoize on first get.
    private Supplier<T> pumpkinSupplier;

    private T pumpkinValue;

    private boolean pumpkinResolved;

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        Lazy<T> lazy = new Lazy<>();
        lazy.pumpkinSupplier = supplier;
        return lazy;
    }

    private Lazy(Supplier<T> delegate) {
    }

    public T get() {
        if (!pumpkinResolved) {
            pumpkinValue = pumpkinSupplier.get();
            pumpkinResolved = true;
        }
        return pumpkinValue;
    }

    public Lazy() {
    }
}
