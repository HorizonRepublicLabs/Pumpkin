package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public abstract class SingleItemRecipe implements Recipe<SingleRecipeInput> {

    public SingleItemRecipe(Recipe.CommonInfo commonInfo, Ingredient input, ItemStackTemplate result) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.<init>:(Lnet/minecraft/world/item/crafting/Recipe$CommonInfo;Lnet/minecraft/world/item/crafting/Ingredient;Lnet/minecraft/world/item/ItemStackTemplate;)V");
    }

    public abstract RecipeSerializer<? extends SingleItemRecipe> getSerializer();

    public abstract RecipeType<? extends SingleItemRecipe> getType();

    public boolean matches(SingleRecipeInput input, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.matches:(Lnet/minecraft/world/item/crafting/SingleRecipeInput;Lnet/minecraft/world/level/Level;)Z");
    }

    public boolean showNotification() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.showNotification:()Z");
    }

    public Ingredient input() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.input:()Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    protected ItemStackTemplate result() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.result:()Lnet/minecraft/world/item/ItemStackTemplate;");
    }

    public PlacementInfo placementInfo() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.placementInfo:()Lnet/minecraft/world/item/crafting/PlacementInfo;");
    }

    public ItemStack assemble(SingleRecipeInput input) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.assemble:(Lnet/minecraft/world/item/crafting/SingleRecipeInput;)Lnet/minecraft/world/item/ItemStack;");
    }

    public interface Factory<T extends SingleItemRecipe> {

        T create(Recipe.CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result);
    }

    protected SingleItemRecipe() {
    }
}
