package net.minecraft.world.inventory;

import net.minecraft.world.item.crafting.RecipeHolder;

public interface RecipeCraftingHolder {

    void setRecipeUsed(final RecipeHolder<?> recipeUsed);

    RecipeHolder<?> getRecipeUsed();
}
