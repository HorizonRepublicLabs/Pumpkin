package net.minecraft.core.component.predicates;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import dev.pumpkin.shim.Unimplemented;

public record AnyValue(DataComponentType<?> type) implements DataComponentPredicate {

    public boolean matches(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/core/component/predicates/AnyValue.matches:(Lnet/minecraft/core/component/DataComponentGetter;)Z");
    }
}
