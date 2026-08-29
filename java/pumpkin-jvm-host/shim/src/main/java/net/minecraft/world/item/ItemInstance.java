package net.minecraft.world.item;

import net.minecraft.core.TypedInstance;
import net.minecraft.core.component.DataComponentGetter;
import net.neoforged.neoforge.common.extensions.ItemInstanceExtension;

public interface ItemInstance extends TypedInstance<Item>, DataComponentGetter, ItemInstanceExtension {

    int count();
}
