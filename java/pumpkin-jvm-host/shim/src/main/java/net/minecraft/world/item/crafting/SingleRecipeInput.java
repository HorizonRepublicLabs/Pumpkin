package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public record SingleRecipeInput(ItemStack item) implements RecipeInput {

    public ItemStack getItem(int index) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleRecipeInput.getItem:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleRecipeInput.size:()I");
    }
}
