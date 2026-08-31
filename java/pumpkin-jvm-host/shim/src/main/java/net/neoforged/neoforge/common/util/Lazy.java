package net.neoforged.neoforge.common.util;

import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public final class Lazy<T> implements Supplier<T> {

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/Lazy.of:(Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/common/util/Lazy;");
    }

    private Lazy(Supplier<T> delegate) {
    }

    public T get() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/Lazy.get:()Ljava/lang/Object;");
    }

    public Lazy() {
    }
}
