package net.minecraft.core.component;

import java.util.List;
import java.util.function.Predicate;
import dev.pumpkin.shim.Unimplemented;

public final class DataComponentExactPredicate implements Predicate<DataComponentGetter> {

    private DataComponentExactPredicate(List<TypedDataComponent<?>> expectedComponents) {
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate.isEmpty:()Z");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate.toString:()Ljava/lang/String;");
    }

    public boolean test(DataComponentGetter actualComponents) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate.test:(Lnet/minecraft/core/component/DataComponentGetter;)Z");
    }

    public static class Builder {

        protected Builder() {
        }

        public DataComponentExactPredicate build() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate$Builder.build:()Lnet/minecraft/core/component/DataComponentExactPredicate;");
        }
    }

    public DataComponentExactPredicate() {
    }
}
