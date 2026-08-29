package net.minecraft.world.item.crafting;

import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class ShapedRecipe extends NormalCraftingRecipe {

    public ShapedRecipe(Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo, ShapedRecipePattern pattern, ItemStackTemplate result) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipe.<init>:(Lnet/minecraft/world/item/crafting/Recipe$CommonInfo;Lnet/minecraft/world/item/crafting/CraftingRecipe$CraftingBookInfo;Lnet/minecraft/world/item/crafting/ShapedRecipePattern;Lnet/minecraft/world/item/ItemStackTemplate;)V");
    }

    public RecipeSerializer<ShapedRecipe> getSerializer() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipe.getSerializer:()Lnet/minecraft/world/item/crafting/RecipeSerializer;");
    }

    protected PlacementInfo createPlacementInfo() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipe.createPlacementInfo:()Lnet/minecraft/world/item/crafting/PlacementInfo;");
    }

    public boolean matches(CraftingInput input, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipe.matches:(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/world/level/Level;)Z");
    }

    public ItemStack assemble(CraftingInput input) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipe.assemble:(Lnet/minecraft/world/item/crafting/CraftingInput;)Lnet/minecraft/world/item/ItemStack;");
    }

    public int getWidth() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipe.getWidth:()I");
    }

    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipe.getHeight:()I");
    }

    public List<RecipeDisplay> display() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipe.display:()Ljava/util/List;");
    }

    public ShapedRecipe() {
    }
}
