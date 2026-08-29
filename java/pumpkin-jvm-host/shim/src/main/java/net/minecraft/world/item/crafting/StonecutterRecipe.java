package net.minecraft.world.item.crafting;

import java.util.List;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import dev.pumpkin.shim.Unimplemented;

public class StonecutterRecipe extends SingleItemRecipe {

    public StonecutterRecipe(Recipe.CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/StonecutterRecipe.<init>:(Lnet/minecraft/world/item/crafting/Recipe$CommonInfo;Lnet/minecraft/world/item/crafting/Ingredient;Lnet/minecraft/world/item/ItemStackTemplate;)V");
    }

    public RecipeType<StonecutterRecipe> getType() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/StonecutterRecipe.getType:()Lnet/minecraft/world/item/crafting/RecipeType;");
    }

    public RecipeSerializer<StonecutterRecipe> getSerializer() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/StonecutterRecipe.getSerializer:()Lnet/minecraft/world/item/crafting/RecipeSerializer;");
    }

    public String group() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/StonecutterRecipe.group:()Ljava/lang/String;");
    }

    public List<RecipeDisplay> display() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/StonecutterRecipe.display:()Ljava/util/List;");
    }

    public RecipeBookCategory recipeBookCategory() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/StonecutterRecipe.recipeBookCategory:()Lnet/minecraft/world/item/crafting/RecipeBookCategory;");
    }

    public StonecutterRecipe() {
    }
}
