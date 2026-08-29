package net.minecraft.core.component;

import dev.pumpkin.shim.Unimplemented;

public interface DataComponentGetter {

    <T> T get(DataComponentType<? extends T> type);

    default <T> T getOrDefault(DataComponentType<? extends T> type, T defaultValue) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentGetter.getOrDefault:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    default <T> T get(java.util.function.Supplier<? extends DataComponentType<? extends T>> componentType) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentGetter.get:(Ljava/util/function/Supplier;)Ljava/lang/Object;");
    }

    default <T> T getOrDefault(java.util.function.Supplier<? extends DataComponentType<? extends T>> componentType, T value) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentGetter.getOrDefault:(Ljava/util/function/Supplier;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    default <T> boolean has(java.util.function.Supplier<? extends DataComponentType<? extends T>> componentType) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentGetter.has:(Ljava/util/function/Supplier;)Z");
    }

    default boolean has(DataComponentType<?> componentType) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentGetter.has:(Lnet/minecraft/core/component/DataComponentType;)Z");
    }
}
