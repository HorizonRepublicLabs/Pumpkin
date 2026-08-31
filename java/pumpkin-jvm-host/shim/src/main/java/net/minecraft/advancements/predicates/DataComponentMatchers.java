package net.minecraft.advancements.predicates;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.predicates.DataComponentPredicate;
import dev.pumpkin.shim.Unimplemented;

public record DataComponentMatchers(DataComponentExactPredicate exact, Map<DataComponentPredicate.Type<?>, DataComponentPredicate> partial) implements Predicate<DataComponentGetter> {

    public boolean test(DataComponentGetter values) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/DataComponentMatchers.test:(Lnet/minecraft/core/component/DataComponentGetter;)Z");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/DataComponentMatchers.isEmpty:()Z");
    }

    public static class Builder {

        private final ImmutableMap.Builder<DataComponentPredicate.Type<?>, DataComponentPredicate> partial = null;

        protected Builder() {
        }

        public static DataComponentMatchers.Builder components() {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/DataComponentMatchers$Builder.components:()Lnet/minecraft/advancements/predicates/DataComponentMatchers$Builder;");
        }

        public <T extends DataComponentPredicate> DataComponentMatchers.Builder partial(DataComponentPredicate.Type<T> type, T predicate) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/DataComponentMatchers$Builder.partial:(Lnet/minecraft/core/component/predicates/DataComponentPredicate$Type;Lnet/minecraft/core/component/predicates/DataComponentPredicate;)Lnet/minecraft/advancements/predicates/DataComponentMatchers$Builder;");
        }

        public DataComponentMatchers build() {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/DataComponentMatchers$Builder.build:()Lnet/minecraft/advancements/predicates/DataComponentMatchers;");
        }
    }
}
