package net.neoforged.neoforge.common.extensions;

import java.util.function.Supplier;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import dev.pumpkin.shim.Unimplemented;

public interface IDataComponentMapBuilderExtensions extends DataComponentGetter {

    // Pumpkin divergence: real body. The NeoForge convenience overload: resolve the
    // supplier -- a DeferredHolder in practice -- and delegate.
    default <T> DataComponentMap.Builder set(Supplier<? extends DataComponentType<T>> componentType, T value) {
        return ((DataComponentMap.Builder) this).set(componentType.get(), value);
    }
}
