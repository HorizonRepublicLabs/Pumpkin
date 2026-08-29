package net.minecraft.core.component;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class DataComponentLookup<T> {

    public DataComponentLookup(Iterable<? extends Holder<T>> elements) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentLookup.<init>:(Ljava/lang/Iterable;)V");
    }

    private record ComponentStorage<C, T>(Multimap<C, Holder<T>> valueToComponent) {
    }

    protected DataComponentLookup() {
    }
}
