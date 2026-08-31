package net.minecraft.core.component;

import java.util.List;
import java.util.function.Predicate;
import dev.pumpkin.shim.Unimplemented;

public final class DataComponentExactPredicate implements Predicate<DataComponentGetter> {

    private DataComponentExactPredicate(List<TypedDataComponent<?>> expectedComponents) {
    }

    public static DataComponentExactPredicate.Builder builder() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate.builder:()Lnet/minecraft/core/component/DataComponentExactPredicate$Builder;");
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

    public boolean alwaysMatches() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate.alwaysMatches:()Z");
    }

    public DataComponentPatch asPatch() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate.asPatch:()Lnet/minecraft/core/component/DataComponentPatch;");
    }

    public static class Builder {

        protected Builder() {
        }

        public <T> DataComponentExactPredicate.Builder expect(TypedDataComponent<T> value) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate$Builder.expect:(Lnet/minecraft/core/component/TypedDataComponent;)Lnet/minecraft/core/component/DataComponentExactPredicate$Builder;");
        }

        public <T> DataComponentExactPredicate.Builder expect(DataComponentType<? super T> type, T value) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate$Builder.expect:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentExactPredicate$Builder;");
        }

        public DataComponentExactPredicate build() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentExactPredicate$Builder.build:()Lnet/minecraft/core/component/DataComponentExactPredicate;");
        }
    }

    public DataComponentExactPredicate() {
    }
}
