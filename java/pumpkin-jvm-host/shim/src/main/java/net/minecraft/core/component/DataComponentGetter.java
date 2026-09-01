package net.minecraft.core.component;

import dev.pumpkin.shim.Unimplemented;

public interface DataComponentGetter {

    <T> T get(DataComponentType<? extends T> type);

    // Pumpkin divergence: vanilla derivations from the one abstract get.
    default <T> T getOrDefault(DataComponentType<? extends T> type, T defaultValue) {
        T value = get(type);
        return value == null ? defaultValue : value;
    }

    default <T> T get(java.util.function.Supplier<? extends DataComponentType<? extends T>> componentType) {
        return get(componentType.get());
    }

    default <T> T getOrDefault(java.util.function.Supplier<? extends DataComponentType<? extends T>> componentType, T value) {
        return getOrDefault(componentType.get(), value);
    }

    default <T> boolean has(java.util.function.Supplier<? extends DataComponentType<? extends T>> componentType) {
        return has(componentType.get());
    }

    default boolean has(DataComponentType<?> componentType) {
        return get(componentType) != null;
    }
}
