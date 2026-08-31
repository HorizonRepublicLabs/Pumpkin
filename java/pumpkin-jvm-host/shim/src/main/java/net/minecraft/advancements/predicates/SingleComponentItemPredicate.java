package net.minecraft.advancements.predicates;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.predicates.DataComponentPredicate;
import dev.pumpkin.shim.Unimplemented;

public interface SingleComponentItemPredicate<T> extends DataComponentPredicate {

    default boolean matches(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/SingleComponentItemPredicate.matches:(Lnet/minecraft/core/component/DataComponentGetter;)Z");
    }

    DataComponentType<T> componentType();

    boolean matches(T value);
}
