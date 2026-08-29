package net.neoforged.neoforge.common;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import dev.pumpkin.shim.Unimplemented;

public interface MutableDataComponentHolder extends DataComponentHolder {

    <T> T set(DataComponentType<T> componentType, T value);

    default <T> T set(Supplier<? extends DataComponentType<T>> componentType, T value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/MutableDataComponentHolder.set:(Ljava/util/function/Supplier;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    default <T, U> T update(DataComponentType<T> componentType, T value, U updateContext, BiFunction<T, U, T> updater) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/MutableDataComponentHolder.update:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;");
    }

    default <T, U> T update(Supplier<? extends DataComponentType<T>> componentType, T value, U updateContext, BiFunction<T, U, T> updater) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/MutableDataComponentHolder.update:(Ljava/util/function/Supplier;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;");
    }

    default <T> T update(DataComponentType<T> componentType, T value, UnaryOperator<T> updater) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/MutableDataComponentHolder.update:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;Ljava/util/function/UnaryOperator;)Ljava/lang/Object;");
    }

    default <T> T update(Supplier<? extends DataComponentType<T>> componentType, T value, UnaryOperator<T> updater) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/MutableDataComponentHolder.update:(Ljava/util/function/Supplier;Ljava/lang/Object;Ljava/util/function/UnaryOperator;)Ljava/lang/Object;");
    }

    <T> T remove(DataComponentType<? extends T> componentType);

    default <T> T remove(Supplier<? extends DataComponentType<? extends T>> componentType) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/MutableDataComponentHolder.remove:(Ljava/util/function/Supplier;)Ljava/lang/Object;");
    }

    void applyComponents(DataComponentPatch patch);

    void applyComponents(DataComponentMap components);
}
