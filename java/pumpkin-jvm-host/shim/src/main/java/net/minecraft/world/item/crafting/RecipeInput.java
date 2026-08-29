package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface RecipeInput {

    ItemStack getItem(int index);

    int size();

    default boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeInput.isEmpty:()Z");
    }
}
