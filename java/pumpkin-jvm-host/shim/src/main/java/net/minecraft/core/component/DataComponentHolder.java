package net.minecraft.core.component;

import net.neoforged.neoforge.common.extensions.IDataComponentHolderExtension;
import dev.pumpkin.shim.Unimplemented;

public interface DataComponentHolder extends DataComponentGetter, IDataComponentHolderExtension {

    DataComponentMap getComponents();

    default <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentHolder.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    default <T> T getOrDefault(DataComponentType<? extends T> type, T defaultValue) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentHolder.getOrDefault:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    default boolean has(DataComponentType<?> type) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentHolder.has:(Lnet/minecraft/core/component/DataComponentType;)Z");
    }
}
