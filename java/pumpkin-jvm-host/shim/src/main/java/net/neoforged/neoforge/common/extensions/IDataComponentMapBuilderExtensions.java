package net.neoforged.neoforge.common.extensions;

import java.util.function.Supplier;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import dev.pumpkin.shim.Unimplemented;

public interface IDataComponentMapBuilderExtensions extends DataComponentGetter {

    default <T> DataComponentMap.Builder set(Supplier<? extends DataComponentType<T>> componentType, T value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IDataComponentMapBuilderExtensions.set:(Ljava/util/function/Supplier;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentMap$Builder;");
    }
}
