package net.minecraft.world.item;

import net.minecraft.core.TypedInstance;
import net.minecraft.core.component.DataComponentGetter;
import net.neoforged.neoforge.common.extensions.ItemInstanceExtension;
import dev.pumpkin.shim.Unimplemented;

public interface ItemInstance extends TypedInstance<Item>, DataComponentGetter, ItemInstanceExtension {

    int count();

    default int getMaxStackSize() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemInstance.getMaxStackSize:()I");
    }
}
