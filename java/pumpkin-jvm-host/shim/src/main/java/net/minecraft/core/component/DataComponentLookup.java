package net.minecraft.core.component;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;

public class DataComponentLookup<T> {

    public DataComponentLookup(Iterable<? extends Holder<T>> elements) {
    }

    private record ComponentStorage<C, T>(Multimap<C, Holder<T>> valueToComponent) {
    }

    public DataComponentLookup() {
    }
}
