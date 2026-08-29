package net.minecraft.world.item.crafting;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import dev.pumpkin.shim.Unimplemented;

public class SmeltingRecipe extends AbstractCookingRecipe {

    public SmeltingRecipe(Recipe.CommonInfo commonInfo, AbstractCookingRecipe.CookingBookInfo bookInfo, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.<init>:(Lnet/minecraft/world/item/crafting/Recipe$CommonInfo;Lnet/minecraft/world/item/crafting/AbstractCookingRecipe$CookingBookInfo;Lnet/minecraft/world/item/crafting/Ingredient;Lnet/minecraft/world/item/ItemStackTemplate;FI)V");
    }

    protected Item furnaceIcon() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.furnaceIcon:()Lnet/minecraft/world/item/Item;");
    }

    public RecipeSerializer<SmeltingRecipe> getSerializer() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.getSerializer:()Lnet/minecraft/world/item/crafting/RecipeSerializer;");
    }

    public RecipeType<SmeltingRecipe> getType() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.getType:()Lnet/minecraft/world/item/crafting/RecipeType;");
    }

    public RecipeBookCategory recipeBookCategory() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.recipeBookCategory:()Lnet/minecraft/world/item/crafting/RecipeBookCategory;");
    }

    protected SmeltingRecipe() {
    }
}
