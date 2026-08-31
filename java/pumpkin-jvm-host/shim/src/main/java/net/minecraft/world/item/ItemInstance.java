package net.minecraft.world.item;

import net.minecraft.core.TypedInstance;
import net.minecraft.core.component.DataComponentGetter;
import net.neoforged.neoforge.common.extensions.ItemInstanceExtension;
import dev.pumpkin.shim.Unimplemented;

public interface ItemInstance extends TypedInstance<Item>, DataComponentGetter, ItemInstanceExtension {

    int count();

    // Pumpkin divergence: the item's declared max stack size; 64, the vanilla
    // default, when the mod did not say.
    default int getMaxStackSize() {
        Item item = typeHolder() == null ? null : typeHolder().value();
        int declared = item == null ? -1 : item.pumpkinMaxStackSize();
        return declared > 0 ? declared : 64;
    }
}
