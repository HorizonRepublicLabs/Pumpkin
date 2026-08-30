package net.neoforged.neoforge.common.extensions;

import java.util.function.Supplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.Item;
import dev.pumpkin.shim.Unimplemented;

public interface IItemPropertiesExtensions {

    // Pumpkin divergence: real body. NeoForge sugar for a default component value on an
    // item -- metadata Pumpkin does not model; accepted and dropped, chain continues.
    default <T> Item.Properties component(Supplier<? extends DataComponentType<T>> componentType, T value) {
        return (Item.Properties) this;
    }
}
