package net.minecraft.world.item.crafting;

import java.util.List;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractCookingRecipe extends SingleItemRecipe {

    public AbstractCookingRecipe(Recipe.CommonInfo commonInfo, AbstractCookingRecipe.CookingBookInfo bookInfo, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/AbstractCookingRecipe.<init>:(Lnet/minecraft/world/item/crafting/Recipe$CommonInfo;Lnet/minecraft/world/item/crafting/AbstractCookingRecipe$CookingBookInfo;Lnet/minecraft/world/item/crafting/Ingredient;Lnet/minecraft/world/item/ItemStackTemplate;FI)V");
    }

    public abstract RecipeSerializer<? extends AbstractCookingRecipe> getSerializer();

    public abstract RecipeType<? extends AbstractCookingRecipe> getType();

    public CookingBookCategory category() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/AbstractCookingRecipe.category:()Lnet/minecraft/world/item/crafting/CookingBookCategory;");
    }

    public String group() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/AbstractCookingRecipe.group:()Ljava/lang/String;");
    }

    protected abstract Item furnaceIcon();

    public List<RecipeDisplay> display() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/AbstractCookingRecipe.display:()Ljava/util/List;");
    }

    public record CookingBookInfo(CookingBookCategory category, String group) implements Recipe.BookInfo<CookingBookCategory> {
    }

    public interface Factory<T extends AbstractCookingRecipe> {

        T create(Recipe.CommonInfo commonInfo, AbstractCookingRecipe.CookingBookInfo cbookInfotegory, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime);
    }

    protected AbstractCookingRecipe() {
    }
}
